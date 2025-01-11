package com.ss.studysystem.UI.misc;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.ss.studysystem.UI.utils.png_metadata_handler.read_png_metadata;

public class profile_pattern {

    public static File imageToTempFile(Image image) throws IOException {
        BufferedImage bufferedImage = javafxImageToBufferedImage(image);

        File tempFile = File.createTempFile("temp_image", ".png");

        ImageIO.write(bufferedImage, "png", tempFile);

        return tempFile;
    }

    private static BufferedImage javafxImageToBufferedImage(Image fxImage) {
        int width = (int) fxImage.getWidth();
        int height = (int) fxImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int argb = fxImage.getPixelReader().getArgb(x, y);
                bufferedImage.setRGB(x, y, argb);
            }
        }

        return bufferedImage;
    }

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
