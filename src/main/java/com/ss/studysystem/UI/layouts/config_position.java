package com.ss.studysystem.UI.layouts;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
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

    public static void center_stage(Stage owner, Stage modalStage, Scene modalScene){
        modalStage.setX((owner.getX() + owner.getWidth() / 2d) - (modalScene.getWidth() / 2d));
        modalStage.setY((owner.getY() + owner.getHeight() / 2d) - (modalScene.getHeight() / 2d));
    }

}
