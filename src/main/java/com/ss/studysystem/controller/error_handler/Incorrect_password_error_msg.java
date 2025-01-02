package com.ss.studysystem.controller.error_handler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Incorrect_password_error_msg {

    @FXML
    private Button ok_btn;

    @FXML
    void initialize(){
        ok_btn.setOnAction(this::close);
    }

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) ok_btn.getScene().getWindow();
        stage.close();
    }

}
