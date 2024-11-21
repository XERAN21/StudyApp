package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Forums {
	private int id;
	private Classrooms classroom;
	private String title;
	private String description;
	private Users user;
	private LocalDateTime created_at;
	
	public Forums() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	
	
}

