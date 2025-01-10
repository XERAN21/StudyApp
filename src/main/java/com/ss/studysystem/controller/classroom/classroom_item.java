package com.ss.studysystem.controller.classroom;

import com.ss.studysystem.Model.Classrooms;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class classroom_item {

    @FXML
    private GridPane class_root;

    @FXML
    private Label classname;

    @FXML
    private Label desc;

    void init(Classrooms classrooms){
        Platform.runLater(()->{
            classname.setText(classrooms.getName());
            desc.setText(classrooms.getDescription());
        });
    }

}
