package com.ss.studysystem.UI.model;

import com.ss.studysystem.UI.layouts.config_background;
import com.ss.studysystem.UI.layouts.config_position;
import com.ss.studysystem.UI.logic.switch_scene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;

public class sign_up {

    private static sign_up instance;

//    private AnchorPane root;
    private HBox root;
    private Node current_node;
    private Node sign_in_node;
    private Node details_node;
    private Stage stage;
    private Image image;
    private Background background_image;

    public sign_up() {
    }

    public static sign_up getInstance() {
        if (instance == null) {
            instance = new sign_up();
        }
        return instance;
    }

//    public AnchorPane getRoot() {
//        return root;
//    }
//
//    public void setRoot(AnchorPane root) {
//        this.root = root;
//    }


    public HBox getRoot() {
        return root;
    }

    public void setRoot(HBox root) {
        this.root = root;
    }

    public Node getCurrent_node() {
        return current_node;
    }

    public void setCurrent_node(Node current_node) {
        this.current_node = current_node;
    }

    public Node getSign_in_node() {
        return sign_in_node;
    }

    public void setSign_in_node(Node sign_in_node) {
        this.sign_in_node = sign_in_node;
    }

    public Node getDetails_node() {
        return details_node;
    }

    public void setDetails_node(Node details_node) {
        this.details_node = details_node;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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

    public class Controller {

        public Controller() {
        }

        switch_scene switcher = new switch_scene();
        sign_up here = sign_up.getInstance();
        
        public void initialize() {

            set_up_actions();

            if(image == null && background_image == null) {
                File img = new File("/Users/thantzinlin/Downloads/Mountain Sunset Wallpaper.jpg");

                here.setImage(new Image(img.toURI().toString()));
                here.setBackground_image(config_background.get_background_w_prop(here.getImage()));
            }

            root.setBackground(background_image);

//            Platform.runLater(() -> {
//                stage.widthProperty().addListener((ob, ov, nv) -> config_position.center_node(stage, current_node));
//                stage.heightProperty().addListener((ob, ov, nv) -> config_position.center_node(stage, current_node));
//            });

            root.setAlignment(Pos.CENTER);

        }

        //todo controller.sign_up
        public void set_up_actions() {

            Button proceed = (Button) sign_in_node.lookup("#sign_up");
            Button back_to_signin = (Button) details_node.lookup("#go_back");
            Button login = (Button) sign_in_node.lookup("#login_acc");

            if (proceed != null) {
                proceed.setOnAction(event -> switch_to_details(event));
            }

            if (back_to_signin != null) {
                back_to_signin.setOnAction(event -> return_to_signin(event));
            }

            //todo signup controller - 1
            if (login != null) {
                login.setOnAction(event -> login_acc(event));
            }
            //

        }

        //todo 1
        private void login_acc(ActionEvent event) {
            try {
                switcher.login_scene(event, stage);
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        //

        private void return_to_signin(ActionEvent event) {

            Platform.runLater(()->{
                root.getChildren().remove(current_node);
                here.current_node = sign_in_node;
                root.getChildren().add(current_node);

//                config_position.center_node(stage, current_node);

            });


        }

        private void switch_to_details(ActionEvent event) {

            Platform.runLater(()->{

                root.getChildren().remove(current_node);
                here.current_node = details_node;
                root.getChildren().add(current_node);

//                config_position.center_node(stage, current_node);

            });


        }
        //
    }
}
