package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Chatrooms {
    private int Chartroom_id;
    private String name;
    private String invitation_code;
    private Users user;
    private LocalDateTime created_at;

    public Chatrooms() {
    }

    public int getChartroom_id() {
        return Chartroom_id;
    }

    public void setChartroom_id(int chartroom_id) {
        this.Chartroom_id = chartroom_id;
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

    public void setInvitation_code(String invitation_code) {
        this.invitation_code = invitation_code;
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
}
