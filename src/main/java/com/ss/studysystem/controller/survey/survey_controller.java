package com.ss.studysystem.controller.survey;

import com.ss.studysystem.UI.model.survey_mdl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class survey_controller {

    @FXML
    private Button skip;

    @FXML
    private VBox survey_view;

    private VBox q_root = new VBox();

    @FXML
    private Pane l_pane;

    @FXML
    private Pane r_pane;


    @FXML
    private BorderPane border_root;

    survey_mdl survey_cnf = survey_mdl.getInstance();

    private List<Node> pages = survey_cnf.getPages();

    @FXML
    void initialize(){

        q_root.setId("q_root");
        q_root.maxHeight(300.0);

        border_root.widthProperty().addListener((ob,ov,nv)->{
            System.out.println(nv.longValue());
            if(nv.longValue() < 900
                    && border_root.getChildren().contains(l_pane)
            && border_root.getChildren().contains(r_pane)){
                border_root.getChildren().remove(l_pane);
                border_root.getChildren().remove(r_pane);
            }else if(nv.longValue() >= 900
                    && !border_root.getChildren().contains(l_pane)
                    && !border_root.getChildren().contains(r_pane)){
                 border_root.setLeft(l_pane);
                 border_root.setRight(r_pane);
            }

        });

        try{

            URL resource = getClass().getResource("/com/ss/studysystem/Fxml/survey/");
            if (resource == null) {
                throw new IOException("Directory not found: /com/ss/studysystem/Fxml/survey");
            }


            Path folderPath = Paths.get(resource.toURI());

            Files.list(folderPath)
                    .filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(".fxml"))
                    .forEach(file -> {
                        try {

                            FXMLLoader loader = new FXMLLoader(file.toUri().toURL());
                            Node root = loader.load();

                            System.out.println("Loaded FXML: " + file.getFileName());
                            pages.add(root);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });


            URL btn_path = getClass().getResource("/com/ss/studysystem/Fxml/survey_buttons.fxml");
            Node btns = FXMLLoader.load(btn_path);

            q_root.getChildren().addAll(pages.get(0),btns);

            survey_view.getChildren().add(q_root);
            q_root.setAlignment(Pos.CENTER);

            survey_cnf.setRoot(q_root);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
