package com.ss.studysystem.controller.Profile;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.net.URL;

public class Setting_ctr {
    @FXML
    private VBox setting;
    @FXML
    public void initialize(){
        try{
            URL path1 = getClass().getResource("/com/ss/studysystem/Fxml/Profile/Setting3.fxml");
            VBox set1 = FXMLLoader.load(path1);

            URL path2 = getClass().getResource("/com/ss/studysystem/Fxml/Profile/Setting4.fxml");
            VBox set2 = FXMLLoader.load(path2);

            setting.getChildren().addAll(set1,set2);

           setting.setSpacing(30);
           setting.setAlignment(Pos.TOP_CENTER);
            setting.setStyle("""
               -fx-background-color: white;
               -fx-border-color: white;
               -fx-Pref-height:590px;
               
               """);







        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


