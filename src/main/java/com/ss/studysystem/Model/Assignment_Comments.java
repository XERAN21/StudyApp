package com.ss.studysystem.Model;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Assignment_Comments {
	private int comment_id;
	private Assignments assignments;
	private Users user;
	private String comment_text;
	private LocalDateTime created_at;

	public Assignment_Comments() {
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public Assignments getAssignments() {
		return assignments;
	}

	public void setAssignments(Assignments assignments) {
		this.assignments = assignments;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
}
