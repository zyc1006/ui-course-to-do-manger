package se.uu.it.todomanger.controller;

import java.util.ArrayList;

import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.model.NewTaskTableModel;
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
	
//	private HashMap<Integer, Task> taskHashMap;
	private ArrayList<Task> taskArrayList;	
	
	/**A task table instance to handle the display operation on the task table */
	private ToDoManagerTaskTable taskTable = ToDoManagerTaskTable.getInstance();
	
	/**Singleton of TaskManager*/
	private static TaskManager tm = null;

	private TaskManager() {

		taskArrayList = DataSource.taskArrayList;
	}
	/**
	 * Initialize the task manager
	 * @return A singleton of Task Manager
	 * @see Task
	 */
	public static TaskManager getInstance() {
		synchronized (TaskManager.class) {
			if (tm == null) {
				synchronized(TaskManager.class){
					tm = new TaskManager();
				}
			}
		}
		return tm;
		
	}
	
	/**
	 * public void addTask ({@link Task} task)<br>
	 * add a task
	 * @param task a task model
	 */
	public void addTask(Task task) {;
		((NewTaskTableModel)taskTable.getModel()).addTask(task);
	}
	
	/**
	 * get a task by id
	 * @param id
	 * @return task
	 */
//	public Task getTask(int id) {
////		return (Task)taskArrayList.get(id);
//	}

	/**
	 * public void editTask ({@link Task} task)<br>
	 * edit a task
	 * @param task a task model
	 */
	public void editTask(Integer row, Task task) {
		((NewTaskTableModel)taskTable.getModel()).editTask(row, task);
	}
	/**
	 * public void deleteTask ({@link Task} task)<br>
	 * delete a task
	 * @param task a task model
	 */
	public void deleteTask(int row) {
		((NewTaskTableModel)taskTable.getModel()).removeTask(row);
		
	}
	/**
	 * show the tasks according to category id
	 * @param categoryid
	 */
	public void showTaskInCategory(int categoryid){
		((NewTaskTableModel)taskTable.getModel()).setCurrentCategoryId(categoryid);
		((NewTaskTableModel)taskTable.getModel()).showTaskInCategory();
	}

//	/**
//	 * display the tasks by due date in ascending order
//	 */
//	public void displayTaskByDueDateAsc() {
//		taskTable.displayAllTasksByOrder(taskArrayList, new SortByDueDateAsc());
//	}
//	/**
//	 * display the tasks by due date in descending order
//	 */
//	public void displayTaskByDueDateDesc() {
//		taskTable.displayAllTasksByOrder(taskArrayList, new SortByDueDateDesc());
//	}
//	
	//inner class for sorting
//	class SortByDueDateAsc implements Comparator<Task> {
//		public int compare(Task s1, Task s2) {
//			
//			if (s1.getDueDate().after(s2.getDueDate())) {
//				return 1;
//			} else if(s1.getDueDate().equals(s2.getDueDate())){
//				return 0;
//			}
//			else
//				return -1;
//
//		}
//	}
//
//	//inner class for sorting
//	class SortByDueDateDesc implements Comparator<Task> {
//		public int compare(Task s1, Task s2) {		
//			if (s1.getDueDate().before(s2.getDueDate())) {
//				return 1;
//			} else if(s1.getDueDate().equals(s2.getDueDate())){
//				return 0;
//			}else
//				return -1;
//
//		}
//	}
	
	
	/**
	 * generate the id for next added task
	 * @return the next task id
	 */
	
	public int getNextTaskId(){
		int nextid = 0;
		for(int i = 0; i < taskArrayList.size(); i++){
			if(taskArrayList.get(i).getId() >= nextid){
				nextid = taskArrayList.get(i).getId()+1;
			}
		}
		return nextid;
	}
	
	/**
	 * private {@link ArrayList} toArrayList() <br>
	 * Convert hashmap to arraylist
	 * @return an arraylist contains all tasks to be displayed
	 */
//	private ArrayList<Task> toArrayList() {
//		taskArrayList.clear();
//		if (taskHashMap != null) {
//			Iterator<Entry<Integer, Task>> it = taskHashMap.entrySet()
//					.iterator();
//			while (it.hasNext()) {
//				Entry<Integer, Task> entry = (Entry<Integer, Task>) it.next();
//				taskArrayList.add(entry.getValue());
//			}
//		}
//		return taskArrayList;
//	}
	
	
}