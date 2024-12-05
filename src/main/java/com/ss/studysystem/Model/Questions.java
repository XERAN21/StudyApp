package com.ss.studysystem.Model;

public class Questions {
    private int id;
    private String question_text;

    public Questions() {

    }

    public int getQuestion() {
        return id;
    }

    public void setQuestion(int question) {
        this.id = question;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

}
