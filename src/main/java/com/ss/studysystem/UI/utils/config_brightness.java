package com.ss.studysystem.UI.utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.stage.Stage;

public class config_brightness {

    private static final ColorAdjust DIM_EFFECT = new ColorAdjust();

    static {
        DIM_EFFECT.setBrightness(-0.5); // Predefine dimming effect
    }

    public static void applyDimmingEffect(Stage stage) {
        if (stage != null) {
            Scene currentScene = stage.getScene();
            if (currentScene != null && currentScene.getRoot() != null) {
                currentScene.getRoot().setEffect(DIM_EFFECT);
            }
        }
    }

    public static void removeDimmingEffect(Stage stage) {
        if (stage != null) {
            Scene currentScene = stage.getScene();
            if (currentScene != null && currentScene.getRoot() != null) {
                currentScene.getRoot().setEffect(null); // Remove effect
            }
        }
    }

    public static void applyDimmingEffectFromEvent(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        applyDimmingEffect(stage);
    }

    public static void removeDimmingEffectFromEvent(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        removeDimmingEffect(stage);
    }
}
