package com.ss.studysystem.UI.components;

import com.ss.studysystem.UI.layouts.config_position;
import com.ss.studysystem.UI.utils.config_brightness;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class modal_builder {

    public static Stage build_modal(Stage owner, Parent view){

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(owner);
        modalStage.initStyle(StageStyle.UTILITY);

        Scene modalScene = new Scene(view, 425, 535);
        modalStage.setScene(modalScene);
        modalScene.setFill(Color.TRANSPARENT);

       // modalStage.setResizable(false);

        return modalStage;

    }


    public static Stage build_fixed_modal(Stage owner, Parent view, double w, double h){

        Platform.runLater(()->config_brightness.applyDimmingEffect(owner));

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(owner);
        modalStage.initStyle(StageStyle.TRANSPARENT);

        Scene modalScene = new Scene(view, w, h);
        modalStage.setScene(modalScene);
        modalScene.setFill(Color.TRANSPARENT);

        // modalStage.setResizable(false);
        config_position.center_stage(owner, modalStage, modalScene);

        modalStage.setOnHidden(hid->Platform.runLater(()->config_brightness.removeDimmingEffect(owner)));



        return modalStage;

    }

    public static Stage build_fixed_modal(Stage owner, Parent view){

        Platform.runLater(()->config_brightness.applyDimmingEffect(owner));

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(owner);
        modalStage.initStyle(StageStyle.TRANSPARENT);

        Scene modalScene = new Scene(view);
        modalStage.setScene(modalScene);
        modalScene.setFill(Color.TRANSPARENT);

        // modalStage.setResizable(false);
        config_position.center_stage(owner, modalStage, modalScene);

        return modalStage;

    }

}
