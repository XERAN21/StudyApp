package com.ss.studysystem.controller.Homepage;

import com.ss.studysystem.UI.components.modal_builder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Home_ctrl {
    @FXML
    private FlowPane bottom_pane;

    @FXML
    private FlowPane middle_pane;

    @FXML
    private BorderPane root;

    @FXML
    private FlowPane top_pane;

    @FXML
    private VBox vbox;



    @FXML
    void initialize(){
        try{
            URL path = getClass().getResource("/com/ss/studysystem/Fxml/Home page/recentused.fxml");
            FXMLLoader loader = new FXMLLoader(path);
            Node Recent = loader.load();
            top_pane.getChildren().add(Recent);

            URL path1 = getClass().getResource("/com/ss/studysystem/Fxml/Home page/pinfile.fxml");
            FXMLLoader loader1= new FXMLLoader(path1);
            Node pin_file = loader1.load();
            top_pane.getChildren().add(pin_file);



            URL path2 = getClass().getResource("/com/ss/studysystem/Fxml/Home page/addfile.fxml");
            FXMLLoader loader2 = new FXMLLoader(path2);
            Node add_file = loader2.load();
            top_pane.getChildren().add(add_file);

            URL path3 = getClass().getResource("/com/ss/studysystem/Fxml/Home page/date.fxml");
            FXMLLoader loader3 = new FXMLLoader(path3);
            Node calendar = loader3.load();
            top_pane.getChildren().add(calendar);

            URL path4 = getClass().getResource("/com/ss/studysystem/Fxml/ToDoList.fxml");
            FXMLLoader loader4 = new FXMLLoader(path4);
            Region to_do_list= loader4.load();
            middle_pane.getChildren().add(to_do_list);

            Platform.runLater(()->{
                try {
                    URL path5 = getClass().getResource("/com/ss/studysystem/Fxml/Home page/note_v2.fxml");
                    FXMLLoader loader5 = new FXMLLoader(path5);
                    Node note= loader5.load();
                    middle_pane.getChildren().add(note);
                    HBox.setHgrow(note, Priority.ALWAYS);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });


            URL path6 = getClass().getResource("/com/ss/studysystem/Fxml/Home page/join.fxml");
            FXMLLoader loader6 = new FXMLLoader(path6);
            Region classroom= loader6.load();
            bottom_pane.getChildren().add(classroom);

            URL path7 = getClass().getResource("/com/ss/studysystem/Fxml/Home page/addfriend.fxml");
            FXMLLoader loader7 = new FXMLLoader(path7);
            Node addfri= loader7.load();
           // root.setRight(addfri);

            HBox.setHgrow(to_do_list, Priority.ALWAYS);
            HBox.setHgrow(classroom, Priority.ALWAYS);



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



