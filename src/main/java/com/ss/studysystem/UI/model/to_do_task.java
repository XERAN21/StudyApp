package com.ss.studysystem.UI.model;

import javafx.scene.Node;

public class to_do_task {

    private static to_do_task instance;

    private Node Mon;
    private Node Tue;
    private Node Wed;
    private Node Thu;
    private Node Fri;
    private Node Sat;
    private Node Sun;

    public to_do_task() {
    }

    public static to_do_task getInstance(){
        if(instance == null){
            instance = new to_do_task();
        }
        return instance;
    }

    public Node getMon() {
        return Mon;
    }

    public void setMon(Node mon) {
        Mon = mon;
    }

    public Node getTue() {
        return Tue;
    }

    public void setTue(Node tue) {
        Tue = tue;
    }

    public Node getWed() {
        return Wed;
    }

    public void setWed(Node wed) {
        Wed = wed;
    }

    public Node getThu() {
        return Thu;
    }

    public void setThu(Node thu) {
        Thu = thu;
    }

    public Node getFri() {
        return Fri;
    }

    public void setFri(Node fri) {
        Fri = fri;
    }

    public Node getSat() {
        return Sat;
    }

    public void setSat(Node sat) {
        Sat = sat;
    }

    public Node getSun() {
        return Sun;
    }

    public void setSun(Node sun) {
        Sun = sun;
    }
}
