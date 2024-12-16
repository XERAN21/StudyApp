package com.ss.studysystem.Model;

import java.sql.Blob;

public class Questions {
	private int id;
	private String question_text;
	private Blob question_img;

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

	public Blob getQuestion_img() {
		return question_img;
	}

	public void setQuestion_img(Blob question_img) {
		this.question_img = question_img;
	}
}
