package com.ss.studysystem;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.stage.StageStyle;

import java.net.URL;

public class MainPage extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(final Stage primaryStage) throws Exception {

        URL path = getClass().getResource("/com/ss/studysystem/Fxml/startup/startup_loader.fxml");
        FXMLLoader loader = new FXMLLoader(path);
        Parent start_up = loader.load();

        Scene sc = new Scene(start_up);
        sc.setFill(Color.TRANSPARENT);

        primaryStage.setScene(sc);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setResizable(false);
        primaryStage.show();

        sc.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        sc.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

    }
}
