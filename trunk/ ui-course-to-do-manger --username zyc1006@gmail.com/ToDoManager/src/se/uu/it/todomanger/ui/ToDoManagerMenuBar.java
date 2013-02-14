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

import se.uu.it.todomanger.controller.TaskManager;

/**
 * A singleton of menu bar for ToDoManager
 * 
 * @author Shiyu
 * @author Yucheng
 * 
 */
public class ToDoManagerMenuBar extends JMenuBar {

	// Window  data
	public static final int MENUBAR_PANEL_WIDTH = 80;
	public static final int MENUBAR_PANEL_HEIGHT = 80;
	
	// Language data
	public static final String MENUBAR_FILE_MENU = "File";
	public static final String MENUBAR_TASK_MENU = "Task";
	public static final String MENUBAR_HELP_MENU = "Help";
	public static final String MENUBAR_OPEN_FILE_OPTION = "Open file";
	public static final String MENUBAR_SAVE_FILE_OPTION = "Save file";
	public static final String MENUBAR_QUIT_OPTION = "Quit";
	public static final String MENUBAR_ADD_TASK_OPTION = "Add task";
	public static final String MENUBAR_EDIT_TASK_OPTION = "Edit task";
	public static final String MENUBAR_DELETE_TASK_OPTION = "Delete task";
	public static final String MENUBAR_ABOUT_OPTION = "About us";
	public static final String MENUBAR_HELP_OPTION = "Help";
	
	private static ToDoManagerMenuBar MenuBar = null;

	private ToDoManagerMenuBar() {

	}

	private static void initMenuBar() {

		/*
		 * JMenu fileMenu = new JMenu("File"); JMenu taskMenu = new
		 * JMenu("Task"); JMenu helpMenu = new JMenu("Help");
		 * 
		 * JMenuItem loadMenuItem = new JMenuItem("Load",KeyEvent.VK_L);
		 * JMenuItem saveMenuItem = new JMenuItem("Save",KeyEvent.VK_S);
		 * JMenuItem exitMenuItem = new JMenuItem("Exit",KeyEvent.VK_X);
		 * JMenuItem createMenuItem = new JMenuItem("Create",KeyEvent.VK_C);
		 * JMenuItem editMenuItem = new JMenuItem("Edit",KeyEvent.VK_E);
		 * JMenuItem deleteMenuItem = new JMenuItem("Delete",KeyEvent.VK_D);
		 * 
		 * fileMenu.add(loadMenuItem); fileMenu.add(saveMenuItem);
		 * fileMenu.add(exitMenuItem); taskMenu.add(createMenuItem);
		 * taskMenu.add(editMenuItem); taskMenu.add(deleteMenuItem);
		 * 
		 * menuBar.add(fileMenu); menuBar.add(taskMenu); menuBar.add(helpMenu);
		 */
		// to create MenuPanel
		JPanel panel = new JPanel(new GridLayout(1, 3));
		panel.setSize(MENUBAR_PANEL_WIDTH, MENUBAR_PANEL_HEIGHT);
		// JMenuBar MenuBar = new JMenuBar();
		panel.add(MenuBar);
		JMenu FileMenu = new JMenu(MENUBAR_FILE_MENU);
		// FileMenu.setFont(new Font("»ªÎÄ²ÊÔÆ",0,30));
		JMenu TaskMenu = new JMenu(MENUBAR_TASK_MENU);
		JMenu HelpMenu = new JMenu(MENUBAR_HELP_MENU);
		// HelpMenu.setSize(100, 100);
		// FileMenu.setSize(50, 50);
		MenuBar.add(FileMenu);
		MenuBar.add(TaskMenu);
		MenuBar.add(HelpMenu);
		JMenuItem OpenItem = new JMenuItem(MENUBAR_OPEN_FILE_OPTION, KeyEvent.VK_O);
		JMenuItem SaveItem = new JMenuItem(MENUBAR_SAVE_FILE_OPTION, KeyEvent.VK_S);
		JMenuItem QuitItem = new JMenuItem(MENUBAR_QUIT_OPTION, KeyEvent.VK_Q);
		FileMenu.add(OpenItem);
		FileMenu.addSeparator();
		FileMenu.add(SaveItem);
		FileMenu.addSeparator();
		FileMenu.add(QuitItem);

		JMenuItem EditItem = new JMenuItem(MENUBAR_EDIT_TASK_OPTION, KeyEvent.VK_E);
		JMenuItem DeleteItem = new JMenuItem(MENUBAR_DELETE_TASK_OPTION, KeyEvent.VK_T);
		JMenuItem AddItem = new JMenuItem(MENUBAR_ADD_TASK_OPTION, KeyEvent.VK_A);
		TaskMenu.add(AddItem);
		TaskMenu.addSeparator();
		TaskMenu.add(EditItem);
		TaskMenu.addSeparator();
		TaskMenu.add(DeleteItem);

		JMenuItem AboutUsItem = new JMenuItem(MENUBAR_ABOUT_OPTION, KeyEvent.VK_A);
		JMenuItem HelpItem = new JMenuItem(MENUBAR_HELP_OPTION, KeyEvent.VK_H);
		HelpMenu.add(HelpItem);
		HelpMenu.addSeparator();
		HelpMenu.add(AboutUsItem);

		// add ActionListener methods
		// Open File
		OpenItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		// Save File
		SaveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		// Quit
		QuitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		// Edit task
		EditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		// delete task
		DeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		// Add task
		AddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEditDialog addEditDialog = new AddEditDialog();
				addEditDialog.ShowAddDialog();
				if(addEditDialog.clickedOK())
				{
					//Add a task here
					TaskManager tm = TaskManager.getInstance();
					tm.addTask(addEditDialog.getTask());
					tm.displayTaskByDueDateAsc();
				}
			}
		});
		// Help
		HelpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		// About us
		AboutUsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		// return panel;
	}

	static public ToDoManagerMenuBar getInstance() {
		if (null == MenuBar) {
			MenuBar = new ToDoManagerMenuBar();
			initMenuBar();
		}
		return MenuBar;
	}

}
