package com.ss.studysystem.controller.startup;

import com.ss.studysystem.UI.misc.modal_animations;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class app_cnf {

    @FXML
    private Button close;

    @FXML
    private Button database;

    @FXML
    private BorderPane root;

    @FXML
    private Button websocket;

    Node database_view;
    Node websocket_view;


    @FXML
    void initialize(){
        close.setOnAction(this::close_stage);
        Platform.runLater(()->load_fxml());
    }

    void close_stage(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        ParallelTransition closeAnimation =  modal_animations.close_modal_w_size(stage.getScene().getRoot(), stage.getWidth(), stage.getHeight());
        closeAnimation.setOnFinished(e -> stage.close());
        closeAnimation.play();
    }

    void load_fxml(){
        try {
            FXMLLoader db_loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/cnf_settings/config_database.fxml"));
            database_view = db_loader.load();
            //todo web
        }catch (Exception e){
            e.printStackTrace();
        }
        Platform.runLater(()->{
            if (database_view != null){
                root.setCenter(database_view);
            }
        });
    }
}
