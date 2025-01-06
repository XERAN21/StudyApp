package com.ss.studysystem.UI.misc;

import javafx.animation.*;
import javafx.scene.Parent;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class modal_animations {

    public static ParallelTransition open_modal_w_size(Parent view, double w, double h){
        Scale scale = new Scale(0.5, 0.5, w / 2, h / 2);
        view.getTransforms().add(scale);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(100), view);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        Timeline scaleIn = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(scale.xProperty(), 0.5),
                        new KeyValue(scale.yProperty(), 0.5)),
                new KeyFrame(Duration.millis(150),
                        new KeyValue(scale.xProperty(), 1.0),
                        new KeyValue(scale.yProperty(), 1.0))
        );

        ParallelTransition animation = new ParallelTransition(fadeIn, scaleIn);
        animation.setInterpolator(Interpolator.EASE_OUT);

        return animation;
    }

    public static ParallelTransition close_modal_w_size(Parent view, double w, double h){
        Scale scale = new Scale(1, 1, w/2 , h/2 );
        view.getTransforms().add(scale);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(100), view);
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.0);

        Timeline scaleIn = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(scale.xProperty(), 1),
                        new KeyValue(scale.yProperty(), 1)),
                new KeyFrame(Duration.millis(150),
                        new KeyValue(scale.xProperty(), 0.25),
                        new KeyValue(scale.yProperty(), 0.25))
        );

        ParallelTransition animation = new ParallelTransition(fadeIn, scaleIn);
        animation.setInterpolator(Interpolator.EASE_IN);

        return animation;
    }
}
