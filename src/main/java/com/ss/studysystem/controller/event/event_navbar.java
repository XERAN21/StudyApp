package com.ss.studysystem.controller.event;

import com.ss.studysystem.UI.components.modal_builder;
import com.ss.studysystem.UI.misc.modal_animations;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class event_navbar {

    @FXML
    private Button all_btn;

    @FXML
    private Button today_btn;

    @FXML
    private Button week_btn;

    @FXML
    private Button month_event;

    @FXML
    private Button close_btn;

    @FXML
    void initialize(){
        all_btn.setUserData(event_type.ALL);
        today_btn.setUserData(event_type.TODAY);
        week_btn.setUserData(event_type.WEEK);
        month_event.setUserData(event_type.MONTH);
        close_btn.setOnAction(this::close);
    }

    @FXML void handleEvent(ActionEvent event) {
        Button source = (Button) event.getSource();
        event_type eventType = (event_type) source.getUserData();
            switch (eventType) {
                case ALL: show_all_event();
                    break;
                case TODAY: show_today_event();
                    break;
                case WEEK: show_weekly_event()    ;
                    break;
                case MONTH: show_monthly_event();
                    break; } }


    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) close_btn.getScene().getWindow();
        Parent view = stage.getScene().getRoot();
        ParallelTransition closeAnimation =  modal_animations.close_modal_w_size(view, stage.getWidth(), stage.getHeight());
        closeAnimation.setOnFinished(e -> stage.close());

        closeAnimation.play();
    }

    @FXML
    void show_all_event() {
        //todo show all event from database

    }

    @FXML
    void show_monthly_event() {
        //todo show all event by month from database
    }

    @FXML
    void show_today_event() {
        //todo show today all event from database
    }

    @FXML
    void show_weekly_event() {
        //todo show all event by week from database
    }

}
