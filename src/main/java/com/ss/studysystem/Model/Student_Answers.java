package Model;

import java.time.LocalDateTime;

public class Student_Answers {
	private int response;
	private Test test;
	private Users user;
	private Questions question;
	private int answer;
	private Boolean is_correct;
	private LocalDateTime attempted_at;
	public Student_Answers() {
	}
	public int getResponse() {
		return response;
	}
	public void setResponse(int response) {
		this.response = response;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Questions getQuestion() {
		return question;
	}
	public void setQuestion(Questions question) {
		this.question = question;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public Boolean getIs_correct() {
		return is_correct;
	}
	public void setIs_correct(Boolean is_correct) {
		this.is_correct = is_correct;
	}
	public LocalDateTime getAttempted_at() {
		return attempted_at;
	}
	public void setAttempted_at(LocalDateTime attempted_at) {
		this.attempted_at = attempted_at;
	}
	
}
