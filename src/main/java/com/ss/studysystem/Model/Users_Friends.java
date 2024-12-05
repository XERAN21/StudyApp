package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Users_Friends {
    private Users user;
    private int friend;
    private LocalDateTime added_at;

    public Users_Friends() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getFriend() {
        return friend;
    }

    public void setFriend(int friend) {
        this.friend = friend;
    }

    public LocalDateTime getAdded_at() {
        return added_at;
    }

    public void setAdded_at(LocalDateTime added_at) {
        this.added_at = added_at;
    }


}
