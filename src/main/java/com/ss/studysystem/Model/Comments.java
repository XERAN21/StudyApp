package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Comments {
	private int comment_id;
	private Users user;
	private Classrooms classrooms;
	private String content;
	private LocalDateTime created_at;
	
	public Comments() {
	}
	
	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment) {
		this.comment_id = comment;
	}

	public Classrooms getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(Classrooms classrooms) {
		this.classrooms = classrooms;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	
}
