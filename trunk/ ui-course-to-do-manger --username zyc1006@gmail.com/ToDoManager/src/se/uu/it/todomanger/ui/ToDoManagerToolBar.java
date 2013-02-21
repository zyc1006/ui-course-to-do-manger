package se.uu.it.todomanger.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.controller.TaskManager;

/**
 * A singleton of tool bar for ToDoManager
 * 
 * @author Shiyu
 * @author Yucheng
 * 
 */
public class ToDoManagerToolBar extends JToolBar {

	// Window data
	public static final int TOOLBAR_BUTTON_WIDTH = 35;
	public static final int TOOLBAR_BUTTON_HEIGHT = 35;
	public static final int TOOLBAR_BUTTON_MAX_WIDTH = 35;
	public static final int TOOLBAR_BUTTON_MAX_HEIGHT = 35;

	// Icon file data
	public static final String TOOLBAR_ICON_ADD_TASK = "res/image/Add.gif";
	public static final String TOOLBAR_ICON_EDIT_TASK = "res/image/Edit.gif";
	public static final String TOOLBAR_ICON_DELETE_TASK = "res/image/Delete.gif";

	private static ToDoManagerToolBar toolBar = null;
	
	
	//tool bar buttons
	private static JButton createTaskButton;
	private static JButton editTaskButton;
	private static JButton delTaskButton;
	
	// A ResourceBundle for handling internationalization
	private static LanguageManager resLocale;

	private ToDoManagerToolBar() {
		super();
	}

	/**
	 * initToolBar
	 * 
	 * @author Shiyu
	 * @author Yucheng
	 * @author Bjorn
	 * 
	 * @description Sets up the tool bar with icons and internationalized
	 *              words/phrases.
	 */
	private static void initToolBar() {

		// Load the international words/phrases from the resourcebundle.
		try {
//			resLocale = ResourceBundle.getBundle("locale.ToDoManager",
//					Locale.getDefault());
			resLocale = new LanguageManager();
		} catch (MissingResourceException mre) {
			System.err.println("res/locale/ToDoManager.properties not found");
			System.exit(1);
		}

		ImageIcon imageIcon = new ImageIcon(TOOLBAR_ICON_ADD_TASK);
		createTaskButton = new JButton(scaleImage(imageIcon,
				TOOLBAR_BUTTON_WIDTH, TOOLBAR_BUTTON_HEIGHT));
		createTaskButton.setToolTipText(resLocale.getString("MenuBar_Add_Task_Option"));
		createTaskButton.setMaximumSize(new Dimension(TOOLBAR_BUTTON_MAX_WIDTH,
				TOOLBAR_BUTTON_MAX_HEIGHT));
		createTaskButton.setBorderPainted(false);

		imageIcon = new ImageIcon(TOOLBAR_ICON_EDIT_TASK);
		editTaskButton = new JButton(scaleImage(imageIcon,
				TOOLBAR_BUTTON_WIDTH, TOOLBAR_BUTTON_HEIGHT));
		editTaskButton.setToolTipText(resLocale.getString("MenuBar_Edit_Task_Option"));
		editTaskButton.setMaximumSize(new Dimension(TOOLBAR_BUTTON_MAX_WIDTH,
				TOOLBAR_BUTTON_MAX_HEIGHT));
		editTaskButton.setBorderPainted(false);

		imageIcon = new ImageIcon(TOOLBAR_ICON_DELETE_TASK);
		delTaskButton = new JButton(scaleImage(imageIcon,
				TOOLBAR_BUTTON_WIDTH, TOOLBAR_BUTTON_HEIGHT));
		delTaskButton.setToolTipText(resLocale.getString("MenuBar_Delete_Task_Option"));
		delTaskButton.setMaximumSize(new Dimension(TOOLBAR_BUTTON_MAX_WIDTH,
				TOOLBAR_BUTTON_MAX_HEIGHT));
		delTaskButton.setBorderPainted(false);

		toolBar.add(createTaskButton);
		toolBar.addSeparator();
		toolBar.add(editTaskButton);
		toolBar.addSeparator();
		toolBar.add(delTaskButton);

		// Actions for the buttons
		createTaskButton.addActionListener(new ActionListener() {

			// Open a dialog for adding tasks
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditDialog addEditDialog = new AddEditDialog();
				addEditDialog.ShowAddDialog();
				if (addEditDialog.clickedOK()) {
					// Add a task here
					TaskManager tm = TaskManager.getInstance();
					tm.addTask(addEditDialog.getTask());
					tm.displayTaskByDueDateAsc();
				} else {
					System.out.println("cancel");
				}

			}
		});

		editTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TaskManager.getInstance().displayTaskByDueDateDesc();
			}
		});

	}

	public static ToDoManagerToolBar getInstance() {
		if (null == toolBar) {
			toolBar = new ToDoManagerToolBar();
			initToolBar();
		}
		return toolBar;
	}

	public void setToolBarText(){
		//resLocale = LanguageManager.getDefaultResourceBundle();
		delTaskButton.setToolTipText(resLocale.getString("MenuBar_Delete_Task_Option"));
		createTaskButton.setToolTipText(resLocale.getString("MenuBar_Add_Task_Option"));
		editTaskButton.setToolTipText(resLocale.getString("MenuBar_Edit_Task_Option"));
	}
	
	
	/**
	 * scale the icon of toolbar so as to fit the size
	 * @param icon
	 * @param width
	 * @param height
	 * @return
	 */
	private static ImageIcon scaleImage(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image smallImage = image.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
		return new ImageIcon(smallImage);

	}

}
