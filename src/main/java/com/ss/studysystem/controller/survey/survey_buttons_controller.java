package com.ss.studysystem.controller.survey;

import com.ss.studysystem.UI.model.survey_mdl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.List;

public class survey_buttons_controller {

    survey_mdl survey_cnf = survey_mdl.getInstance();

    private List<Node> pages;
    private VBox q_root;

    int current_index = 0;

    @FXML
    private Button back_btn;

    @FXML
    private Button next_btn;

    @FXML
    void initialize(){

        back_btn.setDisable(true);

        next_btn.setOnAction(this::next_pagae);
        back_btn.setOnAction(this::prev_pagae);
        Platform.runLater(()->{
           pages = survey_cnf.getPages();
            q_root = survey_cnf.getRoot();
        });
    }

    @FXML
    void next_pagae(ActionEvent event){

        q_root.getChildren().remove(pages.get(current_index));
        current_index++;
        q_root.getChildren().add(0,pages.get(current_index));

        if(current_index == pages.size()-1) next_btn.setDisable(true);
        if(current_index > 0 && back_btn.isDisable())  back_btn.setDisable(false);

    }

    @FXML
    void prev_pagae(ActionEvent event){

        q_root.getChildren().remove(pages.get(current_index));
        current_index--;
        q_root.getChildren().add(0,pages.get(current_index));

        if(current_index == 0) {
            back_btn.setDisable(true);
        }

        if(current_index >= 0 && next_btn.isDisable() ){
            next_btn.setDisable(false);
        }

    }

}
