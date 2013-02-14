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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Shiyu
 * @author Edward
 *
 */
public class MainWindow extends JFrame {
	
	
	// Window  data
	public static final int MAINWINDOW_WIDTH = 800;
	public static final int MAINWINDOW_HEIGHT = 600;
	
	// Language data
	public static final String MAINWINDOW_TITLE = "To Do Manager";
	
	
	
	public MainWindow(/*ClientController controller*/)
	{
		//this.controller = controller;
		init();
	}
	public void init()
	{

		this.setTitle(MAINWINDOW_TITLE);
		this.setSize(MAINWINDOW_WIDTH,MAINWINDOW_HEIGHT);

		this.setContentPane(createPanel());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				{
					int val = JOptionPane.showConfirmDialog(null,"Do you want to exit?","yes or no",0);
					if (val == JOptionPane.OK_OPTION)
						System.exit(0);
				}
			}
		});
	}
	public JPanel createPanel()
	{
		JPanel menu_toolbar_panel = new JPanel(new BorderLayout()); 
		JPanel panel = new JPanel(new BorderLayout());
		//create menubar
		//MenuBar mb = new MenuBar();
		menu_toolbar_panel.add(BorderLayout.NORTH, ToDoManagerMenuBar.getInstance());
		menu_toolbar_panel.add(BorderLayout.SOUTH , ToDoManagerToolBar.getInstance());
		
		panel.add(BorderLayout.NORTH , menu_toolbar_panel);
		MainShowPanel msp = new MainShowPanel(); 
		panel.add(msp.initMainShow());
		return panel;
	}

	public static void main(String[] args){
		MainWindow window = new MainWindow();	
		
	}
}

