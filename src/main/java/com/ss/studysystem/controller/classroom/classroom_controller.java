package com.ss.studysystem.controller.classroom;

import com.ss.studysystem.UI.components.modal_builder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class classroom_controller {

    @FXML
    private VBox pane;


    @FXML
    private Button create_classroom;


    @FXML
    void initialize(){

    }


    @FXML
    void create(ActionEvent event) throws Exception {
        URL loader = getClass().getResource("/com/ss/studysystem/Fxml/startup/Create and Join.fxml");
        Parent load_view = FXMLLoader.load(loader);

        Stage stage = modal_builder.build_fixed_modal((Stage) create_classroom.getScene().getWindow(), load_view, 400, 400);
        stage.show();

        URL Toggle = getClass().getResource("/com/ss/studysystem/Fxml/chatroomlogo.fxml");
        Parent Toggle_view = FXMLLoader.load(Toggle);

        pane.getChildren().add(Toggle_view);

    }

}
