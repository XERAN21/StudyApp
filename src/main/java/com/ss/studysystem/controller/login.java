package com.ss.studysystem.controller;

import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.logic.switch_scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    void initialize() {
        create_new_account.setOnAction(this::sign_up);
        confirm.setOnAction(e-> confirmation());
    }

    @FXML
    void sign_up(ActionEvent event) {
        try {
            switcher.switchToSignUpScene(event, (Stage) confirm.getScene().getWindow());
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    @FXML
    void confirmation() {
        try {
            FXMLLoader tdl = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/ToDoList.fxml"));
            Parent tdl_view = tdl.load();
            Stage stage = modal_builder.build_modal((Stage) confirm.getScene().getWindow(), tdl_view);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
