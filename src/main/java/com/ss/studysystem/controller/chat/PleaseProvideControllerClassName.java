package com.ss.studysystem.controller.chat;

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

public class PleaseProvideControllerClassName implements message_handler {

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


        msg_view_root.getChildren().addListener((javafx.collections.ListChangeListener<? super Node>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    Platform.runLater(this::smoothScrollToBottom);
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
    public void handleMessage(String message) {
        chat_bubble_gen cbg = new chat_bubble_gen();
        Node reply = cbg.generate_chat_msg(Chatter.SENDER, message);
        chat_viewer.add(reply, 0, row++);
    }

    public void onClose() {
        lumi_websocket.getInstance().clearMessageHandler();
    }
}
