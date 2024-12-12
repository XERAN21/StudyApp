package com.ss.studysystem.UI.utils;

import com.ss.studysystem.UI.layouts.status_indicators;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class indicator_animation {

    public void animate_light_effect(StackPane root, status_indicators type) {

        Rectangle lightEffect = new Rectangle();
        lightEffect.setWidth(root.getWidth());
        lightEffect.setHeight(root.getHeight());

        lightEffect.setFill(Color.valueOf(type.getValue()));


        lightEffect.setTranslateX(-root.getWidth());

        root.getChildren().add(lightEffect);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), lightEffect);
        transition.setFromX(-root.getWidth());
        transition.setToX(root.getWidth());
        transition.setOnFinished(event -> root.getChildren().remove(lightEffect));

        Platform.runLater(transition::play);
    }

    public ParallelTransition animate_remove_node_squash_effect(StackPane node){
//
//        ScaleTransition transition = new ScaleTransition(Duration.seconds(0.8), node);
//
//        transition.setToY(0);
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), node);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(500), node);
        scaleDown.setFromX(1.0);
        scaleDown.setFromY(1.0);
        scaleDown.setToX(0.8);
        scaleDown.setToY(0.8);

        TranslateTransition slideUp = new TranslateTransition(Duration.millis(500), node);
        slideUp.setByY(-20);

        return new ParallelTransition(fadeOut, scaleDown, slideUp);

//        transition.setOnFinished(event->root.getChildren().remove(node));
//        Platform.runLater(transition::play);

    }
}
