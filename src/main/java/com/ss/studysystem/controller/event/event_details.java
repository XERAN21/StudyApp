package com.ss.studysystem.controller.event;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class event_details {

    @FXML
    private Label day;

    @FXML
    private Label date;

    @FXML
    private Label exam_title;

    @FXML
    private Label class_name;

    @FXML
    void initialize(){

    }

    public Label getDay() {
        return day;
    }

    public void setDay(Label day) {
        this.day = day;
    }

    public Label getDate() {
        return date;
    }

    public void setDate(Label date) {
        this.date = date;
    }

    public Label getExam_title() {
        return exam_title;
    }

    public void setExam_title(Label exam_title) {
        this.exam_title = exam_title;
    }

    public Label getClass_name() {
        return class_name;
    }

    public void setClass_name(Label class_name) {
        this.class_name = class_name;
    }
}
