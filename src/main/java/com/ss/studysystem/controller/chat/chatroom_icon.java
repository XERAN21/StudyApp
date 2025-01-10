package com.ss.studysystem.controller.chat;

import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import com.ss.studysystem.controller.create_and_join;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/Create and Join.fxml"));
            Parent load_view = loader.load();

            create_and_join createAndJoin = loader.getController();
            createAndJoin.setLocation(chat_where_is_this.CHAT);
            createAndJoin.change_placeholder("Create "+ chat_where_is_this.CLASSROOM.getValue());

            Stage stage = modal_builder.build_fixed_modal((Stage) create_classroom.getScene().getWindow(), load_view, 385,305);

            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
