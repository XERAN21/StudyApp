package com.ss.studysystem.controller;

import com.ss.studysystem.Model.Role;
import com.ss.studysystem.Model.User_Classroom;
import com.ss.studysystem.UI.components.modal_builder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class joinclass {

    @FXML
    private Button btnjoin;

    @FXML
    void initialized(){
        btnjoin.setOnAction(this::joinclass);
    }

    @FXML
    void joinclass(ActionEvent event) {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/Create Group and Join.fxml"));
           Parent load_view = loader.load();

           Stage stage = modal_builder.build_fixed_modal((Stage) btnjoin.getScene().getWindow(), load_view, 400,400);
           stage.show();

       }catch (Exception e){
           e.printStackTrace();
       }
    }

}
