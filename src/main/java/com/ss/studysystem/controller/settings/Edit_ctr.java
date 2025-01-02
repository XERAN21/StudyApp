package com.ss.studysystem.controller.settings;

import com.ss.studysystem.UI.components.modal_builder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Edit_ctr {

    @FXML
    private Label email;

    @FXML
    private Circle profile_img;

    @FXML
    private Button switch_edit;

    @FXML
    private Label username;

    @FXML
    void initialize(){
        switch_edit.setOnAction(this::edit);
    }

    @FXML
    void edit(ActionEvent event)  {
        try {
            URL path3 = getClass().getResource("/com/ss/studysystem/Fxml/Profile/Edit User.fxml");
            Parent dele = FXMLLoader.load(path3);

            Stage modalStage = modal_builder.build_fixed_modal((Stage) switch_edit.getScene().getWindow(), dele);
            modalStage.show();
        } catch (IOException e) {
            throw new  RuntimeException(e);
        }
    }
}


