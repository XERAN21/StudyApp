package com.ss.studysystem.controller.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.w3c.dom.events.MouseEvent;

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
            URL path1 = getClass().getResource("/com/ss/studysystem/Fxml/Profile/UserProfile.fxml");
            VBox info = FXMLLoader.load(path1);

            URL path2 = getClass().getResource("/com/ss/studysystem/Fxml/Profile/profilepassword.fxml");
            VBox pass = FXMLLoader.load(path2);

            URL path3 = getClass().getResource("/com/ss/studysystem/Fxml/Profile/Delete Removal.fxml");
            VBox dele = FXMLLoader.load(path3);

            dele.prefWidthProperty().bind(prof_view.widthProperty().subtract(125));
            info.prefWidthProperty().bind(prof_view.widthProperty().subtract(125));
            pass.prefWidthProperty().bind(prof_view.widthProperty().subtract(125));

        setting.setSpacing(40);
        setting.setStyle("""
               -fx-background-color: white;
               -fx-border-color: white;
               """);

            setting.getChildren().addAll(info,pass,dele);


            URL css_path = getClass().getResource("/com/ss/studysystem/css/scroll_st.css");
            prof_view.getStylesheets().add(css_path.toExternalForm());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
