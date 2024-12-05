package com.ss.studysystem.Model;

import java.time.LocalDateTime;

public class Assignment_Submissions {
	private int submission_id;
	private Assignments assignment;
	private Users user;
	private String assignment_file;
	private LocalDateTime submitted_at;
	
	public Assignment_Submissions() {
	}

	public int getSubmission_id() {
		return submission_id;
	}

	public void setSubmission_id(int submission_id) {
		this.submission_id = submission_id;
	}

	public Assignments getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignments assignment) {
		this.assignment = assignment;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getAssignment_file() {
		return assignment_file;
	}

	public void setAssignment_file(String assignment_file) {
		this.assignment_file = assignment_file;
	}

	public LocalDateTime getSubmitted_at() {
		return submitted_at;
	}

	public void setSubmitted_at(LocalDateTime submitted_at) {
		this.submitted_at = submitted_at;
	}
	
	
	
}
