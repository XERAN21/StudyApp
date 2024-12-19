package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Chatroom_invite {
    int invitation_code;
    Chatrooms chatrooms;
    String invite_code;
    LocalDateTime created_at;
    LocalDateTime expire_at;
    boolean is_used;

    public int getInvitation_code() {
        return invitation_code;
    }

    public void setInvitation_code(int invitation_code) {
        this.invitation_code = invitation_code;
    }

    public Chatrooms getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(Chatrooms chatrooms) {
        this.chatrooms = chatrooms;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getExpire_at() {
        return expire_at;
    }

    public void setExpire_at(LocalDateTime expire_at) {
        this.expire_at = expire_at;
    }

    public boolean isIs_used() {
        return is_used;
    }

    public void setIs_used(boolean is_used) {
        this.is_used = is_used;
    }

    @Override
    public String toString() {
        return "Chatroom_invite{" +
                "invitation_code=" + invitation_code +
                ", chatrooms=" + chatrooms +
                ", invite_code='" + invite_code + '\'' +
                ", created_at=" + created_at +
                ", expire_at=" + expire_at +
                ", is_used=" + is_used +
                '}';
    }
}
