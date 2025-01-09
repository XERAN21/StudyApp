package com.ss.studysystem.controller.settings;

import com.ss.studysystem.UI.components.modal_builder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;

public class del_acc_ctrl {

    @FXML
    private Button btn_dele;

    @FXML
    void initialize(){

    }

    @FXML
    void del_acc(ActionEvent event) throws Exception{
        URL path3 = getClass().getResource("/com/ss/studysystem/Fxml/quiz/delete_account.fxml");
        Parent dele = FXMLLoader.load(path3);

        Stage modalStage = modal_builder.build_fixed_modal((Stage) btn_dele.getScene().getWindow(), dele);
        modalStage.show();
    }



}
