///**
// * A test main window, will be modified later
// */
//package se.uu.it.todomanger.ui;
//import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.Date;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.plaf.DimensionUIResource;
//
//import se.uu.it.todomanger.controller.TaskManager;
//import se.uu.it.todomanger.model.Task;
//import se.uu.it.todomanger.model.TaskTableModel;
//
///**
// * @author Shiyu
// * @author Edward
// *
// */
//public class MainWindow extends JFrame {
//
//	//ContentPane
//	Container container = null;
//	
//	//Default constructor
//	public MainWindow(){
//		
//		//Title
//		this.setTitle("To-Do Manager");
//		container = this.getContentPane();
//		container.setLayout(new BorderLayout());
//		
//		//Menu bar and Tool bar
//		JPanel menuToobarPanel = new JPanel(new BorderLayout());
//		menuToobarPanel.add(ToDoManagerMenuBar.getInstance(), BorderLayout.NORTH);
//		menuToobarPanel.add(ToDoManagerToolBar.getInstance(), BorderLayout.SOUTH);
//		
//		//Task table
//		TaskManager tm = TaskManager.getInstance();
//		tm.addTask(new Task(1, "first", new Date(2012-1900, 12-1, 1), 1, "hello", 5, true));
//		tm.addTask(new Task(2, "first", new Date(2012-1900, 4-1, 12), 2, "hello", 4, true));
//		tm.addTask(new Task(3, "first", new Date(2011-1900, 5-1, 18), 3, "hello", 3, true));
//		tm.addTask(new Task(4, "first", new Date(2012-1900, 6-1, 13), 4, "hello", 2, true));
//		JTable taskTable = new JTable(tm.taskTableModel);
//		JScrollPane taskPane = new JScrollPane(taskTable, 
//					ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
//					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		tm.displayTaskByDueDatDesc();
//		
//
//		
//		//TBD
//		JPanel CategoryPanel = new JPanel(new BorderLayout());
//		JButton button = new JButton("tb");
//		button.setMaximumSize(new Dimension(220, 30));
//		button.setMinimumSize(new Dimension(220, 30));
//		button.setPreferredSize(new Dimension(220, 30));
//		
//		
//		button.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("aaa");
//				
//			}
//		});
//		
//		CategoryPanel.add(button);
//		container.add(taskPane, BorderLayout.CENTER);
//		container.add(CategoryPanel, BorderLayout.WEST);
//		container.add(menuToobarPanel, BorderLayout.NORTH);
//		
//
//		
//		this.setSize(800, 600);
//		this.setVisible(true);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		
//		
//		
//	}
//	
//	public static void main(String[] args){
//		MainWindow window = new MainWindow();
//                // Bjšrn was here again
//		//Sara too
//	
//		
//	}
//	
//
//}

/**
 * A test main window, will be modified later
 */
package se.uu.it.todomanger.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.controller.ThemeManager;
import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.dao.Savestate;
import se.uu.it.todomanger.model.TaskTableModel;
import se.uu.it.todomanger.controller.ReminderTimerManager;

/**
 * @author Shiyu
 * @author Edward
 * 
 */
public class MainWindow extends JFrame {

	// Window data
	public static final int MAINWINDOW_WIDTH = 800;
	public static final int MAINWINDOW_HEIGHT = 600;
	public static String lang ;

	public MainWindow(/* ClientController controller */) {
		// this.controller = controller;
		init();
	}

	/**
	 * Set up the main window.
	 * 
	 * @author Shiyu
	 * @author Edward
	 * @author Bjorn
	 * 
	 */
	public void init() {

		
		Savestate save = new Savestate();
		// load saved size, theme and location
		Properties prop = save.loadLocation(System.getProperty("user.home")
				+ "/TODOgroup12.properties");
		if (null != prop) {
			Point point = new Point();
			point.x = Integer.parseInt(prop.getProperty("x"));
			point.y = Integer.parseInt(prop.getProperty("y"));
			this.setLocation(point);

			Dimension size = new Dimension();
			size.width = Integer.parseInt(prop.getProperty("width"));
			size.height = Integer.parseInt(prop.getProperty("height"));
			this.setSize(size);
			
			// Load theme
			String theme = prop.getProperty("theme");
			if (theme == null) {
				ThemeManager.setTheme(1);
			}
			else
			{
				if (theme.equals("1")) {
					ThemeManager.setTheme(1);
				} else if (theme.equals("2")) {
					ThemeManager.setTheme(2);
				}
				
			}
			
			String la = prop.getProperty("lang");
			if (la == null) {
				LanguageManager.setLocale(LanguageManager.ENGLISH);
				lang = "en";
			} else {
				if (la.equals("de")) {
					LanguageManager.setLocale(LanguageManager.GERMAN);
					lang = "de";
				} else if (la.equals("en")) {
					LanguageManager.setLocale(LanguageManager.ENGLISH);
					lang = "en";
				} else if (la.equals("sv")) {
					LanguageManager.setLocale(LanguageManager.SWEDISH);
					lang = "sv";
				} else if (la.equals("zh")) {
					LanguageManager.setLocale(LanguageManager.CHINESE);
					lang = "zh";
				}
			}
			this.setTitle(LanguageManager.getString("MainWindow_Title"));
		}
		// load default size and location
		else {
			this.setSize(MAINWINDOW_WIDTH, MAINWINDOW_HEIGHT);
			this.setLocationRelativeTo(null);
			// Load the international words/phrases from the LanguageManager.
			try {
				// Set the default language -- this is supposed to be remembered by
				// the program between exits TBD!
				//sv,SE; en,SU; zh,CN;de,DE
				LanguageManager.setLocale(LanguageManager.ENGLISH);
				lang = "en";
				// Set the default theme
				ThemeManager.setTheme(1);
			} catch (MissingResourceException mre) {
				System.err.println("res/locale/ToDoManager.properties not found");
				System.exit(1);
			}
		}
		
		
		//load tasks----------------------
		DataSource.taskArrayList = DataSource.toTaskList();
//		TaskManager.getInstance().setTaskArrayList(DataSource.toTaskList());
//		TaskManager.getInstance().displayTaskByDueDateAsc();
		
		//Run ReminderTimerManager thread
		ReminderTimerManager.getInstance().TimeMonitor();
		
		//-----------------------------------
		this.setContentPane(createPanel());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				{
					int val = JOptionPane.showConfirmDialog(
							null,
							LanguageManager
									.getString("MainWindow_ConfirmExit_Message"),
							LanguageManager
									.getString("MainWindow_ConfirmExit_Title"),
							0);
					if (val == JOptionPane.OK_OPTION) {

						// save status
						JFrame main = (JFrame) e.getSource();
						Dimension size = main.getSize();
						Point location = main.getLocationOnScreen();
						Savestate save = new Savestate();

						save.saveLocation(size, location , lang);
						//save tasks
						DataSource.toXmlFile(DataSource.taskArrayList);
						System.exit(0);
					}
				}
			}
		});
	}

	
	private JPanel createPanel() {
		JPanel menu_toolbar_panel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new BorderLayout());
		// create menubar
		// MenuBar mb = new MenuBar();
		menu_toolbar_panel.add(BorderLayout.NORTH,
				ToDoManagerMenuBar.getInstance());
		menu_toolbar_panel.add(BorderLayout.SOUTH,
				ToDoManagerToolBar.getInstance());

		panel.add(BorderLayout.NORTH, menu_toolbar_panel);
		MainShowPanel msp = new MainShowPanel();
		panel.add(msp.initMainShow());
		return panel;
	}

	/**
	 * reset application language
	 * 
	 */
	public void resetAllLanguage() {
		this.setMainWindowText();
		ToDoManagerMenuBar.getInstance().setMenuBarText();
		ToDoManagerToolBar.getInstance().setToolBarText();
		ToDoManagerTaskTable.getInstance().setTaskTableText();
		ShowCatalog.UpdateLanguageText();

	}

	private void setMainWindowText() {
		
		this.setTitle(LanguageManager.getString("MainWindow_Title"));
	}

	/**
	 * The entry for the application
	 * @param args
	 */
	public static void main(String[] args) {
		MainWindow window = new MainWindow();

	}
}
