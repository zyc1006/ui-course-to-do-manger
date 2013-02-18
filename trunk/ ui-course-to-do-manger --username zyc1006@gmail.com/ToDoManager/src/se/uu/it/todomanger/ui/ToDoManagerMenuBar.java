package se.uu.it.todomanger.ui;

import java.awt.GridLayout;
import java.awt.MenuItem;
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
import javax.swing.JPanel;
import javax.swing.UIManager;

import se.uu.it.todomanger.controller.LanguageManager;
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
	
	private static ToDoManagerMenuBar MenuBar = null;
	// A ResourceBundle for loading international words/phrases.
	private static ResourceBundle resLocale;
	
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
			resLocale = LanguageManager.getDefaultResourceBundle();
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
		LanguageMenu = new JMenu("Language");//(resLocale.getString("MenuBar_Language_Menu"));
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
		
		GermanItem = new JMenuItem("German" , KeyEvent.VK_G);//(resLocale.getString("MenuBar_German_Option"), KeyEvent.VK_G);
		EnglishItem = new JMenuItem("English", KeyEvent.VK_E);//(resLocale.getString("MenuBar_English_Option"), KeyEvent.VK_E);
		SwedishItem = new JMenuItem("Swedish", KeyEvent.VK_W);
		
		LanguageMenu.add(EnglishItem);		
		LanguageMenu.addSeparator();
		LanguageMenu.add(GermanItem);
		LanguageMenu.addSeparator();
		LanguageMenu.add(SwedishItem);

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
		
		//German
		GermanItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.resetResourceBundle(LanguageManager.GERMAN);
				MainWindow mainWindow = (MainWindow)MenuBar.getTopLevelAncestor();
				mainWindow.resetAllLanguage();

				
			}
		});
		
		//English
		EnglishItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.resetResourceBundle(LanguageManager.ENGLISH);
				MainWindow mainWindow = (MainWindow)MenuBar.getTopLevelAncestor();
				mainWindow.resetAllLanguage();
			}
		});

		// English
		SwedishItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.resetResourceBundle(LanguageManager.SWEDISH);
				MainWindow mainWindow = (MainWindow) MenuBar
						.getTopLevelAncestor();
				mainWindow.resetAllLanguage();
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

	public void setMenuBarText(){
		resLocale = LanguageManager.getDefaultResourceBundle();
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
		GermanItem.setText("German");//(resLocale.getString("MenuBar_German_Option"), KeyEvent.VK_G);
		EnglishItem.setText("English");//(resLocale.getString("MenuBar_English_Option"), KeyEvent.VK_E);
		SwedishItem.setText("Swedish");
	}
	
	static public ToDoManagerMenuBar getInstance() {
		if (null == MenuBar) {
			MenuBar = new ToDoManagerMenuBar();
			initMenuBar();
		}
		return MenuBar;
	}

}
