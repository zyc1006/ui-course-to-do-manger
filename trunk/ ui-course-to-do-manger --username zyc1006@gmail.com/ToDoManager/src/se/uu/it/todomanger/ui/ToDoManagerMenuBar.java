package se.uu.it.todomanger.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.dao.Savestate;

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
	
	private static ToDoManagerMenuBar MenuBar = null;
	// A ResourceBundle for loading international words/phrases.
	private static LanguageManager resLocale;
	
	// MenuItem in menu bar
	private static JMenu FileMenu;
	private static JMenu TaskMenu;
	private static JMenu HelpMenu;
	private static JMenu LanguageMenu;
	private static JMenuItem OpenItem;
	private static JMenuItem SaveItem;
	private static JMenuItem QuitItem;
	private static JMenuItem EditItem;
	private static JMenuItem DeleteItem;
	private static JMenuItem AddItem;
	private static JMenuItem AboutUsItem;
	private static JMenuItem HelpItem;
	private static JMenuItem GermanItem;
	private static JMenuItem EnglishItem;
	private static JMenuItem SwedishItem;
	private static JMenuItem ChineseItem;
	
	private ToDoManagerMenuBar() {
		
	}

	/**
	 * initMenuBar
	 * @author Shiyu
	 * @author Yucheng
	 * @author Bjorn
	 * 
	 * @description Sets up the menu bar with the words/phrases from the local resource bundle.
	 */
	private static void initMenuBar() {

		// Load the international words/phrases from the resourcebundle.
		try {
//			resLocale = ResourceBundle.getBundle("locale.ToDoManager", 
//		                                         Locale.getDefault());
			resLocale = new LanguageManager();
		} catch (MissingResourceException mre) {
		    System.err.println("res/locale/ToDoManager.properties not found");
		    System.exit(1);
		}
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
		//JPanel panel = new JPanel(new GridLayout(1, 4));
		//panel.setSize(MENUBAR_PANEL_WIDTH, MENUBAR_PANEL_HEIGHT);
		// JMenuBar MenuBar = new JMenuBar();
		//panel.add(MenuBar);
		FileMenu = new JMenu(resLocale.getString("MenuBar_File_Menu"));
		// FileMenu.setFont(new Font("»ªÎÄ²ÊÔÆ",0,30));
		TaskMenu = new JMenu(resLocale.getString("MenuBar_Task_Menu"));
		HelpMenu = new JMenu(resLocale.getString("MenuBar_Help_Menu"));
		LanguageMenu = new JMenu(resLocale.getString("MenuBar_Language_Menu"));
		// HelpMenu.setSize(100, 100);
		// FileMenu.setSize(50, 50);
		MenuBar.add(FileMenu);
		MenuBar.add(TaskMenu);
		MenuBar.add(LanguageMenu);
		MenuBar.add(HelpMenu);
		OpenItem = new JMenuItem(resLocale.getString("MenuBar_Open_File_Option"), KeyEvent.VK_O);
		SaveItem = new JMenuItem(resLocale.getString("MenuBar_Save_File_Option"), KeyEvent.VK_S);
		QuitItem = new JMenuItem(resLocale.getString("MenuBar_Quit_Option"), KeyEvent.VK_Q);
		FileMenu.add(OpenItem);
		FileMenu.addSeparator();
		FileMenu.add(SaveItem);
		FileMenu.addSeparator();
		FileMenu.add(QuitItem);

		EditItem = new JMenuItem(resLocale.getString("MenuBar_Edit_Task_Option"), KeyEvent.VK_E);
		DeleteItem = new JMenuItem(resLocale.getString("MenuBar_Delete_Task_Option"), KeyEvent.VK_T);
		AddItem = new JMenuItem(resLocale.getString("MenuBar_Add_Task_Option"), KeyEvent.VK_A);
		TaskMenu.add(AddItem);
		TaskMenu.addSeparator();
		TaskMenu.add(EditItem);
		TaskMenu.addSeparator();
		TaskMenu.add(DeleteItem);

		AboutUsItem = new JMenuItem(resLocale.getString("MenuBar_About_Option"), KeyEvent.VK_A);
		HelpItem = new JMenuItem(resLocale.getString("MenuBar_Help_Option"), KeyEvent.VK_H);
		HelpMenu.add(HelpItem);
		HelpMenu.addSeparator();
		HelpMenu.add(AboutUsItem);
		
		GermanItem = new JMenuItem(resLocale.getString("MenuBar_German_Option") , KeyEvent.VK_G);
		EnglishItem = new JMenuItem(resLocale.getString("MenuBar_English_Option"), KeyEvent.VK_E);
		SwedishItem = new JMenuItem(resLocale.getString("MenuBar_Swedish_Option"), KeyEvent.VK_W);
		ChineseItem = new JMenuItem(resLocale.getString("MenuBar_Chinese_Option"), KeyEvent.VK_C);
		
		LanguageMenu.add(EnglishItem);		
		LanguageMenu.addSeparator();
		LanguageMenu.add(GermanItem);
		LanguageMenu.addSeparator();
		LanguageMenu.add(SwedishItem);
		LanguageMenu.addSeparator();
		LanguageMenu.add(ChineseItem);

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
				int val = JOptionPane.showConfirmDialog(
						null,
						resLocale
								.getString("MainWindow_ConfirmExit_Message"),
						resLocale.getString("MainWindow_ConfirmExit_Title"),
						0);
				if (val == JOptionPane.OK_OPTION) {
					
					//save status
					MainWindow main = (MainWindow)MenuBar.getTopLevelAncestor();
					//JFrame main = (JFrame) e.getSource();
					Dimension size = main.getSize();
					Point location = main.getLocationOnScreen();
					Savestate save = new Savestate();
					
					save.saveLocation(size, location);
					System.exit(0);
				}
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
		
		//German
		GermanItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.setLocale(LanguageManager.GERMAN);
				MainWindow mainWindow = (MainWindow)MenuBar.getTopLevelAncestor();
				mainWindow.resetAllLanguage();

				
			}
		});
		
		//English
		EnglishItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.setLocale(LanguageManager.ENGLISH);
				MainWindow mainWindow = (MainWindow)MenuBar.getTopLevelAncestor();
				mainWindow.resetAllLanguage();
			}
		});

		// Swedish
		SwedishItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.setLocale(LanguageManager.SWEDISH);
				MainWindow mainWindow = (MainWindow) MenuBar
						.getTopLevelAncestor();
				mainWindow.resetAllLanguage();
			}
		});

		// Chinese
		ChineseItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.setLocale(LanguageManager.CHINESE);
				MainWindow mainWindow = (MainWindow) MenuBar
						.getTopLevelAncestor();
				mainWindow.resetAllLanguage();
			}
		});
		
		
		
		// Help
		HelpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelpDialog hd = new HelpDialog();
			}
		});
		// About us
		AboutUsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutUsDialog aud = new AboutUsDialog();
			}
		});

		// return panel;
	}

	public void setMenuBarText(){
		//resLocale = LanguageManager.getDefaultResourceBundle();
		FileMenu.setText(resLocale.getString("MenuBar_File_Menu"));
		TaskMenu.setText(resLocale.getString("MenuBar_Task_Menu"));
		HelpMenu.setText(resLocale.getString("MenuBar_Help_Menu"));
		OpenItem.setText(resLocale.getString("MenuBar_Open_File_Option"));
		SaveItem.setText(resLocale.getString("MenuBar_Save_File_Option"));
		QuitItem.setText(resLocale.getString("MenuBar_Quit_Option"));
		EditItem.setText(resLocale.getString("MenuBar_Edit_Task_Option"));
		DeleteItem.setText(resLocale.getString("MenuBar_Delete_Task_Option"));
		AddItem.setText(resLocale.getString("MenuBar_Add_Task_Option"));
		AboutUsItem.setText(resLocale.getString("MenuBar_About_Option"));
		HelpItem.setText(resLocale.getString("MenuBar_Help_Option"));
		LanguageMenu.setText(resLocale.getString("MenuBar_Language_Menu"));
		GermanItem.setText(resLocale.getString("MenuBar_German_Option"));
		EnglishItem.setText(resLocale.getString("MenuBar_English_Option"));
		SwedishItem.setText(resLocale.getString("MenuBar_Swedish_Option"));
		ChineseItem.setText(resLocale.getString("MenuBar_Chinese_Option"));
	}
	
	static public ToDoManagerMenuBar getInstance() {
		if (null == MenuBar) {
			MenuBar = new ToDoManagerMenuBar();
			initMenuBar();
		}
		return MenuBar;
	}

}
