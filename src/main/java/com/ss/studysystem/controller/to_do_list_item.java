package com.ss.studysystem.controller;

import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.database.controller.to_do_list_controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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

        task_col.widthProperty().addListener((ob, ov, nv) -> {
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
        try {
            FXMLLoader alert_mdl = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/modals/del_task_confirmation_type_a.fxml"));
            Parent alert_view = alert_mdl.load();
            Stage stage = modal_builder.build_fixed_modal((Stage) delete.getScene().getWindow(), alert_view, 447, 248);
            del_task_controller del_ctrl = alert_mdl.getController();

            del_ctrl.setToDoList(toDoList);

            del_ctrl.setOnResult(result -> {
                if (result) {
                    boolean success = to_do_list_controller.delete_to_do_list(toDoList.getTo_do_list());
                    if (success) {
                        remove_task();
                        System.out.println("Task deleted successfully!");
                    } else {
                        System.out.println("Failed to delete task.");
                    }
                } else {
                    System.out.println("Delete task operation was cancelled.");
                }
            });

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void remove_task() {
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
        //if (!to_do_list_controller.update_to_do_list(toDoList)) return;

        task.setStrikethrough(check.isSelected());

    }

}
