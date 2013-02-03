package se.uu.it.todomanger.model;

import java.util.Date;


/**
 * A task model
 * @author Yucheng
 *
 */
public class Task {
	
	protected Integer id;
	
	public Task(Integer id, String title, Date dueDate, int category,
			String description, int priority, boolean completed) {
		super();
		this.id = id;
		this.title = title;
		this.dueDate = dueDate;
		this.category = category;
		this.description = description;
		this.priority = priority;
		this.completed = completed;
	}
	protected String title;
	protected Date dueDate;
	protected int category;
	protected String description;
	protected int priority;
	protected boolean completed;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
