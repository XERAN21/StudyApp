package com.ss.studysystem.controller.SoloStudy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;



import java.net.URL;

public class Solo_Study_Ctrl {

    @FXML
    private HBox hbox;

    @FXML
    private ScrollPane scroll;


    @FXML
    private BorderPane root;

    @FXML
    void initialize(){
        try{
            URL path = getClass().getResource("/com/ss/studysystem/Fxml/SoloStudy/Heading.fxml");
            FXMLLoader loader = new FXMLLoader(path);
            Node heading = loader.load();
            root.setLeft(heading);
            heading.setStyle("""
                    -fx-padding: 10px;
                    
                    
                    
                    """);

            URL path1 = getClass().getResource("/com/ss/studysystem/Fxml/Clock.fxml");
            FXMLLoader loader1 =new FXMLLoader(path1);
            Node function_bar=loader1.load();
            hbox.getChildren().add(function_bar);

            URL path2 = getClass().getResource("/com/ss/studysystem/Fxml/Recent_file.fxml");
            FXMLLoader loader2 =new FXMLLoader(path2);
            Node file_section=loader2.load();
            hbox.getChildren().add(file_section);


            HBox.setHgrow(file_section, Priority.ALWAYS);
            hbox.setSpacing(10);

            URL path3 = getClass().getResource("/com/ss/studysystem/Fxml/GoldenNav.fxml");
            FXMLLoader loader3 =new FXMLLoader(path3);
            Node clock=loader3.load();
            hbox.getChildren().add(clock);






        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



