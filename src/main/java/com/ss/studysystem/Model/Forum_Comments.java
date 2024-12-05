package com.ss.studysystem.Model;

import java.sql.Blob;
import java.time.LocalDateTime;

public class Forum_Comments {
	private int comment;
	private Forums forum;
	private Users user;
	private String comment_text;

	private Blob forum_file;
	private String forum_file_path;
	private LocalDateTime created_at;
	
	public Forum_Comments() {
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public Forums getForum() {
		return forum;
	}

	public void setForum(Forums forum) {
		this.forum = forum;
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

	public Blob getForum_file() {
		return forum_file;
	}

	public void setForum_file(Blob forum_file) {
		this.forum_file = forum_file;
	}

	public String getForum_file_path() {
		return forum_file_path;
	}

	public void setForum_file_path(String forum_file_path) {
		this.forum_file_path = forum_file_path;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
}

