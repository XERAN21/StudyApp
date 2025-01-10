package com.ss.studysystem.controller.event;

import com.ss.studysystem.Model.Events;
import com.ss.studysystem.cnf.user_cnf;
import com.ss.studysystem.database.controller.event_controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class event_navbar {

    @FXML
    private Button all_btn;

    @FXML
    private Button today_btn;

    @FXML
    private Button week_btn;

    @FXML
    private Button month_event;

    private Consumer<event_type> event_type_consumer;

    private user_cnf user = user_cnf.get_instance();


    @FXML
    void initialize(){
        all_btn.setUserData(event_type.ALL);
        today_btn.setUserData(event_type.TODAY);
        week_btn.setUserData(event_type.WEEK);
        month_event.setUserData(event_type.MONTH);

        all_btn.setOnAction(this::set_event);
        today_btn.setOnAction(this::set_event);
        week_btn.setOnAction(this::set_event);
        month_event.setOnAction(this::set_event);

    }


    @FXML
    void show_today_event() {
        //todo show today all event from database
    }

    @FXML
    void show_weekly_event() {
        //todo show all event by week from database
    }

    public void setEvent_type_consumer(Consumer<event_type> event_type_consumer) {
        this.event_type_consumer = event_type_consumer;
    }

    @FXML
    void handleEvent(ActionEvent event){/*dummy method cuz theres an error*/}

    public boolean is_matches(LocalDateTime start,LocalDateTime end, Events events){
        boolean is_matches = !events.getStart_date().isBefore(start) &&
                            !events.getStart_date().isAfter(end);
                return is_matches;
    }

    public void set_event(ActionEvent event){
        Button source = (Button) event.getSource();
        event_type eventType = (event_type) source.getUserData();
        event_type_consumer.accept(eventType);
    }
}
