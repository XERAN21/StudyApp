package com.ss.studysystem.controller.startup;

import com.ss.studysystem.UI.misc.modal_animations;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class app_cnf {

    @FXML
    private Button close;

    @FXML
    private Button database;

    @FXML
    private BorderPane root;

    @FXML
    private Button websocket;

    @FXML
    void initialize(){
        close.setOnAction(this::close_stage);
    }

    void close_stage(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        ParallelTransition closeAnimation =  modal_animations.close_modal_w_size(stage.getScene().getRoot(), stage.getWidth(), stage.getHeight());
        closeAnimation.setOnFinished(e -> stage.close());
        closeAnimation.play();
    }
}
