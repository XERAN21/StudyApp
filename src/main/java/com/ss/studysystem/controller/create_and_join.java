package com.ss.studysystem.controller;

import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class create_and_join {

    @FXML
    private Label placeholder;

    @FXML
    private Button create;

    @FXML
    private Button join;

    @FXML
    private Button close;

    private chat_where_is_this location;

    public void setLocation(chat_where_is_this location) {
        this.location = location;
        Platform.runLater(()->placeholder.setText("Create " +location.getValue()));
    }

    @FXML
    void initialize(){
        create.setOnAction(this::Create);
        join.setOnAction(this::Join);
        close.setOnAction(this::setClose);
    }

    @FXML
    void Create(ActionEvent event){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/CreateGroup2.fxml"));
            Parent load_view = loader.load();

            if (location == chat_where_is_this.CLASSROOM){

                Create_Group key_word = loader.getController();
                key_word.setLocation(chat_where_is_this.CLASSROOM);

            } else if (location == chat_where_is_this.CHAT) {

                Create_Group key_word = loader.getController();
                key_word.setLocation(chat_where_is_this.CHAT);
            }

            Stage stage = (Stage) create.getScene().getWindow();
            Scene sc = new Scene(load_view,stage.getWidth(),stage.getHeight(), Color.TRANSPARENT);
            stage.setScene(sc);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void Join(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
            Parent load_view = loader.load();

            Stage stage = modal_builder.build_fixed_modal((Stage) join.getScene().getWindow(), load_view, 400,400);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void change_placeholder(String text){
        Platform.runLater(()->{
            placeholder.setText(text);

        });
    }

    @FXML
    void setClose(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public String get_placeholder(){
         String text = placeholder.getText();
         return text;
    }
}
