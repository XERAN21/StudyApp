package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Chatrooms {
    private int id;
    private String name;
    private String invitation_code;
    private Users user;
    private LocalDateTime created_at;

    public Chatrooms() {
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

    public String getInvitation_code() {
        return invitation_code;
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

}
