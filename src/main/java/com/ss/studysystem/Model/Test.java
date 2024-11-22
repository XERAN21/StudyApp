package Model;

import java.time.LocalDateTime;

public class Test {
	private int test;
	private Classrooms classroom;
	private String title;
	private String[] type = {"Exam", "Quizze"};
	private int created_by;
	private LocalDateTime created_at;
	public Test() {
	}
	public int getTest() {
		return test;
	}
	public void setTest(int test) {
		this.test = test;
	}
	public Classrooms getClassroom() {
		return classroom;
	}
	public void setClassroom(Classrooms classroom) {
		this.classroom = classroom;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getType() {
		return type;
	}
	public void setType(String[] type) {
		this.type = type;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	 
}
