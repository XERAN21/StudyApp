package com.ss.studysystem.controller.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class Edit_controller {

    @FXML
    private TextField Email;

    @FXML
    private Button btn_com;

    @FXML
    private TextField Username;

    @FXML
    private Button btn_cancel;


    private Consumer<Boolean> onResult;

    @FXML
    void initialize() {
        btn_cancel.setOnAction(this::cancel);
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


}
