package com.ss.studysystem.controller;

import com.ss.studysystem.Model.To_Do_List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class del_task_controller {

    @FXML
    private Button cancel;

    @FXML
    private Button delete;

    @FXML
    private Text task_name;

    private Consumer<Boolean> onResult;
    private To_Do_List toDoList;

    @FXML
    void initialize() {
        cancel.setOnAction(event -> close_modal(event));
        delete.setOnAction(this::delete_task);
        Platform.runLater(()-> task_name.setText(toDoList.getContent()));
    }

    @FXML
    void delete_task(ActionEvent event) {
        if (onResult != null) {
            onResult.accept(true);
        }
        close_modal(event);

    }

    @FXML
    void close_modal(ActionEvent event) {

        if (onResult != null) {
            onResult.accept(false);
        }

        Button clicked = (Button) event.getSource();
        Stage stage = (Stage) clicked.getScene().getWindow();
        stage.close();
    }

    public void setToDoList(To_Do_List toDoList) {
        this.toDoList = toDoList;

    }

    public void setOnResult(Consumer<Boolean> onResult) {
        this.onResult = onResult;
    }
}
