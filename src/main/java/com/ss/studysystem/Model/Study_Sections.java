package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Study_Sections {
	private int session;
	private Users user;
	private String title;
	private String description;
	private LocalDateTime created_at;
	private int session_duration;
	
	public Study_Sections() {
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
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

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public int getSession_duration() {
		return session_duration;
	}

	public void setSession_duration(int session_duration) {
		this.session_duration = session_duration;
	}
	
	
	
}
