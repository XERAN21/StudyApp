package com.ss.studysystem.Model;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Users {
    private int id;
    private String username;
    private String email;
    private String password;
    private String salt;
    private LocalDate dob;
    private int study_hour;
    private Blob profile_img;
    private String file_patch;
    private boolean is_active;
    private LocalDateTime created_at;

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getStudy_hour() {
        return study_hour;
    }

    public void setStudy_hour(int study_hour) {
        this.study_hour = study_hour;
    }

    public Blob getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(Blob profile_img) {
        this.profile_img = profile_img;
    }

    public String getFile_patch() {
        return file_patch;
    }

    public void setFile_patch(String file_patch) {
        this.file_patch = file_patch;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", dob=" + dob +
                ", study_hour=" + study_hour +
                ", profile_img=" + profile_img +
                ", file_patch='" + file_patch + '\'' +
                ", is_active=" + is_active +
                ", created_at=" + created_at +
                '}';
    }
}
