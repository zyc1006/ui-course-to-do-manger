package se.uu.it.todomanger.ui;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.model.Task;
/**
 * 
 * @author Shiyu
 */
public class ShowTask 
{
	public JTable initShowTask()
	{
		TaskManager tm = TaskManager.getInstance();
		tm.addTask(new Task(1, "first", new Date(2012-1900, 12-1, 1), 1, "hello", 5, true));
		tm.addTask(new Task(2, "first", new Date(2012-1900, 4-1, 12), 2, "hello", 4, true));
		tm.addTask(new Task(3, "first", new Date(2011-1900, 5-1, 18), 3, "hello", 3, true));
		tm.addTask(new Task(4, "first", new Date(2012-1900, 6-1, 13), 4, "hello", 2, true));
		JTable taskTable = new JTable(tm.taskTableModel);
		JScrollPane taskPane = new JScrollPane(taskTable, 
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tm.displayTaskByDueDatDesc();
		
		return taskTable;
	}
}
