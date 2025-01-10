package com.ss.studysystem.controller.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class Delete_acc_controller {

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_com;

    @FXML
    private PasswordField del_pass;

    @FXML
    void initialize() {
        btn_cancel.setOnAction(this::cancel);
    }
    private Consumer<Boolean> onResult;
    @FXML
    void cancel(ActionEvent event) {

        if (onResult != null) {
            onResult.accept(false);
        }

        Button clicked = (Button) event.getSource();
        Stage stage = (Stage) clicked.getScene().getWindow();
        stage.close();
    }
}
