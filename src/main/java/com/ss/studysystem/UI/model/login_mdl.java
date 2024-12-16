package com.ss.studysystem.UI.model;

import com.ss.studysystem.UI.layouts.config_background;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;

import java.io.File;

public class login_mdl {

    private static login_mdl instance;

    private Image image;
    private Background background_image;

    public static login_mdl getInstance() {
        if (instance == null) {
            instance = new login_mdl();
            if (instance.getImage() == null && instance.getBackground_image() == null)
                instance.initialize_image();
        }
        return instance;
    }

    public void initialize_image() {
        File img = new File("/Users/thantzinlin/Downloads/Collage Landscapes Composition.jpg");
        this.setImage(new Image(img.toURI().toString()));
        this.setBackground_image(config_background.get_background_w_prop(this.getImage()));

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

    public void dispose() {
        if (image != null) {
            image.cancel();
            image = null;
        }
        background_image = null;
        instance = null;
    }
}
