package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Pin_files {
    private Files pin_file;
    private Files file;
    private Users user;
    private Chatrooms chatroom;
    private LocalDateTime created_at;

    public Pin_files() {
    }

    public Files getPin_file() {
        return pin_file;
    }

    public void setPin_file(Files pin_file) {
        this.pin_file = pin_file;
    }

    public Files getFile() {
        return file;
    }

    public void setFile(Files file) {
        this.file = file;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Chatrooms getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatrooms chatroom) {
        this.chatroom = chatroom;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

}
