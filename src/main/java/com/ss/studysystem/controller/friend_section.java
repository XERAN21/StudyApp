package com.ss.studysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class friend_section {

    @FXML
    private ImageView group_photo;

    @FXML
    private Label title;

    @FXML
    private VBox friend_view;

    @FXML
    void initialize(){

    }

    public void setGroup_photo(ImageView group_photo){
        this.group_photo = group_photo;
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public Label getTitle() {
        return title;
    }

    public ImageView getGroup_photo() {
        return group_photo;
    }

    public void setFriend_view(Node friend_view){
        this.friend_view.getChildren().add(friend_view);
    }
}
