package com.ss.studysystem.controller;

import com.ss.studysystem.UI.components.modal_builder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class Change_password_controller {

    @FXML
    private TextField current;

    @FXML
    private TextField new_pass;

    @FXML
    private TextField com_pass;

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_com;

    private Consumer<Boolean> onResult;

    @FXML
    void initialize() {

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
    void com(ActionEvent event) {

    }

    @FXML
    void com_new_pass(ActionEvent event) {

    }

    @FXML
    void cur_pass(ActionEvent event) {

    }

    @FXML
    void new_pass(ActionEvent event) {

    }

}
