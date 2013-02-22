package se.uu.it.todomanger.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import se.uu.it.todomanger.model.Task;
import se.uu.it.todomanger.ui.ToDoManagerTaskTable;

/**
 * A singleton TaskManger used for creating, editing and deleting tasks.
 * It can also sort tasks and display them in the task table
 * @author Yucheng
 * 
 */
public class TaskManager {
	
//----------------------------------------------------------------	
	
	
//---------------------------------------------------	
	
	
	/**A hashmap stores all the task*/
	private HashMap<Integer, Task> taskHashMap = new HashMap<Integer, Task>();
	
	/**An arraylist stores all the task, used for sorting */
	private ArrayList<Task> taskArrayList = new ArrayList<Task>();
	
	public ArrayList<Task> getTaskArrayList() {
		return taskArrayList;
	}
	public void setTaskArrayList(ArrayList<Task> taskArrayList) {
		this.taskArrayList = taskArrayList;
		toHashMap();
	}

	/**A task table instance to handle the display operation on the task table */
	private ToDoManagerTaskTable taskTable = ToDoManagerTaskTable.getInstance();
	
	/**Singleton of TaskManager*/
	private static TaskManager tm = null;

	private TaskManager() {
		
		
	}
	/**
	 * Initialize the task manager
	 * @return A singleton of Task Manager
	 * @see Task
	 */
	public static TaskManager getInstance() {
		if (tm == null) {
			tm = new TaskManager();
		}
		return tm;
	}
	
	/**
	 * public void addTask ({@link Task} task)<br>
	 * add a task
	 * @param task a task model
	 */
	public void addTask(Task task) {
		taskHashMap.put(task.getId(), task);
		toArrayList();
	}

	/**
	 * public void editTask ({@link Task} task)<br>
	 * edit a task
	 * @param task a task model
	 */
	public void editTask(Task task) {
		taskHashMap.put(task.getId(), task);
		toArrayList();
	}
	/**
	 * public void deleteTask ({@link Task} task)<br>
	 * delete a task
	 * @param task a task model
	 */
	public void deleteTask(Task task) {
		taskHashMap.remove(task.getId());
		toArrayList();
	}

	/**
	 * private {@link ArrayList} toArrayList() <br>
	 * Convert hashmap to arraylist
	 * @return an arraylist contains all tasks to be displayed
	 */
	private ArrayList<Task> toArrayList() {
		taskArrayList.clear();
		if (taskHashMap != null) {
			Iterator<Entry<Integer, Task>> it = taskHashMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<Integer, Task> entry = (Entry<Integer, Task>) it.next();
				taskArrayList.add(entry.getValue());
			}
		}
		return taskArrayList;
	}
	
	private HashMap<Integer,Task> toHashMap(){
		taskHashMap.clear();
		for(Task task: taskArrayList){
			taskHashMap.put(task.getId(), task);
		}
		return taskHashMap;
	}

	/**
	 * display the tasks by due date in ascending order
	 */
	public void displayTaskByDueDateAsc() {
		taskTable.displayAllTasksByOrder(taskArrayList, new SortByDueDateAsc());
	}
	/**
	 * display the tasks by due date in descending order
	 */
	public void displayTaskByDueDateDesc() {
		taskTable.displayAllTasksByOrder(taskArrayList, new SortByDueDateDesc());
	}
	
	//inner class for sorting
	class SortByDueDateAsc implements Comparator<Task> {
		public int compare(Task s1, Task s2) {
			
			System.out.println(s1.getDueDate().toLocaleString());
			System.out.println(s2.getDueDate().toLocaleString());
			System.out.println(s1.getDueDate().after(s2.getDueDate()));
			System.out.println(s1.getDueDate().equals(s2.getDueDate()));
			if (s1.getDueDate().after(s2.getDueDate())) {
				return 1;
			} else if(s1.getDueDate().equals(s2.getDueDate())){
				return 0;
			}
			else
				return -1;

		}
	}

	//inner class for sorting
	class SortByDueDateDesc implements Comparator<Task> {
		public int compare(Task s1, Task s2) {		
			if (s1.getDueDate().before(s2.getDueDate())) {
				return 1;
			} else if(s1.getDueDate().equals(s2.getDueDate())){
				return 0;
			}else
				return -1;

		}
	}
	
	public int getNextTaskId(){
		int nextid = 0;
		for(int i = 0; i < taskArrayList.size(); i++){
			if(taskArrayList.get(i).getId() >= nextid){
				nextid = taskArrayList.get(i).getId()+1;
			}
		}
		return nextid;
	}
}