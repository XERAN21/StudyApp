package com.ss.studysystem.controller.image_editor;

import com.ss.studysystem.UI.misc.modal_animations;
import com.ss.studysystem.UI.utils.file_size;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.reactfx.util.LL;

import java.io.File;
import java.util.function.Consumer;

public class upload_img {

    @FXML
    private Button close_modal;

    @FXML
    private Button upload_img;

    @FXML
    private VBox upload_img_pane;

    Consumer<Image> on_image;

    public void setOn_image(Consumer<Image> on_image) {
        this.on_image = on_image;
    }

    File path;

    @FXML
    void initialize() {
        upload_img.setOnAction(e -> open_file());
        upload_img_pane.setOnMouseClicked(e -> open_file());

        close_modal.setOnAction(e -> close());
    }

    void open_file() {
        Stage s = (Stage) close_modal.getScene().getWindow();
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter img = new FileChooser.ExtensionFilter("img", "*.jpg", "*.png");
        FileChooser.ExtensionFilter png = new FileChooser.ExtensionFilter("png", "*.png");
        FileChooser.ExtensionFilter jpg = new FileChooser.ExtensionFilter("jpg", "*.jpg");

        fc.getExtensionFilters().addAll(img, png, jpg);
        String abs_path = fc.showOpenDialog(s).getAbsolutePath();



        if (abs_path == null) {
            //todo throws error
            throw new RuntimeException("File not found");
        }

        path = new File(abs_path);

        System.out.println(file_size.getFileSizeMegaBytes(path));
        if(file_size.getFileSizeMegaBytes(path) > 15){
            //todo thows error -> file too big
            throw  new RuntimeException("File too big");
        }
        edit_image();

    }

    void edit_image() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/profile_img/edit_image.fxml"));
            Parent load_view = loader.load();
            image_editor img_edtr = loader.getController();

            img_edtr.setPath(path);

            img_edtr.setOn_img(on_image);

            Stage stage = (Stage) close_modal.getScene().getWindow();
            Scene sc = new Scene(load_view, Color.TRANSPARENT);
            stage.setScene(sc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void close() {
        Stage stage = (Stage) close_modal.getScene().getWindow();
        Parent view = stage.getScene().getRoot();
        ParallelTransition closeAnimation =  modal_animations.close_modal_w_size(view, stage.getWidth(), stage.getHeight());
        closeAnimation.setOnFinished(e -> stage.close());

        closeAnimation.play();
    }
}
