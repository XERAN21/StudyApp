package com.ss.studysystem.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;

public class to_do_list {

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

    private GridPane grid = new GridPane();
    int row_count = 0;

    @FXML
    void initialize() {

        list_view.getChildren().add(grid);

        list_view.setAlignment(Pos.TOP_CENTER);

        String today = LocalDate.now().getDayOfWeek().toString();
        System.out.println(today);

        Day.setText(today);

        for (Node n : days_list.getChildren()) {
            if (n instanceof VBox) {
                for (Node l : ((VBox) n).getChildren()) {
                    if (l.getId().matches(today)
                            && ((VBox) n).getChildren().get(1) instanceof Circle c) {
                        c.setStyle("-fx-fill: RED;");
                        return;
                    }
                }
            }
        }


    }

    @FXML
    void add_new_list(ActionEvent event) {

        if (new_task_field.getText() == null || new_task_field.getText().isEmpty()) {

            //logic

            return;
        }

        try {

            //Database controller logic

            URL item_path = new File("src/main/resources/com/ss/studysystem/Fxml/to_do_list_item.fxml").toURI().toURL();
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


}
