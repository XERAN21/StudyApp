package com.ss.studysystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class MainPage extends Application {
    private GridPane root = new GridPane();
    private final MenuBar mb = new MenuBar();
    private final MenuItem home = new MenuItem("Home");
    private final MenuItem solo_study = new MenuItem("Solo Study");
    private final MenuItem group_study = new MenuItem("Group Study");
    private final MenuItem Classroom = new MenuItem("Classroom");
    private final MenuItem Leader_Board = new MenuItem("Leader Board");
    private final MenuItem Study_Bot = new MenuItem("Study Bot");
    private Label lb = new Label("Hi Username");

    @Override
    public void start(Stage stage) throws Exception {
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.setTitle("Study App");
        stage.show();

        root.getChildren().add(lb);
        lb.setStyle("-fx-font-family: 'JetBrains Mono'; -fx-font-size: 14;");

    }
}
