package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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
	
	/**
	 * Initialize main panel
	 * @return the initailize main panel
	 */
	public JPanel initMainShow()
	{
		
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
		c.ipadx = 80;
		
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
		panel_left.add(sc.init());
		
		//Task table
		JScrollPane taskPane = new JScrollPane(ToDoManagerTaskTable.getInstance(), 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		

		panel_middle.add(taskPane);
		//create the blank panel to add additional function in the future
		AdditionRequire ar = new AdditionRequire();
		panel_right.add(ar.initAdditionRequire());

		return panel;
	}
	
}
