package com.ss.studysystem.controller.settings;

import com.ss.studysystem.UI.components.modal_builder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;

public class user_profile {

    @FXML
    private GridPane root;

    private HBox box = new HBox();

    @FXML
    public void initialize(){
        try {
            URL path1 = getClass().getResource("/com/ss/studysystem/controller/user_setting_view");
            Node setting = FXMLLoader.load(path1);

            URL path2 = getClass().getResource("/com/ss/studysystem/controller/user_setting_vie2");
            Node pro = FXMLLoader.load(path2);

            box.getChildren().addAll(setting,pro);
            root.getChildren().add(box);

            Stage stage = modal_builder.build_fixed_modal((Stage) root.getScene().getWindow(),box,400,400);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
