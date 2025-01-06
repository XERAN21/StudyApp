package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class forgot_password_ctrl {

    @FXML
    private Button confirm;

    @FXML
    private Button close;

    @FXML
    void initialize(){
        close.setOnAction(this::close_modal);
    }

    void close_modal(ActionEvent event){
        Stage st = (Stage) confirm.getScene().getWindow();
        st.close();
    }

}
