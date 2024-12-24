package com.ss.studysystem.controller.chat.nav;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class who_chat_ctrl {

    @FXML
    private ToggleButton chat;

    @FXML
    void initialize(){

    }

    void set_chat_name(String name){
        Platform.runLater(()->chat.setText(name));
    }

}
