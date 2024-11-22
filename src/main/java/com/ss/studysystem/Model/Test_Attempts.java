package Model;

import java.time.LocalDateTime;

public class Test_Attempts {
	private int attempt;
	private Test test;
	private Users user;
	private int score;
	private LocalDateTime attempted_at;
	public Test_Attempts() {
	}
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public LocalDateTime getAttempted_at() {
		return attempted_at;
	}
	public void setAttempted_at(LocalDateTime attempted_at) {
		this.attempted_at = attempted_at;
	}
	
}
