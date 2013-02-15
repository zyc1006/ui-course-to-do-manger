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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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

import se.uu.it.todomanger.controller.CategoryManager;
import se.uu.it.todomanger.model.Category;
// Todo manager imports
import se.uu.it.todomanger.model.Task;

/**
 * AddEditDialog
 * 
 * @author bjorn
 * @author sara
 * @description A class that shows a
 */
public class AddEditDialog extends JDialog {

	/**
	 * serialVersionUID
	 * 
	 * @author Eclipse
	 */
	private static final long serialVersionUID = 1L;

	// CONSTANTS for dialog language and look:

	// Priority slider data
	public static final int ADDEDITDIALOG_PRIORITY_MIN = 0;
	public static final int ADDEDITDIALOG_PRIORITY_MAX = 2;
	public static final int ADDEDITDIALOG_PRIORITY_TICK_SPACING = 1;
	public static final Boolean ADDEDITDIALOG_PRIORITY_SHOW_TICKS = true;
	public static final Boolean ADDEDITDIALOG_PRIORITY_SHOW_LABELS = true;

	// Language data
	public static final int ADDEDITDIALOG_LABEL_FILL = GridBagConstraints.NONE;

	// Size of regular text fields
	public static final int ADDEDITDIALOG_WIDETEXT_WIDTH = 350;
	public static final int ADDEDITDIALOG_WIDETEXT_HEIGHT = 22;

	// Size of multirow text fields
	public static final int ADDEDITDIALOG_LONGTEXT_WIDTH = 350;
	public static final int ADDEDITDIALOG_LONGTEXT_HEIGHT = 64;
	public static final int ADDEDITDIALOG_SHORTTEXT_WIDTH = 48;
	public static final int ADDEDITDIALOG_SHORTTEXT_HEIGHT = 22;
	public static final int ADDEDITDIALOG_SLIDER_WIDTH = 350;
	public static final int ADDEDITDIALOG_SLIDER_HEIGHT = 48;

	// Size of buttons
	public static final int ADDEDITDIALOG_BUTTON_WIDTH = 50;
	public static final int ADDEDITDIALOG_BUTTON_HEIGHT = 36;

	// Padding of GridBagView
	public static final int ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP = 10;
	public static final int ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT = 10;

	public static final int ADDEDITDIALOG_LAYOUT_LABEL_PADDING_LEFT = 10;
	public static final int ADDEDITDIALOG_LAYOUT_LABEL_PADDING_BOTTOM = 0;

	public static final int ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_LEFT = 0;
	public static final int ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_BOTTOM = 0;

	public static final int ADDEDITDIALOG_LAYOUT_BUTTON_PADDING_LEFT = 10;
	public static final int ADDEDITDIALOG_LAYOUT_BUTTON_PADDING_BOTTOM = 10;

	// Grid cell filling
	public static final int ADDEDITDIALOG_LAYOUT_WIDETEXT_FILL = GridBagConstraints.HORIZONTAL;
	public static final int ADDEDITDIALOG_LAYOUT_LONGTEXT_FILL = GridBagConstraints.BOTH;
	public static final int ADDEDITDIALOG_LAYOUT_SHORTTEXT_FILL = GridBagConstraints.NONE;
	public static final int ADDEDITDIALOG_LAYOUT_SLIDER_FILL = GridBagConstraints.HORIZONTAL;
	public static final int ADDEDITDIALOG_LAYOUT_BUTTONS_FILL = GridBagConstraints.NONE;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_FILL = GridBagConstraints.BOTH;

	// Data for label column
	public static final int ADDEDITDIALOG_LAYOUT_LABEL_ANCHOR = GridBagConstraints.EAST;
	public static final int ADDEDITDIALOG_LAYOUT_LABEL_COLUMN = 0;
	public static final int ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_SPAN = 1;
	public static final double ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_WEIGHT = 0.0;

	// Data for standard field column
	public static final int ADDEDITDIALOG_LAYOUT_FIELDS_ANCHOR = GridBagConstraints.WEST;
	public static final int ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN = 1;
	public static final int ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_SPAN = 5;
	public static final double ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_WEIGHT = 1.0;

	// Data for date columns
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_ANCHOR = GridBagConstraints.WEST;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_DATE_COLUMN = 1;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_DATE_COLUMN_SPAN = 3;
	public static final double ADDEDITDIALOG_LAYOUT_DATEFIELD_DATE_COLUMN_WEIGHT = 0.6;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_HOUR_COLUMN = 4;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_HOUR_COLUMN_SPAN = 1;
	public static final double ADDEDITDIALOG_LAYOUT_DATEFIELD_HOUR_COLUMN_WEIGHT = 0.2;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_MINUTE_COLUMN = 5;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_MINUTE_COLUMN_SPAN = 1;
	public static final double ADDEDITDIALOG_LAYOUT_DATEFIELD_MINUTE_COLUMN_WEIGHT = 0.2;

	// Data for buttons
	public static final int ADDEDITDIALOG_LAYOUT_BUTTONS_ANCHOR = GridBagConstraints.EAST;
	public static final int ADDEDITDIALOG_LAYOUT_BUTTONS_COLUMN_SPAN = 6;

	// Placement data for components in GridBagLayout
	public static final int ADDEDITDIALOG_LAYOUT_TITLE_ROW = 0;
	public static final double ADDEDITDIALOG_LAYOUT_TITLE_ROW_WEIGHT = 0.0;
	public static final int ADDEDITDIALOG_LAYOUT_DUEDATE_ROW = 1;
	public static final double ADDEDITDIALOG_LAYOUT_DUEDATE_ROW_WEIGHT = 0.0;
	public static final int ADDEDITDIALOG_LAYOUT_CATEGORY_ROW = 2;
	public static final double ADDEDITDIALOG_LAYOUT_CATEGORY_ROW_WEIGHT = 0.0;
	public static final int ADDEDITDIALOG_LAYOUT_PRIORITY_ROW = 3;
	public static final double ADDEDITDIALOG_LAYOUT_PRIORITY_ROW_WEIGHT = 0.0;
	public static final int ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW = 4;
	public static final double ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW_WEIGHT = 1.0;
	public static final int ADDEDITDIALOG_LAYOUT_BUTTONS_ROW = 5;
	public static final double ADDEDITDIALOG_LAYOUT_BUTTONS_ROW_WEIGHT = 0.0;
	public static final int ADDEDITDIALOG_LAYOUT_BUTTONS_COLUMN = 0;
	public static final Boolean ADDEDITDIALOG_LAYOUT_ORDER_CANCELOK = true;

	// Window data
	public static final Boolean ADDEDITDIALOG_WINDOW_RESIZABLE = true;
	
	// END OF CONSTANTS

	// For keeping track of which mode the AddEditDialog is in.
	public static enum DialogMode {
		ADD_DIALOG, EDIT_DIALOG
	}

	// Private definitions
	private Task task;
	private Boolean clickedOK;
	private DialogMode dialogMode;

	private JTextField txtTitle;
	private JTextField txtDueDate;
	private JComboBox cmbDueDateHour;
	private JComboBox cmbDueDateMinute;
	private JTextArea txtDescription;
	private JSlider sliPriority;
	private JComboBox cmbCategory;

	private Dimension wideTextDimension = new Dimension(
			ADDEDITDIALOG_WIDETEXT_WIDTH, ADDEDITDIALOG_WIDETEXT_HEIGHT);
	private Dimension dateFieldDimension = new Dimension(
			ADDEDITDIALOG_SHORTTEXT_WIDTH, ADDEDITDIALOG_SHORTTEXT_HEIGHT);
	private Dimension sliderDimension = new Dimension(
			ADDEDITDIALOG_SLIDER_WIDTH, ADDEDITDIALOG_SLIDER_HEIGHT);
	private Dimension longTextDimension = new Dimension(
			ADDEDITDIALOG_LONGTEXT_WIDTH, ADDEDITDIALOG_LONGTEXT_HEIGHT);
	private Dimension buttonDimension = new Dimension(
			ADDEDITDIALOG_BUTTON_WIDTH, ADDEDITDIALOG_BUTTON_HEIGHT);
	
	// A resource bundle for keeping track of language information.
	private ResourceBundle resLocale;

	// The content pane of the dialog
	private Container gridBagContainer;

	/**
	 * AddEditDialog
	 * 
	 * @author bjoern
	 * @author sara
	 * @description The constructor for the AddEditDialog, which defines the
	 *              GUI.
	 */
	public AddEditDialog() {
		
		// Step 0: Load the locale resource.
		
		// Load the resource bundle for the current locale.
		try {
			resLocale = ResourceBundle.getBundle("locale.ToDoManager", 
		                                         Locale.getDefault());
		} catch (MissingResourceException mre) {
		    System.err.println("res/locale/ToDoManager.properties not found");
		    System.exit(1);
		}

		// Step 1: Create and set properties of components

		// Information labels
		JLabel lblTitle = new JLabel(resLocale.getString("AddEditDialog_Title_Label"));
		JLabel lblDueDate = new JLabel(resLocale.getString("AddEditDialog_DueDate_Label"));
		JLabel lblCategory = new JLabel(resLocale.getString("AddEditDialog_Category_Label"));
		JLabel lblPriority = new JLabel(resLocale.getString("AddEditDialog_Priority_Label"));
		JLabel lblDescription = new JLabel(resLocale.getString("AddEditDialog_Description_Label"));

		// Text field for title
		txtTitle = new JTextField();
		txtTitle.setPreferredSize(wideTextDimension);

		// Combo box for categories
		cmbCategory = new JComboBox();
		cmbCategory.setPreferredSize(wideTextDimension);

		// Text fields for due date
		txtDueDate = new JTextField();
		txtDueDate.setPreferredSize(dateFieldDimension);
		CalendarPanel cp = CalendarPanel.getInstance();
		cp.register(txtDueDate);
		cmbDueDateHour = new JComboBox();
		cmbDueDateHour.setPreferredSize(dateFieldDimension);
		cmbDueDateMinute = new JComboBox();
		cmbDueDateMinute.setPreferredSize(dateFieldDimension);

		// JSlider for Priority
		sliPriority = new JSlider();
		sliPriority.setPreferredSize(sliderDimension);
		sliPriority.setMinimum(ADDEDITDIALOG_PRIORITY_MIN);
		sliPriority.setMaximum(ADDEDITDIALOG_PRIORITY_MAX);
		sliPriority.setMajorTickSpacing(ADDEDITDIALOG_PRIORITY_TICK_SPACING);

		Dictionary<Integer, JLabel> priorityLabels = new Hashtable<Integer, JLabel>();
		priorityLabels.put(ADDEDITDIALOG_PRIORITY_MIN, new JLabel(
				resLocale.getString("AddEditDialog_Priority_Low_Label")));
		priorityLabels.put(ADDEDITDIALOG_PRIORITY_MAX, new JLabel(
				resLocale.getString("AddEditDialog_Priority_High_Label")));

		sliPriority.setLabelTable(priorityLabels);
		sliPriority.setPaintLabels(ADDEDITDIALOG_PRIORITY_SHOW_LABELS);
		sliPriority.setPaintTicks(ADDEDITDIALOG_PRIORITY_SHOW_TICKS);

		// JTextFields for Description
		txtDescription = new JTextArea();
		txtDescription.setPreferredSize(longTextDimension);

		// JButtons for OK and Cancel
		JButton btnOK = new JButton(resLocale.getString("AddEditDialog_OK_Label"));
		btnOK.setSize(buttonDimension);
		JButton btnCancel = new JButton(resLocale.getString("AddEditDialog_Cancel_Label"));
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

		// Common information for the labels
		labelConstraint.insets = new Insets(
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP,
				ADDEDITDIALOG_LAYOUT_LABEL_PADDING_LEFT,
				ADDEDITDIALOG_LAYOUT_LABEL_PADDING_BOTTOM,
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT);
		labelConstraint.gridx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN;
		labelConstraint.gridwidth = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_SPAN;
		labelConstraint.anchor = ADDEDITDIALOG_LAYOUT_LABEL_ANCHOR;
		labelConstraint.fill = ADDEDITDIALOG_LABEL_FILL;
		labelConstraint.weightx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_WEIGHT;

		// Common information for the fields/sliders/combobox
		fieldsConstraint.insets = new Insets(
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP,
				ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_LEFT,
				ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_BOTTOM,
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT);
		fieldsConstraint.gridx = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN;
		fieldsConstraint.gridwidth = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_SPAN;
		fieldsConstraint.anchor = ADDEDITDIALOG_LAYOUT_FIELDS_ANCHOR;
		fieldsConstraint.weightx = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_WEIGHT;

		// Common information for the date fields
		dateConstraint.insets = new Insets(
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP,
				ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_LEFT,
				ADDEDITDIALOG_LAYOUT_FIELDS_PADDING_BOTTOM,
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT);
		dateConstraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		dateConstraint.anchor = ADDEDITDIALOG_LAYOUT_DATEFIELD_ANCHOR;
		dateConstraint.fill = ADDEDITDIALOG_LAYOUT_DATEFIELD_FILL;

		// Common information for the buttons
		buttonConstraint.insets = new Insets(
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_TOP,
				ADDEDITDIALOG_LAYOUT_BUTTON_PADDING_LEFT,
				ADDEDITDIALOG_LAYOUT_BUTTON_PADDING_BOTTOM,
				ADDEDITDIALOG_LAYOUT_COMMON_PADDING_RIGHT);
		buttonConstraint.weighty = ADDEDITDIALOG_LAYOUT_BUTTONS_ROW_WEIGHT;
		buttonConstraint.gridx = ADDEDITDIALOG_LAYOUT_BUTTONS_COLUMN;
		buttonConstraint.gridy = ADDEDITDIALOG_LAYOUT_BUTTONS_ROW;
		buttonConstraint.gridwidth = ADDEDITDIALOG_LAYOUT_BUTTONS_COLUMN_SPAN;
		buttonConstraint.anchor = ADDEDITDIALOG_LAYOUT_BUTTONS_ANCHOR;
		buttonConstraint.fill = ADDEDITDIALOG_LAYOUT_BUTTONS_FILL;

		// Add Title components
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_TITLE_ROW;
		gridBagContainer.add(lblTitle, labelConstraint);
		fieldsConstraint.weighty = ADDEDITDIALOG_LAYOUT_TITLE_ROW_WEIGHT;
		fieldsConstraint.gridy = ADDEDITDIALOG_LAYOUT_TITLE_ROW;
		fieldsConstraint.fill = ADDEDITDIALOG_LAYOUT_WIDETEXT_FILL;
		gridBagContainer.add(txtTitle, fieldsConstraint);

		// Add Category components
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_CATEGORY_ROW;
		gridBagContainer.add(lblCategory, labelConstraint);
		fieldsConstraint.weighty = ADDEDITDIALOG_LAYOUT_CATEGORY_ROW_WEIGHT;
		fieldsConstraint.gridy = ADDEDITDIALOG_LAYOUT_CATEGORY_ROW;
		fieldsConstraint.fill = ADDEDITDIALOG_LAYOUT_WIDETEXT_FILL;
		gridBagContainer.add(cmbCategory, fieldsConstraint);

		// Add Date components
		labelConstraint.weighty = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW_WEIGHT;
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		gridBagContainer.add(lblDueDate, labelConstraint);
		dateConstraint.gridx = ADDEDITDIALOG_LAYOUT_DATEFIELD_DATE_COLUMN;
		dateConstraint.gridwidth = ADDEDITDIALOG_LAYOUT_DATEFIELD_DATE_COLUMN_SPAN;
		dateConstraint.weightx = ADDEDITDIALOG_LAYOUT_DATEFIELD_DATE_COLUMN_WEIGHT;
		gridBagContainer.add(txtDueDate, dateConstraint);
		dateConstraint.gridx = ADDEDITDIALOG_LAYOUT_DATEFIELD_HOUR_COLUMN;
		dateConstraint.gridwidth = ADDEDITDIALOG_LAYOUT_DATEFIELD_HOUR_COLUMN_SPAN;
		dateConstraint.weightx = ADDEDITDIALOG_LAYOUT_DATEFIELD_HOUR_COLUMN_WEIGHT;
		gridBagContainer.add(cmbDueDateHour, dateConstraint);
		dateConstraint.gridx = ADDEDITDIALOG_LAYOUT_DATEFIELD_MINUTE_COLUMN;
		dateConstraint.gridwidth = ADDEDITDIALOG_LAYOUT_DATEFIELD_MINUTE_COLUMN_SPAN;
		dateConstraint.weightx = ADDEDITDIALOG_LAYOUT_DATEFIELD_MINUTE_COLUMN_WEIGHT;
		gridBagContainer.add(cmbDueDateMinute, dateConstraint);

		// Add priority components
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_PRIORITY_ROW;
		gridBagContainer.add(lblPriority, labelConstraint);
		fieldsConstraint.weighty = ADDEDITDIALOG_LAYOUT_PRIORITY_ROW_WEIGHT;
		fieldsConstraint.gridy = ADDEDITDIALOG_LAYOUT_PRIORITY_ROW;
		fieldsConstraint.fill = ADDEDITDIALOG_LAYOUT_SLIDER_FILL;
		gridBagContainer.add(sliPriority, fieldsConstraint);

		// Add description components
		labelConstraint.gridy = ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW;
		gridBagContainer.add(lblDescription, labelConstraint);
		fieldsConstraint.weighty = ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW_WEIGHT;
		fieldsConstraint.gridy = ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW;
		fieldsConstraint.fill = ADDEDITDIALOG_LAYOUT_LONGTEXT_FILL;
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
	 * OKButtonHandler
	 * 
	 * @author bjorn
	 * @description Creates/Edits the AddEditDialogs Task with the data the user
	 *              has entered.
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
			errorMessage += resLocale.getString("AddEditDialog_Error_NoTitle") + "\n";
			incorrectDataFormat = true;
		}
		// Date has to be set
		if (txtDueDate.getText().toString().isEmpty()) {
			errorMessage += resLocale.getString("AddEditDialog_Error_NoDate") + "\n";
			incorrectDataFormat = true;
		}
		// Create the date object from the user information.
		try {
			taskDueDate = dateFormat.parse(taskDueDateString);
		} catch (ParseException e) {
			errorMessage += resLocale.getString("AddEditDialog_Error_DateInvalid") + "\n";
			incorrectDataFormat = true;
		}
		// If an error occurred, display the error message.
		if (incorrectDataFormat) {
			JOptionPane
					.showMessageDialog(this, errorMessage,
							resLocale.getString("AddEditDialog_Error_Dialog_Title"),
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
				//temp task id
				task = new Task(Task.nexttaskid++, taskTitle, taskDueDate, taskCategory,
						taskDescription, taskPriority, false);
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
	 * CancelButtonHandler
	 * 
	 * @author bjorn
	 * @description Closes the dialog when the user presses cancel.
	 */
	private void CancelButtonHandler() {
		HideAddEditDialog();
	}

	/**
	 * HideAddEditDialog
	 * 
	 * @author bjorn
	 * @description Closes the dialog.
	 */
	public void HideAddEditDialog() {
		this.setVisible(false);
	}

	/**
	 * PopulateCategoryComboBox()
	 * 
	 * @description Adds all the categories to the combobox.
	 */
	private void PopulateCategoryComboBox() {
		cmbCategory.removeAllItems();
//		cmbCategory.addItem("HARDCODED_TEST_1");
//		cmbCategory.addItem("HARDCODED_TEST_2");
//		cmbCategory.addItem("HARDCODED_TEST_3");
		
		CategoryManager cm = CategoryManager.getInstance();
		
		HashMap<Integer, Category> cat = cm.getCategories();
		for(int i = 0; i < cat.size(); i++){
			cmbCategory.addItem(cat.get(i).getCategoryTitle());
		}
		cmbCategory.updateUI();
	}

	/**
	 * PopulateHourComboBox
	 * 
	 * @description Adds the hours of the clock to the hour combo box.
	 */
	private void PopulateHourComboBox() {
		cmbDueDateHour.removeAll();
		for (int hour = 0; hour < 24; hour++) {
			cmbDueDateHour.addItem(String.format("%02d", hour));
		}
		cmbDueDateHour.updateUI();
	}

	/**
	 * PopulateMinuteComboBox
	 * 
	 * @description Adds the quarters of the clock to the minute combo box.
	 */
	private void PopulateMinuteComboBox() {
		cmbDueDateMinute.removeAll();
		for (int minute = 0; minute < 60; minute++) {
			cmbDueDateMinute.addItem(String.format("%02d", minute));
		}
		cmbDueDateMinute.updateUI();
	}

	/**
	 * ShowAddDialog
	 * 
	 * @author bjorn
	 * @description Displays the AddEditDialog with empty fields for creating a
	 *              new task.
	 */
	public void ShowAddDialog() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				CalendarPanel.dateFormat);
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
		SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
		dialogMode = DialogMode.ADD_DIALOG;
		clickedOK = false;
		txtDueDate.setText(dateFormat.format(currentDate));
		// Select the quarter closest to the current time.
		cmbDueDateMinute.setSelectedIndex(currentDate.getMinutes());
		cmbDueDateHour.setSelectedIndex(currentDate.getHours());
		PopulateCategoryComboBox();
		this.setTitle(resLocale.getString("AddEditDialog_Add_Dialog_Title"));
		this.setVisible(true);
	}

	/**
	 * ShowEditDialog
	 * 
	 * @author bjorn
	 * @param editTask
	 *            - The task that contains the data to be edited.
	 * @description Displays the AddEditDialog with the fields filled with the
	 *              data from editTask.
	 */
	public void ShowEditDialog(Task editTask) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				CalendarPanel.dateFormat);
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
		SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
		dialogMode = DialogMode.EDIT_DIALOG;
		task = editTask;
		clickedOK = false;
		txtTitle.setText(editTask.getTitle());
		txtDueDate.setText(dateFormat.format(editTask.getDueDate()));
		// Select the quarter closest to the current time.
		cmbDueDateMinute.setSelectedIndex(editTask.getDueDate().getMinutes());
		cmbDueDateHour.setSelectedIndex(editTask.getDueDate().getHours());
		sliPriority.setValue(editTask.getPriority());
		PopulateCategoryComboBox();
		this.setTitle(resLocale.getString("AddEditDialog_Edit_Dialog_Title"));
		this.setVisible(true);

	}

	/**
	 * clickedOK
	 * 
	 * @author bjorn
	 * @description Returns true if the user closed the AddEditDialog by
	 *              clicking OK, false otherwise.
	 */
	public Boolean clickedOK() {
		return clickedOK;
	}

	/**
	 * getTask
	 * 
	 * @author bjorn
	 * @description Returns a Task with the information the user has entered.
	 *              Should only be called if clickedOK is true.
	 */
	public Task getTask() {
		return task;
	}

	// OUTSIDE
	// Just for testing purposes
//	public static void main(String[] args) {
//
//		Task theTask = new Task(1, "first", new Date(2012 - 1900, 12 - 1, 1,
//				22, 52), 1, "hello", 5, true);
//
//		AddEditDialog addEdit = new AddEditDialog();
//		addEdit.ShowAddDialog();
//		if (addEdit.clickedOK()) {
//			theTask = addEdit.getTask();
//		}
//
//		for (int i = 0; i < 2; i++) {
//			addEdit.ShowEditDialog(theTask);
//			if (addEdit.clickedOK()) {
//				theTask = addEdit.getTask();
//			}
//		}
//	}

}