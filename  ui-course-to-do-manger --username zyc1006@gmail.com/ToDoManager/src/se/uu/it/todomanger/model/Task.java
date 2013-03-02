package se.uu.it.todomanger.model;

import java.util.Date;


/**
 * A task model
 * @author Yucheng
 *
 */
public class Task {	
	
	protected Integer id;	
	
	/**
	 * Default constructor
	 */
	public Task(){};
	
	/**
	 * Constructor
	 * @param id
	 * @param title
	 * @param dueDate
	 * @param category
	 * @param description
	 * @param priority
	 * @param completed
	 */
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
	protected Integer category;
	protected String description;
	protected Integer priority;
	protected boolean completed;

	/**
	 * returns the task id
	 * @return task id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * sets the task id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * returns the task title
	 * @return task title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * sets the task title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * returns the due date of the task
	 * @return task due date
	 */
	public Date getDueDate() {
		return dueDate;
	}
	
	/**
	 * sets the due date for the task
	 * @param dueDate
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * returns the category id of a task
	 * @return category id
	 */
	public Integer getCategory() {
		return category;
	}
	
	/**
	 * sets the category id for a task
	 * @param category
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}
	
	/**
	 * returns the description of a task
	 * @return task description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * sets the description of a task
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * returns the task priority
	 * @return task priority
	 */
	public Integer getPriority() {
		return priority;
	}
	
	/**
	 * sets the task priority
	 * @param priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	/**
	 * returns whether the task is completed
	 * @return boolean true if the task is completed and false if the task is not completed
	 */
	public boolean isCompleted() {
		return completed;
	}
	
	/**
	 * sets whether the task is completed
	 * @param true if the task is completed and false if the task is not completed
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
