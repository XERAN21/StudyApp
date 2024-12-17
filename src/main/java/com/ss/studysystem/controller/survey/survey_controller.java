package com.ss.studysystem.controller.survey;

import com.ss.studysystem.UI.model.survey_mdl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;

public class survey_controller {

    @FXML
    private Button skip;

    @FXML
    private HBox survey_view;

    private VBox q_root = new VBox();

    survey_mdl survey_cnf = survey_mdl.getInstance();

    private List<Node> pages = survey_cnf.getPages();

    @FXML
    void initialize(){
        try{
            URL path = getClass().getResource("/com/ss/studysystem/Fxml/SurveyDesign.fxml");
            Node question = FXMLLoader.load(path);

            pages.add(question);

            Pane p2 = new Pane();
            p2.setStyle("-fx-background-color: red; -fx-pref-height: 250px; -fx-pref-width: 200px;");

            pages.add(p2);

            URL btn_path = getClass().getResource("/com/ss/studysystem/Fxml/survey_buttons.fxml");
            Node btns = FXMLLoader.load(btn_path);

            q_root.getChildren().addAll(question, btns);

            survey_view.getChildren().add(q_root);
            q_root.setAlignment(Pos.CENTER);

            survey_cnf.setRoot(q_root);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
