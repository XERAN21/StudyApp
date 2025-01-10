package com.ss.studysystem.controller.main;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class main_view {

    @FXML
    private AnchorPane main_anchor;

    @FXML
    private BorderPane main_border;

    boolean hidden = false;

    Region current;


    @FXML
    void initialize() throws Exception {

        URL main_nav_path = getClass().getResource("/com/ss/studysystem/Fxml/main_nav/nav.fxml");
        FXMLLoader nav_loader = new FXMLLoader(main_nav_path);
        Region main_nav_node = nav_loader.load();
        main_nav nav_ctrl = nav_loader.getController();

        TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.3), main_nav_node);

        nav_ctrl.setOn_hidden(hid -> {
            hidden = hid;
            if (hid) {
                Platform.runLater(() -> {
                    main_border.getChildren().remove(main_nav_node);

                    main_anchor.getChildren().add(main_nav_node);

                    config_anchor_pos(main_nav_node);

                    main_nav_node.setTranslateX(0);

                    slideOut.setFromX(0);
                    slideOut.setToX(-main_nav_node.getWidth());
                    slideOut.setInterpolator(Interpolator.EASE_BOTH);
                    slideOut.setOnFinished(event -> {
                        // Optional: Remove the navbar from AnchorPane after sliding out if needed
                        // main_anchor.getChildren().remove(main_nav_node);
                    });

                    slideOut.play();
                });
            } else {
                Platform.runLater(() -> {

                    main_anchor.getChildren().remove(main_nav_node);
                    main_border.setLeft(main_nav_node);

                });

            }
        });

        nav_ctrl.setOn_change(change -> {
            Platform.runLater(()->{
                main_border.getChildren().remove(current);
                current = change;
                main_border.setCenter(current);

            });

        });

        main_border.setLeft(main_nav_node);

        main_anchor.setOnMouseMoved(event -> {
            double mouseX = event.getSceneX();
            double navWidth = main_nav_node.getWidth();

            if (hidden) {
                if (mouseX < 25 && main_nav_node.getTranslateX() < 0) {
                    slideOut.setFromX(-navWidth);
                    slideOut.setToX(0);
                    slideOut.setInterpolator(Interpolator.EASE_BOTH);
                    slideOut.play();
                }

                if (mouseX > navWidth && main_nav_node.getTranslateX() >= 0) {
                    slideOut.setFromX(0);
                    slideOut.setToX(-navWidth);
                    slideOut.setInterpolator(Interpolator.EASE_BOTH);
                    slideOut.play();
                }
            }
        });


        //todo Main Page
        FXMLLoader chat_ = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/chat_is_this_real/chat_main.fxml"));
        FXMLLoader class_ = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/classroom/classroom_view.fxml"));

        Platform.runLater(() -> {
            try {
                nav_ctrl.setParent_stage((Stage) main_border.getScene().getWindow());
                Region class_reg = class_.load();
                current = class_reg;
                main_border.setCenter(current);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    void nav_listener(boolean hid, Region node){
        if (hid) {
            TranslateTransition slideOut = new TranslateTransition(Duration.seconds(0.3), node);
            Platform.runLater(() -> {
                main_border.getChildren().remove(node);

                main_anchor.getChildren().add(node);

                config_anchor_pos(node);

                node.setTranslateX(0);

                slideOut.setFromX(0);
                slideOut.setToX(-node.getWidth());
                slideOut.setInterpolator(Interpolator.EASE_BOTH);
                slideOut.setOnFinished(event -> {
                    // Optional: Remove the navbar from AnchorPane after sliding out if needed
                    // main_anchor.getChildren().remove(main_nav_node);
                });

                slideOut.play();
            });
        } else {
            Platform.runLater(() -> {

                main_anchor.getChildren().remove(node);
                main_border.setLeft(node);

            });

        }
    }

    void config_anchor_pos(Region region) {
        AnchorPane.setTopAnchor(region, 0.0);
        AnchorPane.setBottomAnchor(region, 0.0);
    }
}
