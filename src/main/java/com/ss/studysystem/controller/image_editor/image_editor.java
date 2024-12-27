package com.ss.studysystem.controller.image_editor;

import com.ss.studysystem.UI.utils.file_size;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.File;

public class image_editor {

    @FXML
    private Button apply_img;

    @FXML
    private Button cancel;

    @FXML
    private StackPane image_stack;

    @FXML
    private Slider zoom_slider;

    @FXML
    private ImageView image;


//    @FXML
//    private VBox hidden_elements;

    double initx;
    double inity;
    int height;
    int width;
    File path;
    double offSetX, offSetY, zoomlvl;

    public void setPath(File path) {
        this.path = path;
    }

    @FXML
    void initialize() {

        cancel.setOnAction(e -> cancel_img());

        Platform.runLater(() -> {

            //File imgFile = new File(path);

            Image source = new Image(path.toURI().toString());

            image.setImage(source);
            double ratio = source.getWidth() / source.getHeight();

            if (404 / ratio < 404) {
                width = 404;
                height = (int) (404 / ratio);
            } else if (404 * ratio < 404) {
                height = 404;
                width = (int) (404 * ratio);
            } else {
                height = 404;
                width = 404;
            }

            image.setPreserveRatio(false);
            image.setFitWidth(width);
            image.setFitHeight(height);
            height = (int) source.getHeight();
            width = (int) source.getWidth();


            offSetX = width / 2;
            offSetY = height / 2;

            Slider Hscroll = new Slider();
            Hscroll.setMin(0);
            Hscroll.setMax(width);
            Hscroll.setMaxWidth(image.getFitWidth());
            Hscroll.setMinWidth(image.getFitWidth());
            Hscroll.setTranslateY(-20);
            Slider Vscroll = new Slider();
            Vscroll.setMin(0);
            Vscroll.setMax(height);
            Vscroll.setMaxHeight(image.getFitHeight());
            Vscroll.setMinHeight(image.getFitHeight());
            Vscroll.setOrientation(Orientation.VERTICAL);
            Vscroll.setTranslateX(-20);

            Hscroll.setVisible(false);
            Vscroll.setVisible(false);
            Hscroll.valueProperty().addListener(e -> {
                offSetX = Hscroll.getValue();
                zoomlvl = zoom_slider.getValue();
                double newValue = (double) ((int) (zoomlvl * 10)) / 10;
                if (offSetX < (width / newValue) / 2) {
                    offSetX = (width / newValue) / 2;
                }
                if (offSetX > width - ((width / newValue) / 2)) {
                    offSetX = width - ((width / newValue) / 2);
                }

                image.setViewport(new Rectangle2D(offSetX - ((width / newValue) / 2), offSetY - ((height / newValue) / 2), width / newValue, height / newValue));
            });
            Vscroll.valueProperty().addListener(e -> {
                offSetY = height - Vscroll.getValue();
                zoomlvl = zoom_slider.getValue();
                double newValue = (double) ((int) (zoomlvl * 10)) / 10;
                if (offSetY < (height / newValue) / 2) {
                    offSetY = (height / newValue) / 2;
                }
                if (offSetY > height - ((height / newValue) / 2)) {
                    offSetY = height - ((height / newValue) / 2);
                }
                image.setViewport(new Rectangle2D(offSetX - ((width / newValue) / 2), offSetY - ((height / newValue) / 2), width / newValue, height / newValue));
            });

            zoom_slider.valueProperty().addListener(e -> {
                zoomlvl = zoom_slider.getValue();
                double newValue = (double) ((int) (zoomlvl * 10)) / 10;
                if (offSetX < (width / newValue) / 2) {
                    offSetX = (width / newValue) / 2;
                }
                if (offSetX > width - ((width / newValue) / 2)) {
                    offSetX = width - ((width / newValue) / 2);
                }
                if (offSetY < (height / newValue) / 2) {
                    offSetY = (height / newValue) / 2;
                }
                if (offSetY > height - ((height / newValue) / 2)) {
                    offSetY = height - ((height / newValue) / 2);
                }
                Hscroll.setValue(offSetX);
                Vscroll.setValue(height - offSetY);
                image.setViewport(new Rectangle2D(offSetX - ((width / newValue) / 2), offSetY - ((height / newValue) / 2), width / newValue, height / newValue));
            });
            image_stack.setCursor(Cursor.OPEN_HAND);
            image.setOnMousePressed(e -> {
                initx = e.getSceneX();
                inity = e.getSceneY();
                image_stack.setCursor(Cursor.CLOSED_HAND);
            });
            image.setOnMouseReleased(e -> {
                image_stack.setCursor(Cursor.OPEN_HAND);
            });
            image.setOnMouseDragged(e -> {
                Hscroll.setValue(Hscroll.getValue() + (initx - e.getSceneX()));
                Vscroll.setValue(Vscroll.getValue() - (inity - e.getSceneY()));
                initx = e.getSceneX();
                inity = e.getSceneY();
            });

            zoom_slider.setMax(4);
            zoom_slider.setMin(1);

            VBox img_clip = new VBox();
            img_clip.setPrefWidth(width);
            img_clip.setPrefHeight(height);
            setClipViewport(img_clip);
            //image_stack.getChildren().add(img_clip);
        });
    }

    private void setClipViewport(Region region) {
        Platform.runLater(() -> {

//            double rec_width = region.getWidth();
//            double rec_height = region.getHeight();


            double rec_width = image.getFitWidth();
            double rec_height = image.getFitHeight();

            System.out.println(rec_width);

            Rectangle rect = new Rectangle(0, 0, rec_width, rec_height);
            Circle circ = new Circle(rec_width / 2, rec_height / 2, Math.min(rec_width, rec_height) / 2);

            Circle border_circ = new Circle(rec_width / 2, rec_height / 2, Math.min(rec_width, rec_height) / 2);
            border_circ.setFill(Color.TRANSPARENT);
            border_circ.setStroke(Color.WHITE);
            border_circ.setStrokeWidth(3.5);
            border_circ.setMouseTransparent(true);
            image_stack.getChildren().add(border_circ);


            Shape clip = Shape.subtract(rect, circ);
            clip.setFill(Color.BLACK);
            clip.setOpacity(0.65);
            image_stack.getChildren().add(clip);

           // region.setClip(clip);
        });
    }

    private void cancel_img() {
        try {

            path = null;
            image = null;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/profile_img/image_selector.fxml"));
            Parent load_view = loader.load();

            Stage stage = (Stage) cancel.getScene().getWindow();
            Scene sc = new Scene(load_view, Color.TRANSPARENT);
            stage.setScene(sc);

            System.gc();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void apply() {

    }
}
