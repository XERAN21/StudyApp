package com.ss.studysystem.controller;

import com.ss.studysystem.Model.Frequency;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;

public class to_do_list_view {

    @FXML
    private Label Day;

    @FXML
    private Button add_list;

    @FXML
    private ScrollPane list_root;

    @FXML
    private VBox list_view;

    @FXML
    private TextArea new_task_field;

    @FXML
    void initialze(){
        //todo
    }

    //todo async load user tasks

    @FXML
    void add_new_list(ActionEvent event) {

        if (new_task_field.getText() == null || new_task_field.getText().isEmpty()) {

            //logic -> throws error

            return;
        }

        try {

            //Database controller logic

            URL item_path = getClass().getResource("/com/ss/studysystem/Fxml/to_do_list_item.fxml");
            FXMLLoader item_loader = new FXMLLoader(item_path);
            Node item = item_loader.load();

            to_do_list_item tdl_ctrl = item_loader.getController();
            tdl_ctrl.set_task(new_task_field.getText());
            tdl_ctrl.set_parent(list_view);

            list_view.getChildren().add(item);

//            grid.add(item, 0, row_count++);

            new_task_field.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void set_day(Frequency day){
        this.Day.setText(day.getValue());
    }

}
