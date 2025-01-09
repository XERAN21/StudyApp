package com.ss.studysystem.controller;

import com.ss.studysystem.Model.Chatrooms;
import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import com.ss.studysystem.database.controller.Chatroom_controller;
import com.ss.studysystem.database.controller.classroom_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
    //todo Chatroom join
    @FXML
    void confirm(ActionEvent event){
      if (!name.getText().isEmpty() || !description.getText().isEmpty()){
          switch (location){
              case CHAT:
                  Chatrooms chatrooms = new Chatrooms();
                  chatrooms.setName(name.getText());
                  if (Chatroom_controller.create_Chatroom(chatrooms)){
                      System.out.println("work");
                  }else {
                      System.out.println("error");
                  }

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
      }else {

      }

    }

    @FXML
    void setClose(ActionEvent event){
       try{
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/Create and Join.fxml"));
           Parent load_view = loader.load();

           create_and_join createAndJoin = loader.getController();
           switch (location){
               case CHAT:
                   createAndJoin.setLocation(chat_where_is_this.CHAT);
                   break;
               case CLASSROOM:
                   createAndJoin.setLocation(chat_where_is_this.CLASSROOM);
                   break;
           }

           Stage stage = (Stage) close.getScene().getWindow();
           Scene sc = new Scene(load_view, stage.getWidth(), stage.getHeight(), Color.TRANSPARENT);
           stage.setScene(sc);
           stage.show();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

}
