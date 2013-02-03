package se.uu.it.todomanger.ui;


import java.awt.MenuItem;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A singleton of menu bar for ToDoManager
 * @author Yucheng
 *
 */
public class ToDoManagerMenuBar extends JMenuBar {
	
	private static ToDoManagerMenuBar menuBar = null;
	
	private ToDoManagerMenuBar() {
		
	}
	
	private static void initMenuBar(){
		
		JMenu fileMenu = new JMenu("File");
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
		menuBar.add(helpMenu);
		
	}
	
	static public ToDoManagerMenuBar getInstance(){
		if(null == menuBar){
			menuBar = new ToDoManagerMenuBar();
			initMenuBar();
		}
		return menuBar;
	}
	
	
}
