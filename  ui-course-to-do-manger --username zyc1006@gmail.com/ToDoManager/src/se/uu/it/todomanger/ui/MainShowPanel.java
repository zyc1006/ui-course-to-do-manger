package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		//JPanel panel = new JPanel(new GridLayout(1,3));
		GridBagLayout gbl = new GridBagLayout();
		JPanel panel = new JPanel(gbl);
		JPanel panel_left = new JPanel(new GridLayout());
		JPanel panel_middle = new JPanel(new BorderLayout());
		JPanel panel_right = new JPanel(new GridLayout());
		
		panel_left.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_middle.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_right.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		GridBagConstraints c = new GridBagConstraints();
		//left panel
		c.gridx = 0; c.gridy = 0;
		c.gridwidth = 1; c.gridheight = GridBagConstraints.REMAINDER; 
		c.weightx = 0.1; c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 120;
		
		gbl.setConstraints(panel_left, c);	
		panel.add(panel_left);
		
		c.gridx = 1; c.gridy = 0;
		c.gridwidth = GridBagConstraints.REMAINDER; 
		c.gridheight = GridBagConstraints.REMAINDER; 
		c.weightx = 1.0; c.weighty = 1.0;
		
		c.fill = GridBagConstraints.BOTH;
		gbl.setConstraints(panel_middle, c);	
		panel.add(panel_middle);
		//panel.add(panel_right);
		
		//create catalog 
		ShowCatalog sc = new ShowCatalog();
		panel_left.add(sc.initJTree());
		
		JScrollPane taskPane = new JScrollPane(ToDoManagerTaskTable.getInstance(), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
//------------------testing code-------------------------------		
//		TaskManager tm = TaskManager.getInstance();
//		tm.addTask(new Task(1, "first", new Date(2012-1900, 12-1, 1), 1, "hello", 5, true));
//		tm.addTask(new Task(2, "first", new Date(2012-1900, 4-1, 12), 2, "hello", 4, true));
//		tm.addTask(new Task(3, "first", new Date(2011-1900, 5-1, 18), 3, "hello", 3, true));
//		tm.addTask(new Task(4, "first", new Date(2012-1900, 6-1, 13), 4, "hello", 2, true));
		
		
//		tm.displayTaskByDueDateDesc();
//------------------------------------------------------------------		
		panel_middle.add(taskPane);
		//create the blank panel to add additional function in the future
		AdditionRequire ar = new AdditionRequire();
		panel_right.add(ar.initAdditionRequire());

		return panel;
	}
	
}
