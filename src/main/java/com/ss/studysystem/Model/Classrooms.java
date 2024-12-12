package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Classrooms {
    private int id;
    private String name;
    private String description;
    private Users user;
    private LocalDateTime created_at;
    private boolean is_archived = false;

    public Classrooms() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Classrooms{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", created_at=" + created_at +
                ", is_archived=" + is_archived +
                '}';
    }
}
