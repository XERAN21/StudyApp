package com.ss.studysystem.UI.model;

import com.ss.studysystem.UI.layouts.config_background;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;

import java.io.File;

public class login {

    private static login instance;

    private Image image;
    private Background background_image;

    public static login getInstance() {
        if (instance == null) {
            instance = new login();
            instance.initialize_image();
        }
        return instance;
    }

    public void initialize_image() {

        if (image == null && background_image == null) {
            File img = new File("/Users/thantzinlin/Downloads/Collage Landscapes Composition.jpg");
            this.setImage(new Image(img.toURI().toString()));
            this.setBackground_image(config_background.get_background_w_prop(this.getImage()));
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Background getBackground_image() {
        return background_image;
    }

    public void setBackground_image(Background background_image) {
        this.background_image = background_image;
    }
}
