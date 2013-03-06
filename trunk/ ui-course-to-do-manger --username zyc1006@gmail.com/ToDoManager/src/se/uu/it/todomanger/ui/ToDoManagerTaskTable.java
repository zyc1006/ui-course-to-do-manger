/**
 * 
 */
package se.uu.it.todomanger.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;


import se.uu.it.todomanger.controller.ReminderTimerManager;
import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.model.Task;
import se.uu.it.todomanger.model.NewTaskTableModel;
import se.uu.it.todomanger.model.TaskTableModel;

/**
 * @author Yucheng
 *
 */
public class ToDoManagerTaskTable extends JTable {
	
	private static ToDoManagerTaskTable taskTable = null;
	private static NewTaskTableModel taskTableModel = null;
	
	
	private ToDoManagerTaskTable (TableModel tableModel){
		super(tableModel);
	}
	
	public static ToDoManagerTaskTable getInstance(){
		if(null == taskTable){
			taskTableModel = new NewTaskTableModel(DataSource.taskArrayList);
			taskTable = new ToDoManagerTaskTable(taskTableModel);
			taskTable.hideColumn(0);
			initTaskTable();
		}
		return taskTable;
	}
	
	//if we need more initialization on task table, add it here
	private static void initTaskTable(){
		
		taskTable.setRowSelectionAllowed(true);
		taskTable.setColumnSelectionAllowed(false);
		//Single selection only
		taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskTable.setAutoCreateRowSorter(true);
		taskTable.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int selectedRow = taskTable.getSelectedRow();
					if (selectedRow >= 0) {
						int modelRow = taskTable.getRowSorter().convertRowIndexToModel(selectedRow);
						Task task = ((NewTaskTableModel)taskTable.getModel()).getTask(modelRow);
						ReminderTimerManager.getInstance().resetTaskTimer(task);
					}
				}
				// double click
				else if (e.getClickCount() == 2) {
					int selectedRow = taskTable.getSelectedRow();
					if (selectedRow >= 0) {
					
						int modelRow = taskTable.getRowSorter().convertRowIndexToModel(selectedRow);
						Task task = ((NewTaskTableModel)taskTable.getModel()).getTask(modelRow);
						
						AddEditDialog addEditDialog = new AddEditDialog();
						addEditDialog.ShowEditDialog(task);
						if (addEditDialog.clickedOK()) {
							// Add a task here
							TaskManager tm = TaskManager.getInstance();
							tm.editTask(modelRow, addEditDialog.getTask());
					//		tm.displayTaskByDueDateAsc();
							//add Timer  to monitor
							ReminderTimerManager.getInstance().TimeMonitorTask(addEditDialog.getTask());
						} else {
							System.out.println("cancel");
						}
					}
				}
				super.mouseClicked(e);
				
			}
			
		});
	}
	
	/**
	 * public void displayAllTasksByOrder ({@link ArrayList} taskList, {@link Comparator} comparator)<br>
	 * Display all tasks by specified comparator
	 * 
	 * @param taskList 
	 * A task list includes the tasks to be displayed
	 * @param comparator 
	 * In what order the tasks will be displayed
	 * @see TaskManager
	 */
//	public void displayAllTasksByOrder(ArrayList<Task> taskList, Comparator<Task> comparator){
//
//		taskTableModel.displayAllTasksByOrder(taskList, comparator);
//	}

	private void hideColumn(int index) {
		TableColumn tc = this.getColumnModel().getColumn(index);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setWidth(0);
		tc.setMinWidth(0);
		this.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(0);
		this.getTableHeader().getColumnModel().getColumn(index).setMinWidth(0);
	}
	
	/**
	 * set text of the task table
	 */
	public void setTaskTableText(){
		((NewTaskTableModel)taskTable.getModel()).setTaskTableText();
		taskTable.hideColumn(0);
	}
	
	
	
}
