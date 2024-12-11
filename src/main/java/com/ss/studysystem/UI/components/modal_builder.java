package com.ss.studysystem.UI.components;

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


    public static Stage build_fixed_modal(Stage owner, Parent view, double h, double w){

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(owner);
        modalStage.initStyle(StageStyle.TRANSPARENT);

        Scene modalScene = new Scene(view, h, w);
        modalStage.setScene(modalScene);
        modalScene.setFill(Color.TRANSPARENT);

        // modalStage.setResizable(false);

        return modalStage;

    }

}
