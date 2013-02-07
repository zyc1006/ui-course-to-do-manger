package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.model.Task;
/**
 * 
 * @author Shiyu
 */
public class MainShowPanel {
	
private static MainShowPanel Panel = null;
	
	public JPanel initMainShow()
	{
		JPanel panel = new JPanel(new GridLayout(1,3));
		JPanel panel_left = new JPanel(new GridLayout());
		JPanel panel_middle = new JPanel(new BorderLayout());
		JPanel panel_right = new JPanel(new GridLayout());
		
		panel_left.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_middle.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_right.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel.add(panel_left);
		panel.add(panel_middle);
		panel.add(panel_right);
		
		//create catalog 
		ShowCatalog sc = new ShowCatalog();
		panel_left.add(sc.initJTree());
		

		
//------------------testing code-------------------------------		
		TaskManager tm = TaskManager.getInstance();
		tm.addTask(new Task(1, "first", new Date(2012-1900, 12-1, 1), 1, "hello", 5, true));
		tm.addTask(new Task(2, "first", new Date(2012-1900, 4-1, 12), 2, "hello", 4, true));
		tm.addTask(new Task(3, "first", new Date(2011-1900, 5-1, 18), 3, "hello", 3, true));
		tm.addTask(new Task(4, "first", new Date(2012-1900, 6-1, 13), 4, "hello", 2, true));
		
		JScrollPane taskPane = new JScrollPane(ToDoManagerTaskTable.getInstance(), 
					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tm.displayTaskByDueDateDesc();
//------------------------------------------------------------------		
		panel_middle.add(taskPane);
		//create the blank panel to add additional function in the future
		AdditionRequire ar = new AdditionRequire();
		panel_right.add(ar.initAdditionRequire());

		return panel;
	}
	
}
