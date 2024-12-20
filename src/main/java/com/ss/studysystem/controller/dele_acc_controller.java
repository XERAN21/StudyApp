package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class dele_acc_controller {

    @FXML
    private TextField pass_text;

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_con;

    private Consumer<Boolean> onResult;

    @FXML
    void initialize(){

    }

    @FXML
    void cancel(ActionEvent event) {
        if (onResult != null) {
            onResult.accept(false);
        }

        Button clicked = (Button) event.getSource();
        Stage stage = (Stage) clicked.getScene().getWindow();
        stage.close();
    }

    @FXML
    void confirm(ActionEvent event) {

    }


}
