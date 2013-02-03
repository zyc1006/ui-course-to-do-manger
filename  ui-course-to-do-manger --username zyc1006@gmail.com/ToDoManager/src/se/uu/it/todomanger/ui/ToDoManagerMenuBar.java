package se.uu.it.todomanger.ui;


import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * A singleton of menu bar for ToDoManager
 * @author Shiyu
 * @author Yucheng
 *
 */
public class ToDoManagerMenuBar extends JMenuBar {
	
	private static ToDoManagerMenuBar MenuBar = null;
	
	private ToDoManagerMenuBar() {
		
	}
	
	private static void initMenuBar(){
		
		/*JMenu fileMenu = new JMenu("File");
		JMenu taskMenu = new JMenu("Task");
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem loadMenuItem = new JMenuItem("Load",KeyEvent.VK_L);
		JMenuItem saveMenuItem = new JMenuItem("Save",KeyEvent.VK_S);
		JMenuItem exitMenuItem = new JMenuItem("Exit",KeyEvent.VK_X);
		JMenuItem createMenuItem = new JMenuItem("Create",KeyEvent.VK_C);
		JMenuItem editMenuItem = new JMenuItem("Edit",KeyEvent.VK_E);
		JMenuItem deleteMenuItem = new JMenuItem("Delete",KeyEvent.VK_D);
		
		fileMenu.add(loadMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exitMenuItem);
		taskMenu.add(createMenuItem);
		taskMenu.add(editMenuItem);
		taskMenu.add(deleteMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(taskMenu);
		menuBar.add(helpMenu);*/
		//to create MenuPanel
			JPanel panel = new JPanel(new GridLayout(1,3));
			panel.setSize(80,80);
			//JMenuBar MenuBar = new JMenuBar();
			panel.add(MenuBar);
			JMenu FileMenu = new JMenu("File");
			//FileMenu.setFont(new Font("»ªÎÄ²ÊÔÆ",0,30));
			JMenu TaskMenu = new JMenu("Task");
			JMenu HelpMenu = new JMenu("Help");
			//HelpMenu.setSize(100, 100);
			//FileMenu.setSize(50, 50);
			MenuBar.add(FileMenu);
			MenuBar.add(TaskMenu);
			MenuBar.add(HelpMenu);
			JMenuItem OpenItem = new JMenuItem("Open File",KeyEvent.VK_O);
			JMenuItem SaveItem = new JMenuItem("Save File",KeyEvent.VK_S);
			JMenuItem QuitItem = new JMenuItem("Quit",KeyEvent.VK_Q);
			FileMenu.add(OpenItem);
			FileMenu.addSeparator();
			FileMenu.add(SaveItem);
			FileMenu.addSeparator();
			FileMenu.add(QuitItem);
			
			JMenuItem EditItem = new JMenuItem("Edit Task",KeyEvent.VK_E);
			JMenuItem DeleteItem = new JMenuItem("Delete Task",KeyEvent.VK_T);
			JMenuItem AddItem = new JMenuItem("Add Task",KeyEvent.VK_A);
			TaskMenu.add(AddItem);
			TaskMenu.addSeparator();
			TaskMenu.add(EditItem);
			TaskMenu.addSeparator();
			TaskMenu.add(DeleteItem);
			
			JMenuItem AboutUsItem = new JMenuItem("About Us",KeyEvent.VK_A);
			JMenuItem HelpItem = new JMenuItem("Help",KeyEvent.VK_H);
			HelpMenu.add(HelpItem);
			HelpMenu.addSeparator();
			HelpMenu.add(AboutUsItem);

			// add ActionListener methods
			//Open File 
			OpenItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			//Save File
			SaveItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			//Quit
			QuitItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			//Edit task
			EditItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			//delete task
			DeleteItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			//Add task
			AddItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			//Help 
			HelpItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			//About us
			AboutUsItem.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					
				}
			});
			
			//return panel;
	}
	
	static public ToDoManagerMenuBar getInstance(){
		if(null == MenuBar){
			MenuBar = new ToDoManagerMenuBar();
			initMenuBar();
		}
		return MenuBar;
	}
	
	
}
