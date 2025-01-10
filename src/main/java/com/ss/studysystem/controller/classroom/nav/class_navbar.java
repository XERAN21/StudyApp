package com.ss.studysystem.controller.classroom.nav;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.net.URL;

public class class_navbar {

    @FXML
    private Button compose;

    @FXML
    private Button home;

    @FXML
    private Button members;

    @FXML
    private Button summary;

    @FXML
    private ScrollPane nav_scroll;

    @FXML
    private HBox nav_box;

    @FXML
    void initialize() throws Exception{
        URL path = getClass().getResource("/com/ss/studysystem/css/scroll_round.css");
        if (path != null)
            nav_scroll.getStylesheets().add(path.toExternalForm());

        nav_box.prefWidthProperty().bind(nav_scroll.widthProperty().subtract(10));
    }
}
