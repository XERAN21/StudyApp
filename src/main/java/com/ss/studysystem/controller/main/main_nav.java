package com.ss.studysystem.controller.main;

import com.ss.studysystem.UI.logic.switch_scene;
import com.ss.studysystem.web.lumi_websocket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Stack;
import java.util.UUID;
import java.util.function.Consumer;

public class main_nav {

    @FXML
    private ImageView nav_cat;

    @FXML
    private Button nav_classroom;

    @FXML
    private Button nav_group;

    @FXML
    private Button nav_home;

    @FXML
    private Circle nav_profile;

    @FXML
    private StackPane open_settings;

    @FXML
    private Button nav_solo_study;

    private Stage parent_stage;

    public void setParent_stage(Stage parent_stage) {
        this.parent_stage = parent_stage;
    }

    private Consumer<Boolean> on_hidden;

    public Consumer<Boolean> getOn_hidden() {
        return on_hidden;
    }

    public Consumer<Region> on_change;

    public location getCurrent_loc() {
        return current_loc;
    }

    public void setCurrent_loc(location current_loc) {
        this.current_loc = current_loc;
    }

    public Consumer<Region> getOn_change() {
        return on_change;
    }

    public void setOn_change(Consumer<Region> on_change) {
        this.on_change = on_change;
    }

    public void setOn_hidden(Consumer<Boolean> on_hidden) {
        this.on_hidden = on_hidden;
    }

    private boolean hidden = false;
    private switch_scene switcher = new switch_scene();
    private location current_loc = location.HOME;

    @FXML
    void initialize() {
        nav_cat.setOnMouseClicked(clicked -> {
            if (on_hidden != null) {
                hidden = !hidden;
                on_hidden.accept(hidden);
            }
            System.out.println(hidden);
        });

        nav_solo_study.setOnAction(e -> {

            String room = UUID.randomUUID().toString();
            lumi_websocket.getInstance().create_room(room);
        });

        nav_group.setOnAction(e -> {
            if (!current_loc.equals(location.GROUP))
                if (on_change != null) {
                    Region chatgroup = switcher.open_chat_view(e, parent_stage);
                    current_loc = location.GROUP;
                    on_change.accept(chatgroup);
                }
        });

        nav_classroom.setOnAction(e -> {
            if (!current_loc.equals(location.CLASSROOM))
                if (on_change != null) {
                    Region class_reg = switcher.open_class_view(e, parent_stage);
                    current_loc = location.CLASSROOM;
                    on_change.accept(class_reg);
                }
        });

        nav_home.setOnAction(e -> {
            if (!current_loc.equals(location.HOME))
                if (on_change != null) {
                    Region class_reg = switcher.open_home_view(e, parent_stage);
                    current_loc = location.HOME;
                    on_change.accept(class_reg);
                }
        });

        open_settings.setOnMouseClicked(e -> {
            System.out.println("hello");
            if (on_change != null) {
                Region class_reg = switcher.open_settings(e, parent_stage);
                on_change.accept(class_reg);
                current_loc = location.SETTINGS;
            }

        });
    }

}
