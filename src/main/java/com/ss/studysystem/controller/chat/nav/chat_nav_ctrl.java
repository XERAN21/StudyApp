package com.ss.studysystem.controller.chat.nav;

import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.layouts.chat_where_is_this;
import com.ss.studysystem.controller.create_and_join;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class chat_nav_ctrl {

    @FXML
    private Button create_join;

    @FXML
    void initialize(){

        create_join.setOnAction(this::createClassroom);
    }



    @FXML
    void createClassroom(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/Create and Join.fxml"));
            Parent load_view = loader.load();

            create_and_join createAndJoin = loader.getController();
            createAndJoin.setLocation(chat_where_is_this.CLASSROOM);
            createAndJoin.setLocation(chat_where_is_this.CHAT);

            Stage stage = modal_builder.build_fixed_modal((Stage) create_join.getScene().getWindow(), load_view, 400,400);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
