package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class forgot_password {

    @FXML
    private TextField email_input;

    @FXML
    private Button confirm_btn;

    @FXML
    void initialize(){
        confirm_btn.setOnAction(this::confirm);
    }

    @FXML
    void confirm(ActionEvent event) {
        //todo check the input is email format
        //todo check there's email in database if true sent(switch scene)
    }

}
