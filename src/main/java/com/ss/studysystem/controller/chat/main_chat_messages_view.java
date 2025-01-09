package com.ss.studysystem.controller.chat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ss.studysystem.Model.Chatter;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import com.ss.studysystem.web.lumi_websocket;
import com.ss.studysystem.web.message_handler;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class main_chat_messages_view implements message_handler {

//    @FXML
//    private VBox chat_viewer;

    @FXML
    private ScrollPane chat_scroll;

    @FXML
    private GridPane chat_viewer;

    @FXML
    private VBox msg_view_root;

    int row = 0;

    @FXML
    void initialize() throws IOException {

        lumi_websocket.getInstance().setMessageHandler(this);

        //todo async load messages from database
        //todo load_msg method -> create nodes, the following code below
        //todo websocket real time add node on pane type shit idk

        URL path = getClass().getResource("/com/ss/studysystem/css/scroll_round.css");
        if (path != null)
            chat_scroll.getStylesheets().add(path.toExternalForm());


        chat_viewer.getChildren().addListener((javafx.collections.ListChangeListener<? super Node>) change -> {
            if (chat_scroll.getVvalue() >= 0.5) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        Platform.runLater(this::smoothScrollToBottom);
                    }
                }
            }
        });

        URL user_input = getClass().getResource("/com/ss/studysystem/Fxml/chat_box.fxml");
        FXMLLoader input = new FXMLLoader(user_input);
        Node textbox = input.load();
        ChatBoxCtrl cbt = input.getController();
        cbt.setPlace(chat_where_is_this.CHAT);

        cbt.set_on_result(result -> {
            Platform.runLater(() -> {
                Node chat_bubb = cbt.getCurrent_bubble();
                VBox wrapper = new VBox(chat_bubb);
                wrapper.setAlignment(Pos.TOP_RIGHT);
                chat_viewer.add(wrapper, 1, row++);
                smoothScrollToBottom();
            });
        });

        msg_view_root.getChildren().add(textbox);

    }

    private void smoothScrollToBottom() {
        double startValue = chat_scroll.getVvalue();
        double endValue = 1.0;

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(chat_scroll.vvalueProperty(), startValue)),
                new KeyFrame(Duration.millis(300), new KeyValue(chat_scroll.vvalueProperty(), endValue))
        );

        timeline.play();
    }

    @Override
    public void handleMessage(String payload) {
        chat_bubble_gen cbg = new chat_bubble_gen();
        String message = "Invalid message format";
        try {
            String uid;
            JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
            if (jsonObject.has("uid")) {
                uid = jsonObject.get("uid").getAsString();
            }

            if (jsonObject.has("reply")) {
                message = jsonObject.get("reply").getAsString();
            } else if (jsonObject.has("message")) {
                message = jsonObject.get("message").getAsString();
            }

        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }


        Node reply = cbg.generate_chat_msg(Chatter.SENDER, message);
        chat_viewer.add(reply, 0, row++);
    }

    public void onClose() {
        lumi_websocket.getInstance().clearMessageHandler();
    }
}
