/* TBD by Bjoern and Sara */

package se.uu.it.todomanger.ui;

// Imports
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

// Swing imports
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.dom4j.Element;

import se.uu.it.todomanger.controller.CategoryManager;
import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.model.Category;
// Todo manager imports
import se.uu.it.todomanger.model.Task;

/**
 * A class that can display a dialog for adding and editing tasks.
 * 
 * @author bjorn
 * @author sara
 */
public class AddEditDialog extends JDialog {

	/**
	 * serialVersionUID
	 * 
	 * @author Eclipse
	 */
	private static final long serialVersionUID = 1L;

	// === CONSTANTS for dialog language and look ===

		// Priority slider data
		private static final int ADDEDITDIALOG_PRIORITY_MIN = 0;
		private static final int ADDEDITDIALOG_PRIORITY_MAX = 2;
		private static final int ADDEDITDIALOG_PRIORITY_TICK_SPACING = 1;
		private static final Boolean ADDEDITDIALOG_PRIORITY_SHOW_TICKS = true;
		private static final Boolean ADDEDITDIALOG_PRIORITY_SHOW_LABELS = true;
	
		// Padding of GridBagView
		private static final int ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP = 10;
		private static final int ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT = 10;
		private static final int ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_LEFT = 0;
		private static final int ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_BOTTOM = 0;
	
	
		// Placement data for components in GridBagLayout
		private static final int ADDEDITDIALOG_LAYOUT_TITLE_ROW = 0;
		private static final int ADDEDITDIALOG_LAYOUT_DUEDATE_ROW = 1;
		private static final int ADDEDITDIALOG_LAYOUT_CATEGORY_ROW = 2;
		private static final int ADDEDITDIALOG_LAYOUT_PRIORITY_ROW = 3;
		private static final int ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW = 4;
	
		private static final Boolean ADDEDITDIALOG_LAYOUT_ORDER_CANCELOK = true;
	
		// Window data
		private static final Boolean ADDEDITDIALOG_WINDOW_RESIZABLE = false;

	// === END OF CONSTANTS ===

	// For keeping track of which mode the AddEditDialog is in.
	private static enum DialogMode {
		ADD_DIALOG, EDIT_DIALOG
	}

	// Private definitions
	private Task task;
	private Boolean clickedOK;
	private DialogMode dialogMode;

	private JTextField txtTitle;
	private JTextField txtDueDate;
	private JTextField txtPriority;
	private JComboBox cmbDueDateHour;
	private JComboBox cmbDueDateMinute;
	private JTextArea txtDescription;
	private JSlider sliPriority;
	private JComboBox cmbCategory;

	private Dimension wideTextDimension = new Dimension(350, 22);
	private Dimension dateFieldDimension = new Dimension(32, 22);
	private Dimension sliderDimension = new Dimension(350, 48);
	private Dimension longTextDimension = new Dimension(350, 64);
	private Dimension buttonDimension = new Dimension(50, 36);

	// The content pane of the dialog
	private Container gridBagContainer;

	/**
	 * The constructor for the AddEditDialog, which defines the GUI.
	 * 
	 * @author bjoern
	 * @author sara
	 */
	public AddEditDialog() {

		// Step 1: Create and set properties of components

		// Information labels
		JLabel lblTitle = new JLabel(
				LanguageManager.getString("AddEditDialog_Title_Label"));
		JLabel lblDueDate = new JLabel(
				LanguageManager.getString("AddEditDialog_DueDate_Label"));
		JLabel lblCategory = new JLabel(
				LanguageManager.getString("AddEditDialog_Category_Label"));
		JLabel lblPriority = new JLabel(
				LanguageManager.getString("AddEditDialog_Priority_Label"));
		JLabel lblDescription = new JLabel(
				LanguageManager.getString("AddEditDialog_Description_Label"));

		// Text field for title
		txtTitle = new JTextField();
		txtTitle.setMinimumSize(wideTextDimension);

		// Combo box for categories
		cmbCategory = new JComboBox();
		cmbCategory.setMinimumSize(wideTextDimension);

		// Text fields for due date
		txtDueDate = new JTextField();
		txtDueDate.setMinimumSize(dateFieldDimension);
		CalendarPanel cp = CalendarPanel.getInstance();
		cp.register(txtDueDate);
		cmbDueDateHour = new JComboBox();
		cmbDueDateHour.setMinimumSize(dateFieldDimension);
		cmbDueDateMinute = new JComboBox();
		cmbDueDateMinute.setMinimumSize(dateFieldDimension);

		// JSlider for Priority
		sliPriority = new JSlider();
		sliPriority.setMinimumSize(sliderDimension);
		sliPriority.setMinimum(ADDEDITDIALOG_PRIORITY_MIN);
		sliPriority.setMaximum(ADDEDITDIALOG_PRIORITY_MAX);
		sliPriority.setMajorTickSpacing(ADDEDITDIALOG_PRIORITY_TICK_SPACING);

		Dictionary<Integer, JLabel> priorityLabels = new Hashtable<Integer, JLabel>();
		priorityLabels.put(ADDEDITDIALOG_PRIORITY_MIN, new JLabel(
				LanguageManager.getString("AddEditDialog_Priority_Low_Label")));
		priorityLabels
				.put(ADDEDITDIALOG_PRIORITY_MAX,
						new JLabel(LanguageManager
								.getString("AddEditDialog_Priority_High_Label")));

		sliPriority.setLabelTable(priorityLabels);
		sliPriority.setPaintLabels(ADDEDITDIALOG_PRIORITY_SHOW_LABELS);
		sliPriority.setPaintTicks(ADDEDITDIALOG_PRIORITY_SHOW_TICKS);
		sliPriority.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				UpdatePriorityTextBox();
			}

		});

		// JTextField for Priority
		txtPriority = new JTextField();
		txtPriority.setEditable(false);

		// JTextFields for Description
		txtDescription = new JTextArea();
		txtDescription.setPreferredSize(longTextDimension);

		// JButtons for OK and Cancel
		JButton btnOK = new JButton(
				LanguageManager.getString("AddEditDialog_OK_Label"));
		btnOK.setSize(buttonDimension);
		JButton btnCancel = new JButton(
				LanguageManager.getString("AddEditDialog_Cancel_Label"));
		btnCancel.setSize(buttonDimension);

		// Action handlers for buttons
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				OKButtonHandler();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				CancelButtonHandler();
			}
		});

		// Step 2: Place the components in the content pane

		// Use the GridBagLayout for the dialog.
		gridBagContainer = this.getContentPane();
		gridBagContainer.setLayout(new GridBagLayout());

		// A GridBagConstraints for placing the controls in the dialog.
		GridBagConstraints labelConstraint = new GridBagConstraints();
		GridBagConstraints fieldsConstraint = new GridBagConstraints();
		GridBagConstraints dateConstraint = new GridBagConstraints();
		GridBagConstraints buttonConstraint = new GridBagConstraints();
		GridBagConstraints priorityConstraint; // Will be copied from
												// fieldsConstraint with minor
												// changes.

		// Common information for the labels
		labelConstraint.insets = new Insets(
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP,
				10,
				0,
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT);
		labelConstraint.gridx = 0;
		labelConstraint.gridwidth = 1;
		labelConstraint.anchor = GridBagConstraints.EAST;
		labelConstraint.fill = GridBagConstraints.NONE;
		labelConstraint.weightx = 0.0;

		// Common information for the fields/sliders/combobox
		fieldsConstraint.insets = new Insets(
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP,
				ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_LEFT,
				ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_BOTTOM,
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT);
		fieldsConstraint.gridx = 1;
		fieldsConstraint.gridwidth = 5;
		fieldsConstraint.anchor = GridBagConstraints.WEST;
		fieldsConstraint.weightx = 1.0;

		// Priority information
		priorityConstraint = (GridBagConstraints) fieldsConstraint.clone();

		// Common information for the date fields
		dateConstraint.insets = new Insets(
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP,
				ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_LEFT,
				ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_BOTTOM,
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT);
		dateConstraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		dateConstraint.anchor = GridBagConstraints.WEST;
		dateConstraint.fill = GridBagConstraints.BOTH;

		// Common information for the buttons
		buttonConstraint.insets = new Insets(
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP,
				10,
				10,
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT);
		buttonConstraint.weighty = 0.0;
		buttonConstraint.gridx = 0;
		buttonConstraint.gridy = 5;
		buttonConstraint.gridwidth = 6;
		buttonConstraint.anchor = GridBagConstraints.EAST;
		buttonConstraint.fill = GridBagConstraints.NONE;

		// Add Title components
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_TITLE_ROW;
		gridBagContainer.add(lblTitle, labelConstraint);
		fieldsConstraint.weighty = 0.0;
		fieldsConstraint.gridy = ADDEDITDIALOG_LAYOUT_TITLE_ROW;
		fieldsConstraint.fill = GridBagConstraints.HORIZONTAL;
		gridBagContainer.add(txtTitle, fieldsConstraint);

		// Add Category components
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_CATEGORY_ROW;
		gridBagContainer.add(lblCategory, labelConstraint);
		fieldsConstraint.weighty = 0.0;
		fieldsConstraint.gridy = ADDEDITDIALOG_LAYOUT_CATEGORY_ROW;
		fieldsConstraint.fill = GridBagConstraints.HORIZONTAL;
		gridBagContainer.add(cmbCategory, fieldsConstraint);

		// Add Date components
		labelConstraint.weighty = 0.0;
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		gridBagContainer.add(lblDueDate, labelConstraint);
		dateConstraint.gridx = 1;
		dateConstraint.gridwidth = 3;
		dateConstraint.weightx = 0.6;
		gridBagContainer.add(txtDueDate, dateConstraint);
		dateConstraint.gridx = 4;
		dateConstraint.gridwidth = 1;
		dateConstraint.weightx = 0.2;
		gridBagContainer.add(cmbDueDateHour, dateConstraint);
		dateConstraint.gridx = 5;
		dateConstraint.gridwidth = 1;
		dateConstraint.weightx = 0.2;
		gridBagContainer.add(cmbDueDateMinute, dateConstraint);

		// Add priority components
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_PRIORITY_ROW;
		gridBagContainer.add(lblPriority, labelConstraint);
		priorityConstraint.weighty = 0.0;
		priorityConstraint.gridx = 1;
		priorityConstraint.gridwidth = 4;
		priorityConstraint.gridy = ADDEDITDIALOG_LAYOUT_PRIORITY_ROW;
		priorityConstraint.fill = GridBagConstraints.HORIZONTAL;
		gridBagContainer.add(sliPriority, priorityConstraint);
		priorityConstraint.gridx = 5;
		priorityConstraint.gridwidth = 1;
		gridBagContainer.add(txtPriority, priorityConstraint);

		// Add description components
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW;
		gridBagContainer.add(lblDescription, labelConstraint);
		fieldsConstraint.weighty = 1.0;
		fieldsConstraint.gridy = ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW;
		fieldsConstraint.fill = GridBagConstraints.BOTH;
		gridBagContainer.add(txtDescription, fieldsConstraint);

		// Add OK/Cancel components
		JPanel pnlButtonPanel = new JPanel();
		if (ADDEDITDIALOG_LAYOUT_ORDER_CANCELOK) {
			pnlButtonPanel.add(btnCancel);
			pnlButtonPanel.add(btnOK);
		} else {
			pnlButtonPanel.add(btnOK);
			pnlButtonPanel.add(btnCancel);
		}
		gridBagContainer.add(pnlButtonPanel, buttonConstraint);

		// Step 3: Set the overall settings for the dialog.

		// Populate the time combo boxes
		PopulateHourComboBox();
		PopulateMinuteComboBox();

		// Dialog settings
		this.setModal(true);
		this.pack();
		this.setResizable(ADDEDITDIALOG_WINDOW_RESIZABLE);
		this.setLocationRelativeTo(null);
		this.getRootPane().setDefaultButton(btnOK);
	}

	/**
	 * Creates/Edits the AddEditDialogs Task with the data the user has entered.
	 * 
	 * @author bjorn
	 */
	private void OKButtonHandler() {

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				CalendarPanel.dateFormat);
		Date taskDueDate = new Date();
		// Create variables with the data from the UI.
		String taskTitle = txtTitle.getText();
		String taskDueDateString = txtDueDate.getText();
		// Add leading 0 to prevent parseInt empty string exception.
		int taskDueDateHour = Integer.parseInt("0"
				+ cmbDueDateHour.getSelectedItem());
		// Add leading 0 to prevent parseInt empty string exception.
		int taskDueDateMinute = Integer.parseInt("0"
				+ cmbDueDateMinute.getSelectedItem());
		int taskCategory = cmbCategory.getSelectedIndex();
		String taskDescription = txtDescription.getText();
		int taskPriority = sliPriority.getValue();

		// Make sure the user has entered correct data
		Boolean incorrectDataFormat = false;
		String errorMessage = "";

		// There has to be a title
		if (txtTitle.getText().length() == 0) {
			errorMessage += LanguageManager
					.getString("AddEditDialog_Error_NoTitle") + "\n";
			incorrectDataFormat = true;
		}
		// Date has to be set
		if (txtDueDate.getText().toString().isEmpty()) {
			errorMessage += LanguageManager
					.getString("AddEditDialog_Error_NoDate") + "\n";
			incorrectDataFormat = true;
		}
		// Create the date object from the user information.
		try {
			taskDueDate = dateFormat.parse(taskDueDateString);
		} catch (ParseException e) {
			errorMessage += LanguageManager
					.getString("AddEditDialog_Error_DateInvalid") + "\n";
			incorrectDataFormat = true;
		}
		// If an error occurred, display the error message.
		if (incorrectDataFormat) {
			JOptionPane.showMessageDialog(this, errorMessage, LanguageManager
					.getString("AddEditDialog_Error_Dialog_Title"),
					JOptionPane.ERROR_MESSAGE);
		} else {

			// If no errors occurred, we start the Task creation/changing.

			// Make sure the calling class knows we have clicked OK.
			clickedOK = true;

			taskDueDate.setHours(taskDueDateHour);
			taskDueDate.setMinutes(taskDueDateMinute);

			// If the task is a new task, create it. Otherwise edit the
			// provided task.
			if (dialogMode == DialogMode.ADD_DIALOG) {
				// temp task id
				task = new Task(TaskManager.getInstance().getNextTaskId(), taskTitle, taskDueDate,
						taskCategory, taskDescription, taskPriority, false);
			} else {
				task.setTitle(taskTitle);
				task.setDueDate(taskDueDate);
				task.setCategory(taskCategory);
				task.setDescription(taskDescription);
				task.setPriority(taskPriority);
			}

			// Hide the dialog.
			HideAddEditDialog();

		}
	}

	/**
	 * Closes the dialog when the user presses cancel.
	 * 
	 * @author bjorn
	 */
	private void CancelButtonHandler() {
		HideAddEditDialog();
	}

	/**
	 * Sets the text of the priority text box to the value of the slider.
	 * 
	 * @author bjorn
	 */
	private void UpdatePriorityTextBox() {
		txtPriority.setText(Integer.toString(sliPriority.getValue()));
	}

	/**
	 * Closes the dialog.
	 * 
	 * @author bjorn
	 */
	public void HideAddEditDialog() {
		this.setVisible(false);
	}

	/**
	 * Adds all the categories to the combobox.
	 * 
	 * @author bjorn
	 */
	private void PopulateCategoryComboBox() {
		cmbCategory.removeAllItems();
		// cmbCategory.addItem("HARDCODED_TEST_1");
		// cmbCategory.addItem("HARDCODED_TEST_2");
		// cmbCategory.addItem("HARDCODED_TEST_3");
		//for (String str : Category.category) {
		//	cmbCategory.addItem(str);
		//}

		CategoryManager cm = CategoryManager.getInstance();
		
		for (Entry<Integer, Category> entry : cm.getCategories().entrySet()) {
		    Integer key = entry.getKey();
		    Category nextCategory = entry.getValue();
			cmbCategory.addItem(nextCategory.getCategoryTitle());
		}
		cmbCategory.updateUI();
	}

	/**
	 * Adds the hours of the clock to the hour combo box.
	 * 
	 * @author bjorn
	 */
	private void PopulateHourComboBox() {
		cmbDueDateHour.removeAll();
		for (int hour = 0; hour < 24; hour++) {
			cmbDueDateHour.addItem(String.format("%02d", hour));
		}
		cmbDueDateHour.updateUI();
	}

	/**
	 * Adds the quarters of the clock to the minute combo box.
	 * 
	 * @author bjorn
	 */
	private void PopulateMinuteComboBox() {
		cmbDueDateMinute.removeAll();
		for (int minute = 0; minute < 60; minute++) {
			cmbDueDateMinute.addItem(String.format("%02d", minute));
		}
		cmbDueDateMinute.updateUI();
	}

	/**
	 * Displays the AddEditDialog with empty fields for creating a new task.
	 * 
	 * @author bjorn
	 */
	public void ShowAddDialog() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				CalendarPanel.dateFormat);
		dialogMode = DialogMode.ADD_DIALOG;
		clickedOK = false;
		txtDueDate.setText(dateFormat.format(currentDate));
		// Select the quarter closest to the current time.
		cmbDueDateMinute.setSelectedIndex(currentDate.getMinutes());
		cmbDueDateHour.setSelectedIndex(currentDate.getHours());
		UpdatePriorityTextBox();
		PopulateCategoryComboBox();
		this.setTitle(LanguageManager
				.getString("AddEditDialog_Add_Dialog_Title"));
		this.setVisible(true);
	}

	/**
	 * Displays the AddEditDialog with the fields filled with the data from
	 * editTask.
	 * 
	 * @author bjorn
	 * @param editTask
	 *            - The task that contains the data to be edited.
	 */
	public void ShowEditDialog(Task editTask) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				CalendarPanel.dateFormat);
		dialogMode = DialogMode.EDIT_DIALOG;
		task = editTask;
		clickedOK = false;
		txtTitle.setText(editTask.getTitle());
		txtDueDate.setText(dateFormat.format(editTask.getDueDate()));
		// Select the quarter closest to the current time.
		cmbDueDateMinute.setSelectedIndex(editTask.getDueDate().getMinutes());
		cmbDueDateHour.setSelectedIndex(editTask.getDueDate().getHours());
		sliPriority.setValue(editTask.getPriority());
		txtDescription.setText(editTask.getDescription());
		PopulateCategoryComboBox();
		UpdatePriorityTextBox();
		this.setTitle(LanguageManager
				.getString("AddEditDialog_Edit_Dialog_Title"));
		this.setVisible(true);

	}

	/**
	 * Returns true if the user closed the AddEditDialog by
	 *              clicking OK, false otherwise.
	 * @author bjorn
	 * @return True if the dialog was closed by clicking OK, false otherwise. 
	 */
	public Boolean clickedOK() {
		return clickedOK;
	}

	/**
	 * Returns a Task with the information the user has entered.
	 *              Should only be called if clickedOK is true.
	 * @author bjorn
	 * @return The task created or edited in the AddEditDialog.  
	 */
	public Task getTask() {
		return task;
	}

}