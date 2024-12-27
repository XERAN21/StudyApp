package com.ss.studysystem.controller.chat;

import com.ss.studysystem.Model.Chatter;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import com.ss.studysystem.web.lumi_websocket;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class ChatBoxCtrl {

    @FXML
    VBox chat_root;

    @FXML
    private Button send;

    @FXML
    private TextArea user_input;

    private Consumer<Boolean> on_result;

    private chat_where_is_this place;

    private Node current_bubble;

    @FXML
    void initialize() {

        send.setOnAction(event -> geo_guesser());

        user_input.textProperty().addListener((observable, oldValue, newValue) -> {
            resizeTextArea();
        });

        user_input.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && !event.isShiftDown()) {
                String text = user_input.getText().strip();
                if (!text.isEmpty()) {
                    lumi_websocket.getInstance().sendMsg(text);
                    geo_guesser();
                    user_input.clear();
                    resetTextArea();
                }
                event.consume();
            } else if (event.getCode() == KeyCode.ENTER && event.isShiftDown()) {
                user_input.appendText(System.lineSeparator());
            }
        });
    }

    private void resizeTextArea() {
        double contentHeight = user_input.lookup(".content").getBoundsInLocal().getHeight();
        double newHeight = Math.max(30, Math.min(contentHeight, 100));
        user_input.setPrefHeight(newHeight);
    }

    private void resetTextArea() {
        user_input.setPrefHeight(30);
    }

    public chat_where_is_this getPlace() {
        return place;
    }

    public void setPlace(chat_where_is_this place) {
        this.place = place;
    }

    public Node getCurrent_bubble() {
        return current_bubble;
    }

    public void set_on_result(Consumer<Boolean> on_result) {
        this.on_result = on_result;
    }

    void geo_guesser() {
        if (place == null) return;

        switch (place) {
            case CHAT -> generate_chat_msg(Chatter.SELF, user_input.getText());
            case FORUM -> System.out.println("gg");
            case ASSIGNMENT -> System.out.println("wp");
        }
    }

    @FXML
    public void generate_chat_msg(Chatter who, String msg) {

        if (msg == null || msg.trim().isEmpty()) return; //todo throw error or not, idk

        //todo async insert msg into database
        //todo set on suck seed, the following code below

        try {
        /*
            Node bubble;
            FXMLLoader loader;

            switch (who) {
                case SELF:
                    URL self_path = getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/msg_bubble_self.fxml");
                    loader = new FXMLLoader(self_path);
                    bubble = loader.load();
                    break;
                case SENDER:
                    URL path = getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/msg_bubble.fxml");
                    loader = new FXMLLoader(path);
                    bubble = loader.load();
                    break;
                default:
                    throw new IOException("Error loading messages");
            }

            chat_message_ww_img_ctrl msg_ctrl = loader.getController();
            msg_ctrl.set_messages(msg, null, Chatter.SELF);
            */
            user_input.clear();
            chat_bubble_gen abg = new chat_bubble_gen(); //todo change name (ABG stands for Asian .... Girl)
            this.current_bubble = abg.generate_chat_msg(who, msg);
            on_result.accept(true);
//            this.current_bubble = bubble;
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
    }


}
