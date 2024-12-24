package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class login_error_message {

    @FXML
    private Button close;

    @FXML
    private Button ok;

    @FXML
    void initialize(){
        ok.setOnAction(this::openLoginErrorMessage);
        close.setOnAction(this::close);
    }

    @FXML
    void openLoginErrorMessage(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/login_error_message.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Login Error");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void close(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
