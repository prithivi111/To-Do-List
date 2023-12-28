package taskmodel;
//Develop application of a to-do list where we can add tasks for a particular date with duration 
//and description of the task and we can search a particular date 
//and application show us the tasks for that particular date.

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


//My understanding
//Here tasks become an object which goes into the DB, and will have properties like date, duration and description.
//1st method will be add tasks for a particular date with duration and description on the basis of date
//2nd method will be search and display, on the basis of date selected and will show the task details.
//So, lets write the program now--

@Entity
public class Task {
	
	@Id
	@Column (name ="task_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int taskId;
	
	@Column (name ="task_date")
	private Date taskDate;
	
	private int duration;
	private String description;
	

	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	

}
