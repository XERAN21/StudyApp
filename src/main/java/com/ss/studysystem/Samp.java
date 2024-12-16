package com.ss.studysystem;

import com.ss.studysystem.UI.model.login_mdl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Samp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final HBox root = new HBox();

    @Override
    public void start(Stage stage) throws IOException {

        login_mdl login_config = login_mdl.getInstance();

        URL path = getClass().getResource("/com/ss/studysystem/Fxml/login.fxml");
        FXMLLoader loader = new FXMLLoader(path);
        Node login_node = loader.load();
        root.getChildren().add(login_node);

        Scene sc = new Scene(root, 1080, 720);

        stage.setMinWidth(800);
        stage.setMinHeight(600);

        root.setAlignment(Pos.CENTER);

//        config_position.center_node(stage, login_node);
//        stage.widthProperty().addListener((ob, ov, nv) -> config_position.center_node(stage, login_node));
//        stage.heightProperty().addListener((ob, ov, nv) -> config_position.center_node(stage, login_node));

        root.setBackground(login_config.getBackground_image());

        stage.setScene(sc);
        stage.show();

    }
}
