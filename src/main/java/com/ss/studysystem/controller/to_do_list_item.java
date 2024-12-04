package com.ss.studysystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class to_do_list_item {

    @FXML
    private CheckBox check;

    @FXML
    private Button delete;

    @FXML
    private Text task;

    @FXML
    private GridPane task_root;

    @FXML
    private VBox task_col;

    VBox parent;

    @FXML
    void initialize() {

        delete.setOnAction(e -> delete_task());
        check.setOnAction(e -> complete_task());

        task_col.widthProperty().addListener((ob,ov,nv)->{
            double w = nv.doubleValue();
            task.setWrappingWidth(w);
        });

    }

    private void delete_task() {

        //Database controller logic

        parent.getChildren().remove(task_root);
    }

    public void set_parent(VBox node) {
        this.parent = node;
    }

    public void set_task(String new_task) {
        task.setText(new_task);
    }

    private void complete_task() {

        //Database controller logic

        task.setStrikethrough(check.isSelected());

    }

}
