package com.ss.studysystem.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//todo need to put two new attributes starttime and endtime

public class Test {
	private int test;
	private Classrooms classroom;
	private String title;
	private Test_Type type; //enum class Test_Type { EXAM, QUIZ }
	private Users user;
	private LocalDateTime start_time;
	private LocalDateTime end_time;
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

	public Test_Type getType() {
		return type;
	}

	public void setType(Test_Type type) {
		this.type = type;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "Test{" +
				"test=" + test +
				", classroom=" + classroom +
				", title='" + title + '\'' +
				", type=" + type +
				", user=" + user +
				", start_time=" + start_time +
				", end_time=" + end_time +
				", created_at=" + created_at +
				'}';
	}
}
