package com.ss.studysystem.controller.classroom;

import com.ss.studysystem.Model.Classrooms;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.function.Consumer;

public class classroom_item {

    @FXML
    private GridPane class_root;

    @FXML
    private Label classname;

    @FXML
    private Label desc;

    private Classrooms current;

    Consumer<Region> on_change;

    public void setOn_change(Consumer<Region> on_change) {
        this.on_change = on_change;
    }

    void init(Classrooms classrooms) {
        this.current = classrooms;
        Platform.runLater(() -> {
            classname.setText(classrooms.getName());
            desc.setText(classrooms.getDescription());
        });


    }

    @FXML
    void load(Classrooms classrooms) {

    }

    @FXML
    void initialize() {

        class_root.setOnMouseClicked(e -> load_class());
    }

    void load_class(){
        try{
            URL view_path = getClass().getResource("/com/ss/studysystem/Fxml/classroom/class_home.fxml");
            FXMLLoader view_load =  new FXMLLoader(view_path);
            Region view_region = view_load.load();
            class_home class_ctrl = view_load.getController();
            class_ctrl.set_text(current.getName());

            if(on_change != null){
                on_change.accept(view_region);
            }

        }catch (Exception e){

        }
    }
}
