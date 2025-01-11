package com.ss.studysystem.controller.SoloStudy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration; // Import the correct Duration class

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock_ctr {

    @FXML
    private Label timeLabel;

    public void initialize() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), // Use javafx.util.Duration
                        event -> {
                            LocalTime time = LocalTime.now();
                            timeLabel.setText(time.format(formatter));
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
