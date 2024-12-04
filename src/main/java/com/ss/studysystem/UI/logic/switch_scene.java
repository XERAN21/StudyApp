package com.ss.studysystem.UI.logic;

import com.ss.studysystem.UI.layouts.config_position;
import com.ss.studysystem.UI.model.login;
import com.ss.studysystem.UI.model.sign_up;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;

public class switch_scene {

    public void sign_up_scene(ActionEvent event, Stage mainStage) {

        sign_up sign_up_config = sign_up.getInstance();
        sign_up.Controller sign_up_ctrl = sign_up_config.new Controller();

        Task<Parent> load_scene = new Task<>() {
            @Override
            protected Parent call() throws Exception {

                Scene sc = mainStage.getScene();

                double w = sc.getWidth();
                double h = sc.getHeight();

                URL signin_path = getClass().getResource("/com/ss/studysystem/Fxml/sign_up.fxml");
                FXMLLoader signin_loader = new FXMLLoader(signin_path);
                sign_up_config.setSign_in_node(signin_loader.load());

                URL details_path = getClass().getResource("/com/ss/studysystem/Fxml/sign_up_details.fxml");
                FXMLLoader details_loader = new FXMLLoader(details_path);
                sign_up_config.setDetails_node(details_loader.load());

                sign_up_config.setCurrent_node(sign_up_config.getSign_in_node());

                //AnchorPane root = new AnchorPane();
                HBox root = new HBox();
                root.setPrefWidth(w);
                root.setPrefHeight(h);

                root.getChildren().add(sign_up_config.getCurrent_node());
                sign_up_config.setRoot(root);

                return root;

            }
        };

        load_scene.setOnSucceeded(e -> {

            try {

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Parent root = load_scene.getValue();

                Scene sc = new Scene(root);

                stage.setScene(sc);

                stage.show();

                stage.setMinWidth(800);
                stage.setMinHeight(600);

                sign_up_config.setStage(stage);

                //Platform.runLater(() -> config_position.center_node(stage, sign_up_config.getCurrent_node()));

                sign_up_ctrl.initialize();


            } catch (Exception err) {

                System.out.println("Here");

                err.printStackTrace();
            }

        });

        load_scene.setOnFailed(e -> {
            Throwable throwable = load_scene.getException();
            throwable.printStackTrace();
        });

        new Thread(load_scene).start();
    }

    public void login_scene(ActionEvent event, Stage mainStage) {

        login login_config = login.getInstance();

        Task<Parent> load_scene = new Task<>() {
            @Override
            protected Parent call() throws Exception {

                URL login_path = getClass().getResource("/com/ss/studysystem/Fxml/login.fxml");
                FXMLLoader login_loader = new FXMLLoader(login_path);

                Scene sc = mainStage.getScene();

                double w = sc.getWidth();
                double h = sc.getHeight();

                AnchorPane root = new AnchorPane();
                root.getChildren().add(login_loader.load());
                root.setPrefWidth(w);
                root.setPrefHeight(h);

                root.setBackground(login_config.getBackground_image());

                return root;

            }
        };

        load_scene.setOnSucceeded(e -> {

            try {

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Parent root = load_scene.getValue();
                Scene sc = new Scene(root);


                stage.setScene(sc);

                stage.show();

                stage.setMinWidth(800);
                stage.setMinHeight(600);

                Node node = root.getChildrenUnmodifiable().get(0);

                config_position.center_node(stage, node);

                Platform.runLater(() -> {
                    stage.widthProperty().addListener((ob, ov, nv) -> config_position.center_node(stage, node));
                    stage.heightProperty().addListener((ob, ov, nv) -> config_position.center_node(stage, node));
                });

            } catch (Exception err) {
                err.printStackTrace();
            }

        });

        load_scene.setOnFailed(e -> {
            Throwable throwable = load_scene.getException();
            throwable.printStackTrace();
        });

        new Thread(load_scene).start();
    }


}
