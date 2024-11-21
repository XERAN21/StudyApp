package com.ss.studysystem.Model;

public class User_Classroom {
    private Users user;
    private Classrooms classrooms;
    private String[] role = {"Teacher","Student"};
    private boolean is_active = true;

    public User_Classroom() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Classrooms getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(Classrooms classrooms) {
        this.classrooms = classrooms;
    }

    public String[] getRole() {
        return role;
    }

    public void setRole(String[] role) {
        this.role = role;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
