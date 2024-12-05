package com.ss.studysystem.controller;

import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.database.controller.to_do_list_controller;
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
    To_Do_List toDoList;

    @FXML
    void initialize() {

        delete.setOnAction(e -> delete_task());
        check.setOnAction(e -> complete_task());

        task_col.widthProperty().addListener((ob,ov,nv)->{
            double w = nv.doubleValue();
            task.setWrappingWidth(w);
        });

    }

    public void setToDoList(To_Do_List toDoList) {
        this.toDoList = toDoList;
    }

    private void delete_task() {

        //todo ask for user confirmation

        //todo Database controller logic
        System.out.println(toDoList.getTo_do_list());
        if(!to_do_list_controller.delete_to_do_list(toDoList.getTo_do_list())) return; //todo throws error msg
        parent.getChildren().remove(task_root);
    }

    public void set_parent(VBox node) {
        this.parent = node;
    }

    public void set_task(String new_task) {
        task.setText(new_task);
    }

    private void complete_task() {

        //todo Database controller logic

        task.setStrikethrough(check.isSelected());

    }

}
