package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Messages {
	private int message;
	private Chatrooms chatroom;
	private Users user;
	private String message_text;
	private String message_file;
	private LocalDateTime created_at;
	
	public Messages() {
	}

	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public Chatrooms getChatroom() {
		return chatroom;
	}

	public void setChatroom(Chatrooms chatroom) {
		this.chatroom = chatroom;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getMessage_text() {
		return message_text;
	}

	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}

	public String getMessage_file() {
		return message_file;
	}

	public void setMessage_file(String message_file) {
		this.message_file = message_file;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	
	
}
