package com.ss.studysystem.UI.layouts;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class config_background {

    public static Background get_background_w_prop(Image bg){
        BackgroundSize size = new BackgroundSize(
                BackgroundSize.AUTO,     //width
                BackgroundSize.AUTO,     //height
                false,                   //width is not proportional
                false,                   //height is not proportional
                true,                    //entire region
                true                     //no white spaces
        );

        BackgroundImage bg_img = new BackgroundImage(
                bg,
                BackgroundRepeat.NO_REPEAT,    //horizontally
                BackgroundRepeat.NO_REPEAT,    //vertically
                BackgroundPosition.CENTER,     //pos
                size
        );

        return new Background(bg_img);
    }
}
