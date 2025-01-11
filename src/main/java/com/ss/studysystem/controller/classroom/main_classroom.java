package com.ss.studysystem.controller.classroom;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.net.URL;

public class main_classroom {

    @FXML
    private BorderPane main_root;

    Region current;

    @FXML
    void initialize() throws Exception {
        URL nav_path = getClass().getResource("/com/ss/studysystem/Fxml/classroom/nav_bar.fxml");
        Region nav_node = new FXMLLoader(nav_path).load();

        URL view_path = getClass().getResource("/com/ss/studysystem/Fxml/classroom/class_home.fxml");
        FXMLLoader view_load =  new FXMLLoader(view_path);
        Region view_region = view_load.load();
        class_home class_ctrl = view_load.getController();
        class_ctrl.set_text("Your Classes");
        main_root.setTop(nav_node);

        current = view_region;
        VBox vbox = new VBox(current);
        VBox.setVgrow(view_region, Priority.ALWAYS);
        main_root.setCenter(vbox);

        class_ctrl.setUpdate_view(update->{
            if(update!= null){
                vbox.getChildren().remove(current);
                current = update;
                VBox.setVgrow(current, Priority.ALWAYS);
                vbox.getChildren().add(current);
            }
        });

    }
}
