package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Notes {

    @FXML
    private Label tilte;

    @FXML
    private Button btnnote;

    @FXML
    private Pane add_note_section;

    @FXML
    private VBox note_container;

    @FXML
    void initialize(){
        btnnote.setOnAction(this::add_note);
        note_container = new VBox();
        add_note_section.getChildren().add(note_container);
    }

    @FXML
    void add_note(ActionEvent event) {
        TextArea addtext = new TextArea();
        addtext.setPromptText("Enter text here");
        addtext.setWrapText(true);

        addtext.setPrefSize(160,10);
        addtext.setStyle("-fx-border-color: transparent;");


        note_container.getChildren().add(addtext);
    }

}

