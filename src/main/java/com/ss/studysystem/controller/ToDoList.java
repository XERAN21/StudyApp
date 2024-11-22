package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.time.LocalDate;
import java.util.List;

public class ToDoList {

    @FXML
    private Label Month;

    @FXML
    private Circle Monday;

    @FXML
    private Circle Tuesday;

    @FXML
    private Circle Wednesday;

    @FXML
    private Circle Thursday;

    @FXML
    private Circle Friday;

    @FXML
    private Circle Saturday;

    @FXML
    private Circle Sunday;

    @FXML
    private Label Day;

    @FXML
    private Button add_list;

    @FXML
    private VBox list_view;

    @FXML
    private HBox days_list;

    @FXML
    void initialize() {

        String today = LocalDate.now().getDayOfWeek().toString();
        System.out.println(today);

        for(Node n : days_list.getChildren()){
            if(n instanceof VBox){
                for( Node l : ((VBox) n).getChildren()){
                    if(l.getId().matches(today)){
                        Node c = ((VBox) n).getChildren().get(1);
                        c.setStyle("-fx-background-color: orange;");
                        return;
                    }
                }
            }
        }


    }
    @FXML
    void add_new_list(ActionEvent event) {
        Button new_list = new Button("Hello");
        list_view.getChildren().add(new_list);
    }

}
