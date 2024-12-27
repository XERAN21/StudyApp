package com.ss.studysystem.UI.model;

import com.ss.studysystem.Model.Frequency;
import javafx.scene.Node;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class to_do_task {

    private static to_do_task instance;

    private static WeakReference<to_do_task> weak_instance = new WeakReference<>(null);

    private Map<Frequency, Node> task_days = new HashMap<>();

    private Node Mon;
    private Node Tue;
    private Node Wed;
    private Node Thu;
    private Node Fri;
    private Node Sat;
    private Node Sun;

    public to_do_task() {
    }

    public static to_do_task getInstance() {
        if (instance == null) {
            instance = new to_do_task();
        }
        return instance;
    }

    public static to_do_task getWeakInstance() {
        to_do_task temp = weak_instance.get();
        if (temp == null) {
            temp = new to_do_task();
            weak_instance = new WeakReference<>(temp);
        }
        return temp;
    }

    public static void clear() {
        to_do_task dump_inst = to_do_task.getWeakInstance();
        dump_inst.dump();
    }

    public void dump() {
        weak_instance = new WeakReference<>(null);
        instance = null;
        task_days.clear();
        task_days = null;
        System.gc();
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

    public Map<Frequency, Node> getTask_days() {
        return task_days;
    }

    public void setTask_days(Map<Frequency, Node> task_days) {
        this.task_days = task_days;
    }
}
