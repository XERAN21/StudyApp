package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Study_Goals {

    @FXML
    private Button add_goal;

    @FXML
    private VBox goal_list;

    @FXML
    void Add_new_goal(ActionEvent event) {

        //
        Button new_g = new Button("Goal");
        goal_list.getChildren().add(new_g);
        //
    }

}
