package com.ss.studysystem.controller.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;

public class user_setting_vie2 {

    @FXML
    private Button switch_edit;

    @FXML
    private VBox setting;


    @FXML
    private ScrollPane prof_view;

    @FXML
    public void initialize(){
        try{
            URL path1 = getClass().getResource("/com/ss/studysystem/Fxml/UserProfile.fxml");
            VBox info = FXMLLoader.load(path1);

            URL path2 = getClass().getResource("/com/ss/studysystem/Fxml/profilepassword.fxml");
            VBox pass = FXMLLoader.load(path2);

            URL path3 = getClass().getResource("/com/ss/studysystem/Fxml/Delete Removal.fxml");
            VBox dele = FXMLLoader.load(path3);

            dele.prefWidthProperty().bind(prof_view.widthProperty());
            info.prefWidthProperty().bind(prof_view.widthProperty());
            pass.prefWidthProperty().bind(prof_view.widthProperty());



            setting.getChildren().addAll(info,pass,dele);




        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
