package com.ss.studysystem.controller;

import com.ss.studysystem.Model.Frequency;
import com.ss.studysystem.UI.model.to_do_task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.time.LocalDate;

public class to_do_list {

    @FXML
    private VBox root;

    @FXML
    private Label Month;

    @FXML
    private Circle Monday;

    @FXML
    private Circle Tuesday;

    @FXML
    private Circle Wednesday;

    @FXML
    private Circle Thursday;

    @FXML
    private Circle Friday;

    @FXML
    private Circle Saturday;

    @FXML
    private Circle Sunday;

    @FXML
    private Label Day;

    @FXML
    private Button add_list;

    @FXML
    private VBox list_view;

    @FXML
    private HBox days_list;

    @FXML
    private TextArea new_task_field;

    private Circle current_day;
    private Node current_node;
    private String today = LocalDate.now().getDayOfWeek().toString();

    private to_do_task to_do_config = to_do_task.getInstance();

    private GridPane grid = new GridPane();
    int row_count = 0;

    @FXML
    void initialize() {

//        list_view.getChildren().add(grid);
//        list_view.setAlignment(Pos.TOP_CENTER);

        try {

            String day = today.substring(0, 3).toUpperCase();
            Frequency view_day = Frequency.valueOf(day);

            Node view = load_to_do(view_day);
            root.getChildren().add(view);
            this.current_node = view;

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Node n : days_list.getChildren()) {
            if (n instanceof VBox) {


                if (((VBox) n).getChildren().get(1) instanceof Circle c
                        && ((VBox) n).getChildren().get(0) instanceof Label lbl) {

                    Frequency freq = Frequency.valueOf(lbl.getText().toUpperCase());
                    c.setUserData(freq);

                    c.setOnMouseClicked(e -> {
                        current_day.setStyle("-fx-fill: #D9D9D9");
                        c.setStyle("-fx-fill: RED;");
                        this.current_day = c;
                        switch_day(freq);
                    });


                    //today -> WEDNESDAY -> WED
                    //lbl.getText -> Wed -> WED
                    if (lbl.getText().toUpperCase().matches(today.substring(0, 3))) {
                        c.setStyle("-fx-fill: RED;");
                        this.current_day = c;
                        continue;
                    }

                    System.out.println(freq.getValue());
                    //todo load .fxml
                    load_to_do(freq);
                }

            }
        }


    }

//    @FXML
//    void add_new_list(ActionEvent event) {
//
//        if (new_task_field.getText() == null || new_task_field.getText().isEmpty()) {
//
//            //logic
//
//            return;
//        }
//
//        try {
//
//            //Database controller logic
//
//            URL item_path = getClass().getResource("/com/ss/studysystem/Fxml/to_do_list_item.fxml");
//            FXMLLoader item_loader = new FXMLLoader(item_path);
//            Node item = item_loader.load();
//
//            to_do_list_item tdl_ctrl = item_loader.getController();
//            tdl_ctrl.set_task(new_task_field.getText());
//            tdl_ctrl.set_parent(list_view);
//
//            list_view.getChildren().add(item);
//
////            grid.add(item, 0, row_count++);
//
//            new_task_field.clear();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    void assign_day_node(Node n, Frequency day) {

        switch (day) {
            case MON:
                to_do_config.setMon(n);
                break;
            case TUE:
                to_do_config.setTue(n);
                break;
            case WED:
                to_do_config.setWed(n);
                break;
            case THU:
                to_do_config.setThu(n);
                break;
            case FRI:
                to_do_config.setFri(n);
                break;
            case SAT:
                to_do_config.setSat(n);
                break;
            case SUN:
                to_do_config.setSun(n);
                break;
            case DAILY:
                //todo
                break;
            default:
                break;
        }
    }

    Node load_to_do(Frequency view_day) {

        try {

            URL path = getClass().getResource("/com/ss/studysystem/Fxml/to_do_list_view.fxml");
            FXMLLoader view_loader = new FXMLLoader(path);
            Node view = view_loader.load();

            to_do_list_view to_do_list_controller = view_loader.getController();
            to_do_list_controller.set_day(view_day);

            assign_day_node(view, view_day);

            return view;

        } catch (Exception e) {
            e.printStackTrace();
            return new Label("Failed to load resource. \n" + e);
        }

    }

    void switch_day(Frequency freq) {

        root.getChildren().remove(current_node);
        switch (freq) {
            case MON:
                this.current_node = to_do_config.getMon();
                break;
            case TUE:
                this.current_node = to_do_config.getTue();
                break;
            case WED:
                this.current_node = to_do_config.getWed();
                break;
            case THU:
                this.current_node = to_do_config.getThu();
                break;
            case FRI:
                this.current_node = to_do_config.getFri();
                break;
            case SAT:
                this.current_node = to_do_config.getSat();
                break;
            case SUN:
                this.current_node = to_do_config.getSun();
                break;
            case DAILY:
                //todo
                break;
            default:
                break;
        }
        root.getChildren().add(current_node);
    }

    //Async load task


}
