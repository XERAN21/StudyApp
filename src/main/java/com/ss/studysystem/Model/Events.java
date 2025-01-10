package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Events {
    private int id;
    private String title;
    private String description;
    private Users user;
    private Classrooms classroom;
    private LocalDateTime created_at;
    private boolean is_archived = false;
    private LocalDateTime start_date;

    public Events() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Classrooms getClassroom() {
        return classroom;
    }

    public void setClassroom(Classrooms classroom) {
        this.classroom = classroom;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public boolean is_archived() {
        return is_archived;
    }

    public void set_archived(boolean is_archived) {
        this.is_archived = is_archived;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }
}
