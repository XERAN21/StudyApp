package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Appearance {

    @FXML
    private Circle light;

    @FXML
    private Circle dark;

    @FXML
    private Button confirm;

    @FXML
    private VBox preview;

    @FXML
    void initialize(){
        light.setOnMouseClicked(event -> Appearance("light"));
        dark.setOnMouseClicked(event -> Appearance("dark"));
    }

    @FXML
    void Appearance(String mode){
        try {
            if(mode.equals("light")) {
                preview.setStyle("-fx-background-color: white;");
            } else if(mode.equals("dark")){
                preview.setStyle("-fx-background-color: black;");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
