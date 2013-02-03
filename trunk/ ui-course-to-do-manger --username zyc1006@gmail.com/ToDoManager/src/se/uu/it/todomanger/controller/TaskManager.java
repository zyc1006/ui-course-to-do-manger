package se.uu.it.todomanger.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import se.uu.it.todomanger.model.Task;
import se.uu.it.todomanger.model.TaskTableModel;

/**
 * A singleton TaskManger used for creating, editing and deleting tasks.
 * It can also sort tasks and display them in the task table
 * @author Yucheng
 * 
 */
public class TaskManager {
	
	//A hashmap stores all the task
	private HashMap<Integer, Task> taskHashMap = new HashMap<Integer, Task>();
	
	//An arraylist stores all the task, used for sorting
	private ArrayList<Task> taskArrayList = new ArrayList<Task>();
	
	//An instance of TableModel, linked with a JTable
	public TaskTableModel  taskTableModel = new TaskTableModel();
	
	//Singleton of TaskManager
	private static TaskManager tm = null;

	private TaskManager() {

	}

	public static TaskManager getInstance() {
		if (tm == null) {
			tm = new TaskManager();
		}
		return tm;
	}

	public void addTask(Task task) {
		taskHashMap.put(task.getId(), task);
		toArrayList();
	}

	public void editTask(Task task) {
		taskHashMap.put(task.getId(), task);
		toArrayList();
	}

	public void deleteTask(Task task) {
		taskHashMap.remove(task.getId());
		toArrayList();
	}

	//Convert hashmap to arraylist
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

	public void displayTaskByDueDateAsc() {
		taskTableModel.displayAllTasksByOrder(taskArrayList, new SortByDueDateAsc());
	}
	public void displayTaskByDueDatDesc() {
		taskTableModel.displayAllTasksByOrder(taskArrayList, new SortByDueDateDesc());
	}
	
	//inner class for sorting
	class SortByDueDateAsc implements Comparator<Task> {
		public int compare(Task s1, Task s2) {
			
			if (s1.getDueDate().after(s2.getDueDate())) {
				return 1;
			} else {
				return 0;
			}

		}
	}

	//inner class for sorting
	class SortByDueDateDesc implements Comparator<Task> {
		public int compare(Task s1, Task s2) {
			
			if (s1.getDueDate().before(s2.getDueDate())) {
				return 1;
			} else {
				return 0;
			}

		}
	}
}