package com.ss.studysystem.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Assignments {
	private int assignment_id;
	private Classrooms classroom;
	private String title;
	private String description;
	private LocalDate due_date;
	private Users created_by;
	private LocalDateTime created_at;
	
	public Assignments() {
	}

	public int getAssignment_id() {
		return assignment_id;
	}

	public void setAssignment_id(int assignment_id) {
		this.assignment_id = assignment_id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}

	public Users getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Users created_by) {
		this.created_by = created_by;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	
	
	
}
