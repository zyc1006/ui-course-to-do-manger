package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
/**
 * 
 * @author Shiyu
 */
public class MainShowPanel 
{
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
		
		//create subpanel to show the toolbar and taskshow
		//ToolBar tb = new ToolBar();
		//panel_middle.add(BorderLayout.NORTH , ToDoManagerToolBar.getInstance());
		//create showtask
		ShowTask st = new ShowTask();
		panel_middle.add(st.initShowTask());
		
		//create the blank panel to add additional function in the future
		AdditionRequire ar = new AdditionRequire();
		panel_right.add(ar.initAdditionRequire());

		return panel;
	}
	
}
