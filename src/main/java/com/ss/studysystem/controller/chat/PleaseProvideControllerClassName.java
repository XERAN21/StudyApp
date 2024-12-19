package com.ss.studysystem.controller.chat;

import com.ss.studysystem.Model.Chatter;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;

public class PleaseProvideControllerClassName {

//    @FXML
//    private VBox chat_viewer;

    @FXML
     private GridPane chat_viewer;

    @FXML
    private VBox msg_view_root;

    int row = 0;


    @FXML
    void initialize() throws IOException {

        //todo async load messages from database
        //todo load_msg method -> create nodes, the following code below
        //todo websocket real time add node on pane type shit idk

        URL path = getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/msg_bubble.fxml");
        FXMLLoader loader = new FXMLLoader(path);
        Node bubble = loader.load();
        chat_message_ww_img_ctrl msg_ctrl = loader.getController();
        msg_ctrl.set_messages("こんにちは、皆さん。今日は、私が本を読むことが大好きな理由と、その影響についてお話ししたいと思います。特に、小説が私の人生に与えた影響について触れます。本を読むことは、ただの趣味ではなく、私にとって大切な活動です。", null, Chatter.SENDER);
        chat_viewer.add(bubble,0, row++); //todo SELF? 1:0
        //

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
                chat_viewer.add(wrapper,1,row++);
            });
        });

        msg_view_root.getChildren().add(textbox);

    }


}
