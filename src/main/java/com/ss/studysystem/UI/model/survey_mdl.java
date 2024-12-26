package com.ss.studysystem.UI.model;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class survey_mdl {

    private static WeakReference<survey_mdl> weak_instance = new WeakReference<>(null);
    private static survey_mdl instance;

    public static survey_mdl getInstance() {
        if (instance == null) {
            instance = new survey_mdl();
        }
        return instance;
    }

    private List<Node> pages = new ArrayList<>();
    private VBox root;

    public List<Node> getPages() {
        return pages;
    }

    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }

    public void addPage(Node n) {
        pages.add(n);
    }

    public void setPages(List<Node> pages) {
        this.pages = pages;
    }
}