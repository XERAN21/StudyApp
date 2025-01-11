package com.ss.studysystem.controller.Profile;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;

public class FinalProfileCtr {

    @FXML
    private BorderPane inner_border;

    @FXML
    private BorderPane root;

    @FXML
    void initialize(){
        try{
            URL path = getClass().getResource("/com/ss/studysystem/Fxml/user_profile_setting_view.fxml");
            FXMLLoader loader = new FXMLLoader(path);
            Node settings_node = loader.load();
            inner_border.setCenter(settings_node);



            URL path1 =getClass().getResource("/com/ss/studysystem/Fxml/UserSetting.fxml");
            FXMLLoader loader1 = new FXMLLoader(path1);
            Node Setting = loader1.load();
            inner_border.setLeft(Setting);



         VBox LeftBorder= new VBox();
         LeftBorder.setStyle("""
                 -fx-pref-width:100px;
                 -fx-pref-height: 400px;
                 -fx-background-color:F3EEF8;
                 """);
         root.setLeft(LeftBorder);

            VBox TopBorder= new VBox();
            TopBorder.setStyle("""
                 -fx-pref-width:600px;
                 -fx-pref-height: 55px;
                 -fx-background-color:F3EEF8;
                 """);
            root.setTop(TopBorder);






        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
