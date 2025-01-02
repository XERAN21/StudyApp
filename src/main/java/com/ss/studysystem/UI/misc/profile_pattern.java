package com.ss.studysystem.UI.misc;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;

import static com.ss.studysystem.UI.utils.png_metadata_handler.read_png_metadata;

public class profile_pattern {

    public static ImagePattern generate_profile_image(File inputFile) {
        try {
            ImageView imageView = new ImageView();
            StackPane root = new StackPane();

            Image img = new Image(inputFile.toURI().toString());
            imageView.setImage(img);

            String zoomKey = "zoom_level";
            String xKey = "offset_x";
            String yKey = "offset_y";

            String zoomLvl = read_png_metadata(inputFile, zoomKey);
            String offsetXStr = read_png_metadata(inputFile, xKey);
            String offsetYStr = read_png_metadata(inputFile, yKey);

            double zoomLevel = Double.parseDouble(zoomLvl);
            double offsetX = Double.parseDouble(offsetXStr);
            double offsetY = Double.parseDouble(offsetYStr);

            double newWidth = img.getWidth() / zoomLevel;
            double newHeight = img.getHeight() / zoomLevel;

            double newOffsetX = offsetX - (newWidth / 2);
            double newOffsetY = offsetY - (newHeight / 2);

            imageView.setViewport(new Rectangle2D(newOffsetX, newOffsetY, newWidth, newHeight));

            Circle clipCircle = new Circle();
            clipCircle.setRadius(Math.min(newWidth, newHeight) / 2);
            clipCircle.setCenterX(newWidth / 2);
            clipCircle.setCenterY(newHeight / 2);
            imageView.setClip(clipCircle);

            return new ImagePattern(imageView.snapshot(null, null), 0, 0, 1, 1, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
