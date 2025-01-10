package com.ss.studysystem.controller.classroom.quiz;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class quiz_input_view {
    @FXML
    private VBox quiz_root;

    @FXML
    void initialize() throws Exception {
        Platform.runLater(() -> {

            try {
                URL view_path = getClass().getResource("/com/ss/studysystem/Fxml/quiz/input_question.fxml");
                Region view_region = new FXMLLoader(view_path).load();
                quiz_root.getChildren().add(view_region);

                URL aview_path = getClass().getResource("/com/ss/studysystem/Fxml/quiz/long_answer_view.fxml");
                Region aview_region = new FXMLLoader(aview_path).load();
                quiz_root.getChildren().add(aview_region);


                URL iview_path = getClass().getResource("/com/ss/studysystem/Fxml/quiz/input_answer.fxml");
                Region iview_region = new FXMLLoader(iview_path).load();
                quiz_root.getChildren().add(iview_region);

                URL pview_path = getClass().getResource("/com/ss/studysystem/Fxml/quiz/progress_bar.fxml");
                Region pview_region = new FXMLLoader(pview_path).load();
                quiz_root.getChildren().add(pview_region);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
