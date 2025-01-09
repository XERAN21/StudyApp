package com.ss.studysystem.controller.chat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class chatroom_icon {

    @FXML
    private ToggleButton classroom;

    @FXML
    private Button create_classroom;

    @FXML
    void initialize(){
        classroom.setOnAction(this::enter_chatroom);
        create_classroom.setOnAction(this::add_Chatroom);
    }

    @FXML
    void enter_chatroom(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/chat_real.fxml"));
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
    void add_Chatroom(ActionEvent event){
        try{
            System.out.println("Classroom created!");

            Stage stage = (Stage) create_classroom.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
