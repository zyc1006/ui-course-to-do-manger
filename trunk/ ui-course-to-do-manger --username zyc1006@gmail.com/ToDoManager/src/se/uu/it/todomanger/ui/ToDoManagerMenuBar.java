package se.uu.it.todomanger.ui;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.controller.ThemeManager;
import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.dao.Savestate;
import se.uu.it.todomanger.model.NewTaskTableModel;
import se.uu.it.todomanger.model.Task;

import com.sun.java.swing.plaf.*;

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
	//private static String lang ;
	
	private static ToDoManagerMenuBar MenuBar = null;
	
	// MenuItem in menu bar
	private static JMenu FileMenu;
	private static JMenu TaskMenu;
	private static JMenu HelpMenu;
	private static JMenu LanguageMenu;
	private static JMenu ThemeMenu;
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
	private static JMenuItem Theme1Item;
	private static JMenuItem Theme2Item;
	
	private ToDoManagerMenuBar() {
		
	}

	/**
	 * Sets up the menu bar with the words/phrases from the local resource bundle.
	 * @author Shiyu
	 * @author Yucheng
	 * @author Bjorn
	 */
	private static void initMenuBar() {

		FileMenu = new JMenu(LanguageManager.getString("MenuBar_File_Menu"));
		TaskMenu = new JMenu(LanguageManager.getString("MenuBar_Task_Menu"));
		HelpMenu = new JMenu(LanguageManager.getString("MenuBar_Help_Menu"));
		LanguageMenu = new JMenu(LanguageManager.getString("MenuBar_Language_Menu"));
		ThemeMenu = new JMenu(LanguageManager.getString("MenuBar_Theme_Menu"));
		MenuBar.add(FileMenu);
		MenuBar.add(TaskMenu);
		MenuBar.add(ThemeMenu);
		MenuBar.add(LanguageMenu);
		MenuBar.add(HelpMenu);
		
		Theme1Item = new JMenuItem(LanguageManager.getString("MenuBar_Theme1_Option"), KeyEvent.VK_A);
		Theme2Item = new JMenuItem(LanguageManager.getString("MenuBar_Theme2_Option"), KeyEvent.VK_B);
		ThemeMenu.add(Theme1Item);
		ThemeMenu.addSeparator();
		ThemeMenu.add(Theme2Item);
		
		OpenItem = new JMenuItem(LanguageManager.getString("MenuBar_Open_File_Option"), KeyEvent.VK_O);
		SaveItem = new JMenuItem(LanguageManager.getString("MenuBar_Save_File_Option"), KeyEvent.VK_S);
		QuitItem = new JMenuItem(LanguageManager.getString("MenuBar_Quit_Option"), KeyEvent.VK_Q);
		FileMenu.add(OpenItem);
		FileMenu.addSeparator();
		FileMenu.add(SaveItem);
		FileMenu.addSeparator();
		FileMenu.add(QuitItem);

		EditItem = new JMenuItem(LanguageManager.getString("MenuBar_Edit_Task_Option"), KeyEvent.VK_E);
		DeleteItem = new JMenuItem(LanguageManager.getString("MenuBar_Delete_Task_Option"), KeyEvent.VK_T);
		AddItem = new JMenuItem(LanguageManager.getString("MenuBar_Add_Task_Option"), KeyEvent.VK_A);
		TaskMenu.add(AddItem);
		TaskMenu.addSeparator();
		TaskMenu.add(EditItem);
		TaskMenu.addSeparator();
		TaskMenu.add(DeleteItem);

		AboutUsItem = new JMenuItem(LanguageManager.getString("MenuBar_About_Option"), KeyEvent.VK_A);
		HelpItem = new JMenuItem(LanguageManager.getString("MenuBar_Help_Option"), KeyEvent.VK_H);
		HelpMenu.add(HelpItem);
		HelpMenu.addSeparator();
		HelpMenu.add(AboutUsItem);
		
		GermanItem = new JMenuItem(LanguageManager.getString("MenuBar_German_Option") , KeyEvent.VK_G);
		EnglishItem = new JMenuItem(LanguageManager.getString("MenuBar_English_Option"), KeyEvent.VK_E);
		SwedishItem = new JMenuItem(LanguageManager.getString("MenuBar_Swedish_Option"), KeyEvent.VK_W);
		ChineseItem = new JMenuItem(LanguageManager.getString("MenuBar_Chinese_Option"), KeyEvent.VK_C);
		
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
//			public void actionPerformed(ActionEvent e) {
//				int val = JOptionPane.showConfirmDialog(
//						null,
//						LanguageManager
//								.getString("MainWindow_ConfirmExit_Message"),
//						LanguageManager.getString("MainWindow_ConfirmExit_Title"),
//						0);
//				if (val == JOptionPane.OK_OPTION) {
//					
//					//save status
//					MainWindow main = (MainWindow)MenuBar.getTopLevelAncestor();
//					//JFrame main = (JFrame) e.getSource();
//					Dimension size = main.getSize();
//					Point location = main.getLocationOnScreen();
//					Savestate save = new Savestate();
//					
//					save.saveLocation(size, location , MainWindow.lang);
//					System.exit(0);
//				}
//			}
			public void actionPerformed(ActionEvent e) {
				{
					int val = JOptionPane.showConfirmDialog(
							null,
							LanguageManager
									.getString("MainWindow_ConfirmExit_Message"),
							LanguageManager.getString("MainWindow_ConfirmExit_Title"),
							0);
					if (val == JOptionPane.OK_OPTION) {
						
						//save status
						MainWindow main = (MainWindow)MenuBar.getTopLevelAncestor();
						//JFrame main = (JFrame) e.getSource();
						Dimension size = main.getSize();
						Point location = main.getLocationOnScreen();
						Savestate save = new Savestate();
						
						save.saveLocation(size, location , MainWindow.lang);
						DataSource.toXmlFile(DataSource.taskArrayList);
						System.exit(0);
					}
				}
			}
		});
		// Edit task
		EditItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToDoManagerTaskTable taskTable = ToDoManagerTaskTable
						.getInstance();
				int selectedRow = taskTable.getSelectedRow();
				
				if(selectedRow >= 0){
					int modelRow = taskTable.getRowSorter().convertRowIndexToModel(selectedRow);
					Task task = ((NewTaskTableModel)taskTable.getModel()).getTask(modelRow);
					
					AddEditDialog addEditDialog = new AddEditDialog();
					addEditDialog.ShowEditDialog(task);
					if (addEditDialog.clickedOK()) {
						// Add a task here
						TaskManager tm = TaskManager.getInstance();
						tm.editTask(modelRow, addEditDialog.getTask());
				//		tm.displayTaskByDueDateAsc();
					} else {
						System.out.println("cancel");
					}
				}
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
				//	tm.displayTaskByDueDateAsc();
				}
			}
		});
		
		// Theme1
		Theme1Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemeManager.setTheme(1);
				
				// Re-render the program
				SwingUtilities.updateComponentTreeUI(getInstance().getTopLevelAncestor());
			}
		});
		
		// Theme2
		Theme2Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemeManager.setTheme(2);
				
				// Re-render the program
				SwingUtilities.updateComponentTreeUI(getInstance().getTopLevelAncestor());
			}
		});
		
		//German
		GermanItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.setLocale(LanguageManager.GERMAN);
				MainWindow.lang = "de";
				MainWindow mainWindow = (MainWindow)MenuBar.getTopLevelAncestor();
				mainWindow.resetAllLanguage();

				
			}
		});
		
		//English
		EnglishItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.setLocale(LanguageManager.ENGLISH);
				MainWindow.lang = "en";
				MainWindow mainWindow = (MainWindow)MenuBar.getTopLevelAncestor();
				mainWindow.resetAllLanguage();
			}
		});

		// Swedish
		SwedishItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LanguageManager.setLocale(LanguageManager.SWEDISH);
				MainWindow.lang = "sv";
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
				MainWindow.lang = "zh";
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
		FileMenu.setText(LanguageManager.getString("MenuBar_File_Menu"));
		TaskMenu.setText(LanguageManager.getString("MenuBar_Task_Menu"));
		HelpMenu.setText(LanguageManager.getString("MenuBar_Help_Menu"));
		OpenItem.setText(LanguageManager.getString("MenuBar_Open_File_Option"));
		SaveItem.setText(LanguageManager.getString("MenuBar_Save_File_Option"));
		QuitItem.setText(LanguageManager.getString("MenuBar_Quit_Option"));
		EditItem.setText(LanguageManager.getString("MenuBar_Edit_Task_Option"));
		DeleteItem.setText(LanguageManager.getString("MenuBar_Delete_Task_Option"));
		AddItem.setText(LanguageManager.getString("MenuBar_Add_Task_Option"));
		AboutUsItem.setText(LanguageManager.getString("MenuBar_About_Option"));
		HelpItem.setText(LanguageManager.getString("MenuBar_Help_Option"));
		LanguageMenu.setText(LanguageManager.getString("MenuBar_Language_Menu"));
		GermanItem.setText(LanguageManager.getString("MenuBar_German_Option"));
		EnglishItem.setText(LanguageManager.getString("MenuBar_English_Option"));
		SwedishItem.setText(LanguageManager.getString("MenuBar_Swedish_Option"));
		ChineseItem.setText(LanguageManager.getString("MenuBar_Chinese_Option"));
		
		ThemeMenu.setText(LanguageManager.getString("MenuBar_Theme_Menu"));
		Theme1Item.setText(LanguageManager.getString("MenuBar_Theme1_Option"));
		Theme2Item.setText(LanguageManager.getString("MenuBar_Theme2_Option"));
	}
	
	/**
	 * returns the singleton instance of the menu bar
	 * @return menu bar
	 */
	static public ToDoManagerMenuBar getInstance() {
		if (null == MenuBar) {
			MenuBar = new ToDoManagerMenuBar();
			initMenuBar();
		}
		return MenuBar;
	}

}
