package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class To_Do_List {
	private int to_do_list;
	private Users user;
	private String content;
	private Boolean is_complete;
	private LocalDateTime created_at;
	
	public To_Do_List() {
	}

	public int getTo_do_list() {
		return to_do_list;
	}

	public void setTo_do_list(int to_do_list) {
		this.to_do_list = to_do_list;
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

	public Boolean getIs_complete() {
		return is_complete;
	}

	public void setIs_complete(Boolean is_complete) {
		this.is_complete = is_complete;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	
	
	
}
