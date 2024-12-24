package com.ss.studysystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class Classroom {

    @FXML
    private ToggleButton classroom;

    @FXML
    private Button create_classroom;

    @FXML
    void initialize(){
        classroom.setOnAction(this::enterClassroom);
        create_classroom.setOnAction(this::createClassroom);
    }

    @FXML
    void enterClassroom(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/classroom_view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Classroom");
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void createClassroom(ActionEvent event){
        try{
            System.out.println("Classroom created!");

            Stage stage = (Stage) create_classroom.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
