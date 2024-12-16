package com.ss.studysystem.controller;

import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.layouts.status_indicators;
import com.ss.studysystem.UI.utils.config_brightness;
import com.ss.studysystem.UI.utils.indicator_animation;
import com.ss.studysystem.database.async_service.Operation;
import com.ss.studysystem.database.async_service.to_do_list_async;
import com.ss.studysystem.database.controller.to_do_list_controller;
import javafx.animation.ParallelTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class to_do_list_item {

    @FXML
    private CheckBox check;

    @FXML
    private StackPane stack_pane;

    @FXML
    private Button delete;

    @FXML
    private Text task;

    @FXML
    private GridPane task_root;

    @FXML
    private VBox task_col;

    ScrollPane parent_parent;
    VBox parent;
    To_Do_List toDoList;
    indicator_animation iani = new indicator_animation();

    @FXML
    void initialize() {

        delete.setOnAction(this::delete_task);

        check.setOnAction(e -> complete_task());

        task_col.widthProperty().addListener((ob, ov, nv) -> {
            double w = nv.doubleValue();
            task.setWrappingWidth(w);
        });

        Platform.runLater(()->{
            task.setStrikethrough(toDoList.getIs_complete());
            check.setSelected(toDoList.getIs_complete());
        });

    }

    public void setToDoList(To_Do_List toDoList) {
        this.toDoList = toDoList;
    }

    @FXML
    private void delete_task(ActionEvent event) {

        //todo ask for user confirmation

        //todo Database controller logic
        try {

            config_brightness.applyDimmingEffectFromEvent(event);

            FXMLLoader alert_mdl = new FXMLLoader(getClass().getResource("/com/ss/studysystem/Fxml/modals/del_task_confirmation_type_a.fxml"));
            Parent alert_view = alert_mdl.load();
            Stage stage = modal_builder.build_fixed_modal((Stage) delete.getScene().getWindow(), alert_view, 447, 248);
            del_task_controller del_ctrl = alert_mdl.getController();

            del_ctrl.setToDoList(toDoList);

            del_ctrl.setOnResult(result -> {
                if (result) {
                    System.out.println(toDoList.getTo_do_list());

                   // boolean success = to_do_list_controller.delete_to_do_list(toDoList.getTo_do_list());
                    to_do_list_async tdl_service = new to_do_list_async(toDoList);
                    tdl_service.set_operation(Operation.DELETE);

                    double y_axis = parent_parent.getVvalue();
                    tdl_service.setOnSucceeded(s_event->{
                        Boolean success = tdl_service.getValue();
                        if (success) {
                            ParallelTransition trans = iani.animate_remove_node_squash_effect(stack_pane);
                            trans.setOnFinished(ani_event->{
                                remove_task();
                                Platform.runLater(()->parent_parent.setVvalue(y_axis));
                            });
                            //Platform.runLater(() -> parent_parent.setVvalue(y_axis));
                            Platform.runLater(trans::play);
                        } else {
                            System.out.println("Failed to delete task.");
                        }
                    });

                    tdl_service.start();
                }

                config_brightness.removeDimmingEffect((Stage) parent.getScene().getWindow());
            });

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove_task() {
        parent.getChildren().remove(stack_pane);
    }

    public void set_parent(VBox node) {
        this.parent = node;
    }

    public void setParent_parent(ScrollPane parent_parent) {
        this.parent_parent = parent_parent;
    }

    public void set_task(String new_task) {
        task.setText(new_task);
    }

    private void complete_task() {

        toDoList.setIs_complete(check.isSelected());

        //todo Database controller logic
        to_do_list_async tdl_service = new to_do_list_async(toDoList);
        tdl_service.set_operation(Operation.UPDATE);

        tdl_service.setOnSucceeded(state ->{
            Boolean success = tdl_service.getValue();
            if(success){
                Platform.runLater(()->iani.animate_light_effect(stack_pane, status_indicators.UPDATE));
            }
        });
        //if (!to_do_list_controller.update_to_do_list(toDoList)) return;

        tdl_service.start();
        task.setStrikethrough(check.isSelected());

    }

    public StackPane getStack_pane() {
        return stack_pane;
    }

}
