package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Classroom_invite {
    int invitation_id;
    Classrooms classrooms;
    String invite_code;
    LocalDateTime created_at;
    LocalDateTime expire_at;
    boolean is_used;

    public int getInvitation_id() {
        return invitation_id;
    }

    public void setInvitation_id(int invitation_id) {
        this.invitation_id = invitation_id;
    }

    public Classrooms getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Classrooms classrooms) {
        this.classrooms = classrooms;
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
        return "Classroom_invite{" +
                "invitation_id=" + invitation_id +
                ", classrooms=" + classrooms +
                ", invite_code='" + invite_code + '\'' +
                ", created_at=" + created_at +
                ", expire_at=" + expire_at +
                ", is_used=" + is_used +
                '}';
    }
}
