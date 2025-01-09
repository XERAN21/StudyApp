package com.ss.studysystem.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class timer_controller {

    @FXML
    private Label Timer;

    @FXML
    private Button btn;

    Timeline time;
    int second = 0;

    @FXML
    void initialize() {
        time = new Timeline(new KeyFrame(Duration.seconds(1),e->timer()));
        time.setCycleCount(Animation.INDEFINITE);
        btn.setOnAction(this::stop);
    }

    @FXML
    void stop(ActionEvent event) {
        time.stop();
    }

    private void timer() {
        second++;
        int hour = second/3600;
        int min = (second % 3600) / 60;
        int sec = second % 60;
        Timer.setText(String.format("%02d:%02d:%02d", hour,min,sec));
    }

}
