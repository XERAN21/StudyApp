package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Mentions {
	private int mention;
	private Users mentioning_user;
	private Users mentioned_user;
	private String content;
	private LocalDateTime create_at;
	
	public Mentions() {
	}
	
	public int getMention() {
		return mention;
	}

	public void setMention(int mention) {
		this.mention = mention;
	}

	public Users getMentioning_user() {
		return mentioning_user;
	}

	public void setMentioning_user(Users mentioning_user) {
		this.mentioning_user = mentioning_user;
	}
	public Users getMentioned_user() {
		return mentioned_user;
	}

	public void setMentioned_user(Users mentioned_user) {
		this.mentioned_user = mentioned_user;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreate_at() {
		return create_at;
	}

	public void setCreate_at(LocalDateTime create_at) {
		this.create_at = create_at;
	}
	
}
