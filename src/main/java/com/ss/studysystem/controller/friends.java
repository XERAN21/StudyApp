package com.ss.studysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class friends {

    @FXML
    private ImageView profile_img;

    @FXML
    private Label name;

    @FXML
    void initialize(){}

    public ImageView getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(ImageView profile_img) {
        this.profile_img = profile_img;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }
}
