package com.ss.studysystem.controller;

import com.ss.studysystem.Model.Chatrooms;
import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import com.ss.studysystem.database.controller.classroom_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Create_locationCG {

    @FXML
    private TextField name;

    @FXML
    private TextField description;

    @FXML
    private Button switch_confirm;

    @FXML
    private Button close;

    private chat_where_is_this location;

    public void setLocation(chat_where_is_this location) {
        this.location = location;
    }

    @FXML
    void initialize(){
        switch_confirm.setOnAction(this::confirm);
        close.setOnAction(this::setClose);
    }

    @FXML
    void confirm(ActionEvent event){
       switch (location){
           case CHAT:
               Chatrooms chatrooms = new Chatrooms();
               chatrooms.setName(name.getText());

               break;
           case CLASSROOM:
               Users users = new Users();
               users.setId(1);

               Classrooms classrooms = new Classrooms();
               classrooms.setName(name.getText());
               classrooms.setDescription(description.getText());
               classrooms.setUser(users);

               if (classroom_controller.create_classroom(classrooms)){
                   System.out.println("Done");
               }else {
                   System.out.println("error");
               }

               break;
       }

    }

    @FXML
    void setClose(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
