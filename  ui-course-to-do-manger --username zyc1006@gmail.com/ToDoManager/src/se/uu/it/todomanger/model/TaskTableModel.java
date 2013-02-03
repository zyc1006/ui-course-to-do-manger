package se.uu.it.todomanger.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.table.DefaultTableModel;


/**
 * A task table model which will be linked with an instance of JTable.
 * Task table model defines the column names of a JTable
 * Task table model can add and remove rows from JTable
 * @author Yucheng
 *
 */
public class TaskTableModel extends DefaultTableModel {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Column name of the task table
	 */
	private final static String[] columnName = {"Title", 
										 		"Category",
										 		"Priority",
										 		"Due Date"}; 
	public TaskTableModel(){
		super(columnName, 0);
	}
	/**
	 * Add a task into the table
	 * @param task
	 */
	public void addTaskAsRow(Task task){
		
		Object[] row = {task.getTitle(),
						task.getCategory(),
						task.getPriority(),
						task.getDueDate()};
		super.addRow(row);
	}
	
	public void displayAllTasksByOrder(ArrayList<Task> taskList, Comparator<Task> comparator){
		
		
		Collections.sort(taskList, comparator);
		for(int i = 0; i < taskList.size(); i++){
			addTaskAsRow(taskList.get(i));
		}
		
		
		
	}
	
	
	
	

}
