package com.ss.studysystem.controller.event;

import com.ss.studysystem.Model.Events;
import com.ss.studysystem.cnf.user_cnf;
import com.ss.studysystem.database.controller.event_controller;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Event {

    @FXML
    private BorderPane root;

    @FXML
    private Button Close;

    private user_cnf user = user_cnf.get_instance();
    private event_navbar navbar_contoller;
    private List<Events> all_events;
    private List<Events> current_list;


    @FXML
    void initialize(){
        Close.setOnAction(this::close);

        try {
            URL path = getClass().getResource("/com/ss/studysystem/Fxml/Event/event_navbar.fxml");
            FXMLLoader view_loader = new FXMLLoader(path);

            navbar_contoller = view_loader.getController();
            Node view = view_loader.load();

            navbar_contoller.setEvent_type_consumer(result->{
                handleEvent(result);
            });

            root.setLeft(view);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        load_all_events.setOnSucceeded(succ->{
            all_events = load_all_events.getValue();
            current_list = all_events;
        });


        load_all_events.start();
    }

    @FXML
    void close(ActionEvent event){
        Stage stage = (Stage) Close.getScene().getWindow();
        stage.close();
    }


    void handleEvent(event_type event) {

        switch (event) {
            case ALL:
                current_list = all_events;
                break;
            case TODAY:

                break;
            case WEEK:

            case MONTH:
                current_list = get_this_month(all_events);

                break;
        }
    }

   private List<Events> get_this_month(List<Events> events){

        List<Events> this_month_event = new ArrayList<>();
        YearMonth curr_month = YearMonth.now();
        LocalDateTime this_month_start = curr_month.atDay(1).atStartOfDay();
        LocalDateTime this_month_end = curr_month.atEndOfMonth().atTime(23, 59, 59);

        for (Events event : events){
            if (is_matches(this_month_start, this_month_end,event)){
                this_month_event.add(event);
            }
        }
        return this_month_event;
    }

    public boolean is_matches(LocalDateTime start,LocalDateTime end, Events events){
        boolean is_matches = !events.getStart_date().isBefore(start) &&
                !events.getStart_date().isAfter(end);
        return is_matches;
    }

    private List<Events> get_this_week(List<Events> events){
        List<Events> this_week_event = new ArrayList<>();
        LocalDateTime curr_week = LocalDateTime.now();
        LocalDateTime this_week_start = curr_week.with(DayOfWeek.MONDAY).truncatedTo(ChronoUnit.DAYS);
        LocalDateTime this_week_end = curr_week.with(DayOfWeek.SUNDAY).truncatedTo(ChronoUnit.DAYS);

        for (Events event : events){
            if (is_matches(this_week_start, this_week_end,event)){
                this_week_event.add(event);
            }
        }
        return this_week_event;
    }


    Service<List<Events>> load_all_events = new Service<List<Events>>() {
        @Override
        protected Task<List<Events>> createTask() {
            return new Task<List<Events>>() {
                @Override
                protected List<Events> call() throws Exception {
                    return  event_controller.get_all_event(user.getUser().getId());
                }
            };
        }
    };

}
