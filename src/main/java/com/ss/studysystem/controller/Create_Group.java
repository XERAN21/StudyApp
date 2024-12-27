package com.ss.studysystem.controller;

import com.ss.studysystem.UI.layouts.chat_where_is_this;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Create_Group {

    @FXML
    private TextField name;

    @FXML
    private TextField description;

    @FXML
    private Button switch_confirm;

    @FXML
    private Button close;

    private chat_where_is_this location;

    public void setLocation(chat_where_is_this location) {
        this.location = location;
    }

    @FXML
    void initialize(){
        switch_confirm.setOnAction(this::confirm);
        close.setOnAction(this::setClose);
    }

    @FXML
    void confirm(ActionEvent event){
       switch (location){
           case CHAT:
               break;
           case CLASSROOM:
               break;
       }

    }

    @FXML
    void setClose(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
