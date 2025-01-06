package com.ss.studysystem.controller.settings;

import com.ss.studysystem.UI.components.modal_builder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;

public class change_pw_ctrl {

    @FXML
    private Button change_password;

    @FXML
    void initialize(){

    }

    @FXML
    void change_user_pw(ActionEvent event) throws Exception {
        URL path3 = getClass().getResource("/com/ss/studysystem/Fxml/quiz/change_password.fxml");
        Parent dele = FXMLLoader.load(path3);

        Stage modalStage = modal_builder.build_fixed_modal((Stage) change_password.getScene().getWindow(), dele,400,400);
        modalStage.show();
    }
}
