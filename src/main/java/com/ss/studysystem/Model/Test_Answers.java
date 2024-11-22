package com.ss.studysystem.Model;

public class Test_Answers {
	private int answer;
	private Questions question;
	private String answer_text;
	private Boolean is_correct;

	public Test_Answers() {

	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public Questions getQuestion() {
		return question;
	}
	public void setQuestion(Questions question) {
		this.question = question;
	}
	public String getAnswer_text() {
		return answer_text;
	}
	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}
	public Boolean getIs_correct() {
		return is_correct;
	}
	public void setIs_correct(Boolean is_correct) {
		this.is_correct = is_correct;
	}
	
}
