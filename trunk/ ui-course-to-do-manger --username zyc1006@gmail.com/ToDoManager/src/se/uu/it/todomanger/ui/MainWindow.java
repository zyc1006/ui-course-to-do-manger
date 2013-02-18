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
import se.uu.it.todomanger.dao.Savestate;
import se.uu.it.todomanger.model.TaskTableModel;

/**
 * @author Shiyu
 * @author Edward
 * 
 */
public class MainWindow extends JFrame {

	// Window data
	public static final int MAINWINDOW_WIDTH = 800;
	public static final int MAINWINDOW_HEIGHT = 600;

	// Language data
	private ResourceBundle resLocale;

	public MainWindow(/* ClientController controller */) {
		// this.controller = controller;
		init();
	}

	/**
	 * init
	 * @author Shiyu
	 * @author Edward
	 * @author Bjorn
	 * 
	 * @description Set up the main window.
	 */
	public void init() {

		// Load the international words/phrases from the resourcebundle.
		try {
//			resLocale = ResourceBundle.getBundle("locale.ToDoManager",
//					Locale.getDefault());
//			resLocale = LanguageManager.getDefaultResourceBundle();
			resLocale = LanguageManager.resetResourceBundle(LanguageManager.ENGLISH);
		} catch (MissingResourceException mre) {
			System.err.println("res/locale/ToDoManager.properties not found");
			System.exit(1);
		}
		
		this.setTitle(resLocale.getString("MainWindow_Title"));		
		Savestate save = new Savestate();
		//load saved size and location
		Properties prop = save.loadLocation(System.getProperty("user.home")+ 
				"/TODOgroup12.properties");
		if( null != prop){
			Point point = new Point();
			point.x = Integer.parseInt(prop.getProperty("x"));
			point.y = Integer.parseInt(prop.getProperty("y"));
			this.setLocation(point);
			
			Dimension size = new Dimension();
			size.width = Integer.parseInt(prop.getProperty("width"));
			size.height = Integer.parseInt(prop.getProperty("height"));
			this.setSize(size);
			
		}
		//load default size and location
		else{
			this.setSize(MAINWINDOW_WIDTH, MAINWINDOW_HEIGHT);
			this.setLocationRelativeTo(null);
		}
		this.setContentPane(createPanel());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				{
					int val = JOptionPane.showConfirmDialog(
							null,
							resLocale
									.getString("MainWindow_ConfirmExit_Message"),
							resLocale.getString("MainWindow_ConfirmExit_Title"),
							0);
					if (val == JOptionPane.OK_OPTION) {
						
						//save status
						JFrame main = (JFrame) e.getSource();
						Dimension size = main.getSize();
						Point location = main.getLocationOnScreen();
						Savestate save = new Savestate();
						
						save.saveLocation(size, location);
						System.exit(0);
					}
				}
			}
		});
	}

	public JPanel createPanel() {
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
	 * @param language
	 */
	public void resetAllLanguage(){
		this.setMainWindowText();
		ToDoManagerMenuBar.getInstance().setMenuBarText();
		ToDoManagerToolBar.getInstance().setToolBarText();
		ToDoManagerTaskTable.getInstance().setTaskTableText();
		
	}
	
	
	
	private void setMainWindowText(){
		this.resLocale = LanguageManager.getDefaultResourceBundle();
		this.setTitle(resLocale.getString("MainWindow_Title"));	
	}
	

	public static void main(String[] args) {
		MainWindow window = new MainWindow();

	}
}
