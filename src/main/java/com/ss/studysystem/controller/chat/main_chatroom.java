package com.ss.studysystem.controller.chat;

import com.ss.studysystem.UI.layouts.chat_where_is_this;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import java.net.URL;

public class main_chatroom {
    @FXML
    private BorderPane main_root;

    @FXML
    void initialize() throws Exception {
        URL view_path = getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/chat_real.fxml");
        FXMLLoader view_loader = new FXMLLoader(view_path);
        Region view_region = view_loader.load();
        main_chat_messages_view chat_ctrl = view_loader.getController();

        chat_ctrl.setWhere_am_i(chat_where_is_this.CHAT);
        main_root.setCenter(view_region);

        URL chat_rooms = getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/chatroom/chatroom_icon.fxml");
        Region room_region = new FXMLLoader(chat_rooms).load();
        main_root.setLeft(room_region);

        URL friend_nav_path = getClass().getResource("/com/ss/studysystem/Fxml/friend_section.fxml");
        FXMLLoader fnev_loader = new FXMLLoader(friend_nav_path);
        Region friend_nav_reg = fnev_loader.load();
        main_root.setRight(friend_nav_reg);

    }


}
