package com.ss.studysystem.controller.classroom;

import com.ss.studysystem.Model.Classrooms;
import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.controller.create_and_join;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

public class classroom_controller {

    @FXML
    private VBox pane;


    @FXML
    private Button create_classroom;



    @FXML
    void initialize(){

    }

    Consumer<Classrooms> on_class;

    public void setOn_class(Consumer<Classrooms> on_class) {
        this.on_class = on_class;
    }

    @FXML
    void create(ActionEvent event) throws Exception {
        URL loader = getClass().getResource("/com/ss/studysystem/Fxml/startup/Create and Join.fxml");
        FXMLLoader fm_radio = new FXMLLoader(loader);
        Parent load_view = fm_radio.load();
        create_and_join cj = fm_radio.getController();
        cj.setOn_classroom(on_class);

        Stage stage = modal_builder.build_fixed_modal((Stage) create_classroom.getScene().getWindow(), load_view, 400, 400);
        stage.show();

        URL Toggle = getClass().getResource("/com/ss/studysystem/Fxml/chatroomlogo.fxml");
        Parent Toggle_view = FXMLLoader.load(Toggle);

        pane.getChildren().add(Toggle_view);

    }



}
