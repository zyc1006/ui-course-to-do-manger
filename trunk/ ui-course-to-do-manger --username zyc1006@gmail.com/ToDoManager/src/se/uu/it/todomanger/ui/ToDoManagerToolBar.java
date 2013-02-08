package se.uu.it.todomanger.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import se.uu.it.todomanger.controller.TaskManager;
/**
 *  A singleton of tool bar for ToDoManager
 * @author Shiyu
 * @author Yucheng
 *
 */
public class ToDoManagerToolBar extends JToolBar {
	
	
	// Window  data
	public static final int TOOLBAR_BUTTON_WIDTH = 50;
	public static final int TOOLBAR_BUTTON_HEIGHT = 50;
	public static final int TOOLBAR_BUTTON_MAX_WIDTH = 50;
	public static final int TOOLBAR_BUTTON_MAX_HEIGHT = 50;
	
	
	// Language data
	public static final String TOOLBAR_ADD_TASK = "Create task";
	public static final String TOOLBAR_EDIT_TASK = "Edit task";
	public static final String TOOLBAR_DELETE_TASK = "Delete task";
	
	// Icon file data
	public static final String TOOLBAR_ICON_ADD_TASK = "res/image/Create.png";
	public static final String TOOLBAR_ICON_EDIT_TASK = "res/image/edit.png";
	public static final String TOOLBAR_ICON_DELETE_TASK = "res/image/delete.png";
	
	
	private static ToDoManagerToolBar toolBar = null;
	
	private ToDoManagerToolBar(){
		super();
	}
	
	private static void initToolBar(){
		
		ImageIcon imageIcon = new ImageIcon(TOOLBAR_ICON_ADD_TASK);
		JButton createTaskButton = new JButton(scaleImage(imageIcon, TOOLBAR_BUTTON_WIDTH, TOOLBAR_BUTTON_HEIGHT));
		createTaskButton.setToolTipText(TOOLBAR_ADD_TASK);
		createTaskButton.setMaximumSize(new Dimension(TOOLBAR_BUTTON_MAX_WIDTH, TOOLBAR_BUTTON_MAX_HEIGHT));
		createTaskButton.setBorderPainted(false);
		
		imageIcon = new ImageIcon(TOOLBAR_ICON_EDIT_TASK);
		JButton editTaskButton = new JButton(scaleImage(imageIcon, TOOLBAR_BUTTON_WIDTH, TOOLBAR_BUTTON_HEIGHT));
		editTaskButton.setToolTipText(TOOLBAR_EDIT_TASK);
		editTaskButton.setMaximumSize(new Dimension(TOOLBAR_BUTTON_MAX_WIDTH, TOOLBAR_BUTTON_MAX_HEIGHT));
		editTaskButton.setBorderPainted(false);
		
		imageIcon = new ImageIcon(TOOLBAR_ICON_DELETE_TASK);
		JButton delTaskButton = new JButton(scaleImage(imageIcon, TOOLBAR_BUTTON_WIDTH, TOOLBAR_BUTTON_HEIGHT));
		delTaskButton.setToolTipText(TOOLBAR_DELETE_TASK);
		delTaskButton.setMaximumSize(new Dimension(TOOLBAR_BUTTON_MAX_WIDTH, TOOLBAR_BUTTON_MAX_HEIGHT));
		delTaskButton.setBorderPainted(false);
		
		toolBar.add(createTaskButton);
		toolBar.add(editTaskButton);
		toolBar.add(delTaskButton);
		
		//Actions for the buttons 
		createTaskButton.addActionListener(new ActionListener() {
			
			//Open a dialog for adding tasks
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditDialog addEditDialog = new AddEditDialog();
				addEditDialog.ShowAddDialog();
				if(addEditDialog.clickedOK()){
					//Add a task here
					TaskManager tm = TaskManager.getInstance();
					tm.addTask(addEditDialog.getTask());
					tm.displayTaskByDueDateAsc();
				}
				else {
					System.out.println("cancel");
				}
				
				
			}
		});
		
	}
	
	public static ToDoManagerToolBar getInstance(){
		if(null == toolBar){
			toolBar = new ToDoManagerToolBar();
			initToolBar();
		}
		return toolBar;
	}
	
	private static ImageIcon scaleImage(ImageIcon icon, int width, int height){
		Image image = icon.getImage();
		Image smallImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(smallImage);
			
	}
	
	
}
