package com.ss.studysystem.controller;

import com.ss.studysystem.Model.Frequency;
import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.UI.layouts.status_indicators;
import com.ss.studysystem.UI.utils.indicator_animation;
import com.ss.studysystem.database.controller.to_do_list_controller;
import javafx.application.Platform;
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

    private indicator_animation iani = new indicator_animation();

    @FXML
    void initialze() {
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

            int uid = 1; //todo change this later

            Frequency freq = to_do_list.getCurrent_freq();

            Users users = new Users();
            users.setId(uid);

            To_Do_List toDoList = new To_Do_List();
            toDoList.setUser(users);
            toDoList.setContent(new_task_field.getText());
            toDoList.setFreq(freq);
            toDoList.setIs_complete(false);

            int[] inserted_id = new int[1];

            if (!to_do_list_controller.create_to_do_list(toDoList, inserted_id)) return; //todo throws error

            toDoList.setTo_do_list(inserted_id[0]);

            append_node(toDoList, status_indicators.PROGRESS);

//            grid.add(item, 0, row_count++);

            new_task_field.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void set_day(Frequency day) {
        this.Day.setText(day.getValue());
    }

    public void append_node(To_Do_List toDoList, status_indicators type) {
        try {
            URL item_path = getClass().getResource("/com/ss/studysystem/Fxml/to_do_list_item.fxml");
            FXMLLoader item_loader = new FXMLLoader(item_path);
            Node item = item_loader.load();

            to_do_list_item tdl_ctrl = item_loader.getController();
            tdl_ctrl.set_task(toDoList.getContent());
            tdl_ctrl.set_parent(list_view);
            tdl_ctrl.setParent_parent(list_root);

            tdl_ctrl.setToDoList(toDoList);

            list_view.getChildren().add(0, item);

            Platform.runLater(() -> iani.animate_light_effect(tdl_ctrl.getStack_pane(), type));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
