package com.ss.studysystem.controller.to_do_list;

import com.ss.studysystem.Model.Frequency;
import com.ss.studysystem.Model.To_Do_List;
import com.ss.studysystem.UI.layouts.status_indicators;
import com.ss.studysystem.UI.model.to_do_task;
import com.ss.studysystem.database.controller.to_do_list_controller;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static Frequency current_freq;
    private String today = LocalDate.now().getDayOfWeek().toString();

    private to_do_task to_do_config = to_do_task.getWeakInstance();
    private Map<Frequency, Node> task_day = to_do_config.getTask_days();

    private GridPane grid = new GridPane();
    int row_count = 0;
    Map<Frequency, to_do_list_view> node_map = new HashMap<>();
    private static final Logger logger = Logger.getLogger(to_do_list.class.getName());

    @FXML
    void initialize() {

        try {

            String day = today.substring(0, 3).toUpperCase();
            Frequency view_day = Frequency.valueOf(day);

            this.current_freq = view_day;

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
                        this.current_freq = freq;
                        switch_day(freq);
                    });

                    //today -> WEDNESDAY -> WED
                    //lbl.getText -> Wed -> WED
                    if (lbl.getText().toUpperCase().matches(today.substring(0, 3))) {
                        c.setStyle("-fx-fill: RED;");
                        this.current_day = c;
                        continue;
                    }

                    //todo load .fxml
                    load_to_do(freq);
                }

            }
        }

        service_tdl.setOnSucceeded(workerStateEvent -> {
            List<To_Do_List> td_list = service_tdl.getValue();
            for (To_Do_List td : td_list) {
                try {
                    to_do_list_view tdl_ctrl = node_map.get(td.getFreq());
                    if (tdl_ctrl != null) {
                        tdl_ctrl.append_node(tdl_ctrl.generate_to_do_item(td, status_indicators.LOAD),
                                status_indicators.LOAD);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        service_tdl.setOnFailed(event -> {
            Throwable throwable = service_tdl.getException();
            logger.log(Level.SEVERE, "Scene loading task failed.", throwable);
        });

        service_tdl.start();

    }

    public static Frequency getCurrent_freq() {
        return current_freq;
    }


    void assign_day_node(Node n, Frequency day) {

        /* old code

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
*/

        task_day.put(day, n);
    }

    Node load_to_do(Frequency view_day) {

        try {
            URL path = getClass().getResource("/com/ss/studysystem/Fxml/to_do_list_view.fxml");
            FXMLLoader view_loader = new FXMLLoader(path);
            Node view = view_loader.load();

            to_do_list_view to_do_list_controller = view_loader.getController();
            to_do_list_controller.set_day(view_day);

            assign_day_node(view, view_day);

            node_map.put(view_day, to_do_list_controller);

            return view;

        } catch (Exception e) {
            e.printStackTrace();
            return new Label("Failed to load resource. \n" + e);
        }

    }

    void switch_day(Frequency freq) {

        root.getChildren().remove(current_node);

        /*
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
    */

        this.current_node = task_day.get(freq);

        root.getChildren().add(current_node);
    }

    //Async load task
    Service<List<To_Do_List>> service_tdl = new Service<>() {
        @Override
        protected Task<List<To_Do_List>> createTask() {
            return new Task<>() {
                @Override
                protected List<To_Do_List> call() throws Exception {
                    return to_do_list_controller.get_to_do_list(1); //todo dyn uid
                }
            };
        }
    };

}
