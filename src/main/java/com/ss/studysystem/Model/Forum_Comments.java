package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Forum_Comments {
    private int comment;
    private Forums forum;
    private Users user;
    private String comment_text;
    private String forum_file;
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

    public String getForum_file() {
        return forum_file;
    }

    public void setForum_file(String forum_file) {
        this.forum_file = forum_file;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }


}
