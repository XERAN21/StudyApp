package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Goals {
	private int goal;
	private Users user;
	private String description;
	private LocalDate target_date;
	private String[] status = {"not_started", "in_progress", "completed"};
	private LocalDateTime created_at;
	
	public Goals() {
	}
	
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getTarget_date() {
		return target_date;
	}
	public void setTarget_date(LocalDateTime target_date) {
		this.target_date = target_date;
	}
	public String[] getStatus() {
		return status;
	}
	public void setStatus(String[] status) {
		this.status = status;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	
	
}
