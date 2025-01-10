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

    @FXML
    void initialize() throws Exception {
        URL nav_path = getClass().getResource("/com/ss/studysystem/Fxml/classroom/nav_bar.fxml");
        Region nav_node = new FXMLLoader(nav_path).load();

        URL view_path = getClass().getResource("/com/ss/studysystem/Fxml/quiz/quiz_input.fxml");
        Region view_region = new FXMLLoader(view_path).load();
        main_root.setTop(nav_node);

        VBox vbox = new VBox(view_region);
        vbox.setStyle("-fx-padding: 15px;");
        VBox.setVgrow(view_region, Priority.ALWAYS);
        main_root.setCenter(vbox);

    }
}