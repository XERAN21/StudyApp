package com.ss.studysystem.controller.Profile;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;

public class theme_ctr {

    @FXML
    private ScrollPane prof_view;

    @FXML
    private VBox setting;
    @FXML
    public void initialize(){
        try{
            URL path1 = getClass().getResource("/com/ss/studysystem/Fxml/quiz/apperence.fxml");
            VBox info = FXMLLoader.load(path1);




            info.prefWidthProperty().bind(prof_view.widthProperty().subtract(125));


            setting.setSpacing(40);
            setting.setStyle("""
               -fx-background-color: white;
               -fx-border-color: white;
               """);

            setting.getChildren().addAll(info);


            URL css_path = getClass().getResource("/com/ss/studysystem/css/scroll_st.css");
            prof_view.getStylesheets().add(css_path.toExternalForm());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

