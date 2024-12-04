package com.ss.studysystem.controller;

import com.ss.studysystem.UI.logic.switch_scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class login {

    @FXML
    private Button confirm;

    @FXML
    private Button create_new_account;

    @FXML
    private Button forgot_password;

    @FXML
    private PasswordField user_password;

    @FXML
    private TextField user_textfield;

    switch_scene switcher = new switch_scene();

    @FXML
    void initialize(){
        create_new_account.setOnAction(this::sign_up);
    }

    @FXML
    void sign_up(ActionEvent event) {
        try{
            switcher.sign_up_scene(event, (Stage) confirm.getScene().getWindow());
        }catch (Exception err){
            err.printStackTrace();
        }
    }

}
