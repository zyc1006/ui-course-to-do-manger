package se.uu.it.todomanger.ui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
/**
 *  A singleton of tool bar for ToDoManager
 * @author Yucheng
 *
 */
public class ToDoManagerToolBar extends JToolBar {
	
	private static ToDoManagerToolBar toolBar = null;
	
	private ToDoManagerToolBar(){
		super();
	}
	
	private static void initToolBar(){
		
		ImageIcon imageIcon = new ImageIcon("res/image/Create.png");
		JButton createTaskButton = new JButton(scaleImage(imageIcon, 50, 50));
		createTaskButton.setToolTipText("Create task");
		createTaskButton.setMaximumSize(new Dimension(50, 50));
		createTaskButton.setBorderPainted(false);
		
		imageIcon = new ImageIcon("res/image/edit.png");
		JButton editTaskButton = new JButton(scaleImage(imageIcon, 50, 50));
		editTaskButton.setToolTipText("Edit task");
		editTaskButton.setMaximumSize(new Dimension(50, 50));
		editTaskButton.setBorderPainted(false);
		
		imageIcon = new ImageIcon("res/image/delete.png");
		JButton delTaskButton = new JButton(scaleImage(imageIcon, 50, 50));
		delTaskButton.setToolTipText("Delte task");
		delTaskButton.setMaximumSize(new Dimension(50, 50));
		delTaskButton.setBorderPainted(false);
		
		toolBar.add(createTaskButton);
		toolBar.add(editTaskButton);
		toolBar.add(delTaskButton);
		
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
