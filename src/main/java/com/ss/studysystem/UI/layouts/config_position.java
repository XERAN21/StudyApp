package com.ss.studysystem.UI.layouts;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;

public class config_position {


    public static void center_node(Stage stage, Node node) {

        Platform.runLater(()->{
            double w = node.getLayoutBounds().getWidth();
            double h = node.getLayoutBounds().getHeight();

            node.setLayoutX(stage.getWidth() / 2 - w / 2);
            node.setLayoutY(stage.getHeight() / 2 - h / 2 - 25);
        });

    }

}
