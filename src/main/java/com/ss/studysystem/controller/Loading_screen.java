package com.ss.studysystem.controller;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Loading_screen {

    @FXML
    private Label label;

    @FXML
    private Circle loading_circle;

    @FXML
    void initialize(){
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(loading_circle);
        rotateTransition.setDuration(Duration.seconds(2));
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);

        rotateTransition.play();
    }

}
