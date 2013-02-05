/**
 * 
 */
package se.uu.it.todomanger.ui;

import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.table.TableModel;


import se.uu.it.todomanger.model.Task;
import se.uu.it.todomanger.model.TaskTableModel;

/**
 * @author Yucheng
 *
 */
public class ToDoManagerTaskTable extends JTable {
	
	private static ToDoManagerTaskTable taskTable = null;
	private static TaskTableModel taskTableModel = null;
	
	
	private ToDoManagerTaskTable (TableModel tableModel){
		super(tableModel);
	}
	
	public static ToDoManagerTaskTable getInstance(){
		if(null == taskTable){
			taskTableModel = new TaskTableModel();
			taskTable = new ToDoManagerTaskTable(taskTableModel);
			initTaskTable();
		}
		return taskTable;
	}
	
	private static void initTaskTable(){
		//if we need more initialization on task table, add it here
	}
	
	/**Display all tasks by specified comparator
	 * 
	 * @param taskList A task list includes the tasks to be displayed
	 * @param comparator In what order the tasks will be displayed
	 */
	public void displayAllTasksByOrder(ArrayList<Task> taskList, Comparator<Task> comparator){
		taskTableModel.displayAllTasksByOrder(taskList, comparator);
	}
	
}
