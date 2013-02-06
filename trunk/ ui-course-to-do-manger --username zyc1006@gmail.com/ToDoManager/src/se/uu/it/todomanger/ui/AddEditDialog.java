/* TBD by Bjoern and Sara */

package se.uu.it.todomanger.ui;

// Imports
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

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
	public static final String ADDEDITDIALOG_ADD_DIALOG_TITLE = "Add new task";
	public static final String ADDEDITDIALOG_EDIT_DIALOG_TITLE = "Edit task";
	public static final String ADDEDITDIALOG_OK_LABEL = "OK";
	public static final String ADDEDITDIALOG_CANCEL_LABEL = "Cancel";
	public static final String ADDEDITDIALOG_TITLE_LABEL = "Title:";
	public static final String ADDEDITDIALOG_DUEDATE_LABEL = "Due date (YMDhm):";
	public static final String ADDEDITDIALOG_CATEGORY_LABEL = "Category:";
	public static final String ADDEDITDIALOG_PRIORITY_LABEL = "Priority:";
	public static final String ADDEDITDIALOG_DESCRIPTION_LABEL = "Description:";
	public static final String ADDEDITDIALOG_PRIORITY_LOW_LABEL = "Low";
	public static final String ADDEDITDIALOG_PRIORITY_HIGH_LABEL = "High";
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
	public static final int ADDEDITDIALOG_LAYOUT_PADDING_X = 5;
	public static final int ADDEDITDIALOG_LAYOUT_PADDING_Y = 5;

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
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_YEAR = 1;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_MONTH = 2;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_DAY = 3;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_HOUR = 4;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_MINUTE = 5;
	public static final int ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_SPAN = 1;
	public static final double ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_WEIGHT = 0.2;

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

	// Error messages
	public static final String ADDEDITDIALOG_ERROR_NOTITLE = "Please enter a title.";
	public static final String ADDEDITDIALOG_ERROR_INVALIDMONTH = "Please enter a month between 1 and 12.";
	public static final String ADDEDITDIALOG_ERROR_INVALIDDAY = "Please enter a day between 1 and 31.";
	public static final String ADDEDITDIALOG_ERROR_INVALIDHOUR = "Please enter an hour between 0 and 23.";
	public static final String ADDEDITDIALOG_ERROR_INVALIDMINUTE = "Please enter a minute between 0 and 59.";
	public static final String ADDEDITDIALOG_ERROR_DIALOG_TITLE = "Error";
	public static final String ADDEDITDIALOG_ERROR_DATEINVALID = "The selected date doesn't exist.";

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
	private JTextField txtDueDateYear;
	private JTextField txtDueDateMonth;
	private JTextField txtDueDateDay;
	private JTextField txtDueDateHour;
	private JTextField txtDueDateMinute;
	private JTextArea txtDescription;
	private JSlider sliPriority;
	private JComboBox cmbCategory;

	// The content pane of the dialog
	Container container = null;

	/**
	 * AddEditDialog
	 * 
	 * @author bjoern
	 * @author sara
	 * @description The constructor for the AddEditDialog, which defines the
	 *              GUI.
	 */
	public AddEditDialog() {

		// Use the GridLayout for the dialog.
		container = this.getContentPane();
		container.setLayout(new GridBagLayout());

		// A GridBagConstraints for placing the controls in the dialog.
		GridBagConstraints constraint = new GridBagConstraints();

		// Information labels
		JLabel lblTitle = new JLabel(ADDEDITDIALOG_TITLE_LABEL);
		JLabel lblDueDate = new JLabel(ADDEDITDIALOG_DUEDATE_LABEL);
		JLabel lblCategory = new JLabel(ADDEDITDIALOG_CATEGORY_LABEL);
		JLabel lblPriority = new JLabel(ADDEDITDIALOG_PRIORITY_LABEL);
		JLabel lblDescription = new JLabel(ADDEDITDIALOG_DESCRIPTION_LABEL);

		// Text field for title
		txtTitle = new JTextField();
		txtTitle.setPreferredSize(new Dimension(ADDEDITDIALOG_WIDETEXT_WIDTH,
				ADDEDITDIALOG_WIDETEXT_HEIGHT));

		// Combo box for categories
		cmbCategory = new JComboBox();
		cmbCategory.setPreferredSize(new Dimension(
				ADDEDITDIALOG_WIDETEXT_WIDTH, ADDEDITDIALOG_WIDETEXT_HEIGHT));

		cmbCategory.setEnabled(false);

		// Text fields for due date
		txtDueDateYear = new JTextField();
		txtDueDateYear.setPreferredSize(new Dimension(
				ADDEDITDIALOG_SHORTTEXT_WIDTH, ADDEDITDIALOG_SHORTTEXT_HEIGHT));
		txtDueDateMonth = new JTextField();
		txtDueDateMonth.setPreferredSize(new Dimension(
				ADDEDITDIALOG_SHORTTEXT_WIDTH, ADDEDITDIALOG_SHORTTEXT_HEIGHT));
		txtDueDateDay = new JTextField();
		txtDueDateDay.setPreferredSize(new Dimension(
				ADDEDITDIALOG_SHORTTEXT_WIDTH, ADDEDITDIALOG_SHORTTEXT_HEIGHT));
		txtDueDateHour = new JTextField();
		txtDueDateHour.setPreferredSize(new Dimension(
				ADDEDITDIALOG_SHORTTEXT_WIDTH, ADDEDITDIALOG_SHORTTEXT_HEIGHT));
		txtDueDateMinute = new JTextField();
		txtDueDateMinute.setPreferredSize(new Dimension(
				ADDEDITDIALOG_SHORTTEXT_WIDTH, ADDEDITDIALOG_SHORTTEXT_HEIGHT));

		// JSlider for Priority
		sliPriority = new JSlider();
		sliPriority.setPreferredSize(new Dimension(ADDEDITDIALOG_SLIDER_WIDTH,
				ADDEDITDIALOG_SLIDER_HEIGHT));
		sliPriority.setMinimum(ADDEDITDIALOG_PRIORITY_MIN);
		sliPriority.setMaximum(ADDEDITDIALOG_PRIORITY_MAX);
		sliPriority.setMajorTickSpacing(ADDEDITDIALOG_PRIORITY_TICK_SPACING);

		Dictionary<Integer, JLabel> priorityLabels = new Hashtable<Integer, JLabel>();
		priorityLabels.put(ADDEDITDIALOG_PRIORITY_MIN, new JLabel(
				ADDEDITDIALOG_PRIORITY_LOW_LABEL));
		priorityLabels.put(ADDEDITDIALOG_PRIORITY_MAX, new JLabel(
				ADDEDITDIALOG_PRIORITY_HIGH_LABEL));

		sliPriority.setLabelTable(priorityLabels);
		sliPriority.setPaintLabels(ADDEDITDIALOG_PRIORITY_SHOW_LABELS);
		sliPriority.setPaintTicks(ADDEDITDIALOG_PRIORITY_SHOW_TICKS);

		// JTextFields for Description
		txtDescription = new JTextArea();
		txtDescription.setPreferredSize(new Dimension(
				ADDEDITDIALOG_LONGTEXT_WIDTH, ADDEDITDIALOG_LONGTEXT_HEIGHT));

		// JButtons for OK and Cancel
		JButton btnCancel = new JButton(ADDEDITDIALOG_CANCEL_LABEL);
		JButton btnOK = new JButton(ADDEDITDIALOG_OK_LABEL);

		btnCancel.setSize(ADDEDITDIALOG_BUTTON_WIDTH,
				ADDEDITDIALOG_BUTTON_HEIGHT);
		btnOK.setSize(ADDEDITDIALOG_BUTTON_WIDTH, ADDEDITDIALOG_BUTTON_HEIGHT);

		// Action handlers for buttons
		btnOK.addActionListener(new ActionListener() {
			@Override
			/** actionPerformed
			 * @author bjoern
			 * @author sara
			 * @param event
			 * @description Handles the click of the OK button.
			 */
			public void actionPerformed(ActionEvent event) {
				OKButtonHandler();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			/** actionPerformed
			 * @author bjoern
			 * @author sara
			 * @param event
			 * @description Handles the click of the Cancel button.
			 */
			public void actionPerformed(ActionEvent event) {
				CancelButtonHandler();
			}
		});

		// Set padding for the GridBagView
		constraint.ipadx = ADDEDITDIALOG_LAYOUT_PADDING_X;
		constraint.ipady = ADDEDITDIALOG_LAYOUT_PADDING_Y;

		// Add Title components
		constraint.weighty = ADDEDITDIALOG_LAYOUT_TITLE_ROW_WEIGHT;
		constraint.gridx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_TITLE_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_LABEL_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LABEL_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_WEIGHT;
		container.add(lblTitle, constraint);
		constraint.gridx = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_TITLE_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_FIELDS_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_WIDETEXT_FILL;
		container.add(txtTitle, constraint);

		// Add Category components
		constraint.weighty = ADDEDITDIALOG_LAYOUT_CATEGORY_ROW_WEIGHT;
		constraint.gridx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_CATEGORY_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_LABEL_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LABEL_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_WEIGHT;
		container.add(lblCategory, constraint);
		constraint.gridx = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_CATEGORY_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_FIELDS_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_WIDETEXT_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_WEIGHT;
		container.add(cmbCategory, constraint);

		// Add Date components
		constraint.weighty = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW_WEIGHT;

		constraint.gridx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_LABEL_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LABEL_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_WEIGHT;
		container.add(lblDueDate, constraint);

		constraint.gridx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_YEAR;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_DATEFIELD_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_DATEFIELD_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_WEIGHT;
		container.add(txtDueDateYear, constraint);

		constraint.gridx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_MONTH;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_DATEFIELD_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_DATEFIELD_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_WEIGHT;
		container.add(txtDueDateMonth, constraint);

		constraint.gridx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_DAY;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_DATEFIELD_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_DATEFIELD_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_WEIGHT;
		container.add(txtDueDateDay, constraint);

		constraint.gridx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_HOUR;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_DATEFIELD_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_DATEFIELD_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_WEIGHT;
		container.add(txtDueDateHour, constraint);

		constraint.gridx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_MINUTE;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_DUEDATE_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_DATEFIELD_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_DATEFIELD_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_DATEFIELD_COLUMN_WEIGHT;
		container.add(txtDueDateMinute, constraint);

		// Add priority components
		constraint.weighty = ADDEDITDIALOG_LAYOUT_PRIORITY_ROW_WEIGHT;
		constraint.gridx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_PRIORITY_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_LABEL_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LABEL_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_WEIGHT;
		container.add(lblPriority, constraint);
		constraint.gridx = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_PRIORITY_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_FIELDS_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_SLIDER_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_WEIGHT;
		container.add(sliPriority, constraint);

		// Add description components
		constraint.weighty = ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW_WEIGHT;
		constraint.gridx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_LABEL_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LABEL_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_LABEL_COLUMN_WEIGHT;
		container.add(lblDescription, constraint);
		constraint.gridx = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_DESCRIPTION_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_FIELDS_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_LONGTEXT_FILL;
		constraint.weightx = ADDEDITDIALOG_LAYOUT_FIELDS_COLUMN_WEIGHT;
		container.add(txtDescription, constraint);

		// Add OK/Cancel components
		constraint.weighty = ADDEDITDIALOG_LAYOUT_BUTTONS_ROW_WEIGHT;
		JPanel pnlButtonPanel = new JPanel();

		constraint.gridx = ADDEDITDIALOG_LAYOUT_BUTTONS_COLUMN;
		constraint.gridy = ADDEDITDIALOG_LAYOUT_BUTTONS_ROW;
		constraint.gridwidth = ADDEDITDIALOG_LAYOUT_BUTTONS_COLUMN_SPAN;
		constraint.anchor = ADDEDITDIALOG_LAYOUT_BUTTONS_ANCHOR;
		constraint.fill = ADDEDITDIALOG_LAYOUT_BUTTONS_FILL;
		if (ADDEDITDIALOG_LAYOUT_ORDER_CANCELOK) {
			pnlButtonPanel.add(btnCancel);
			pnlButtonPanel.add(btnOK);
		} else {
			pnlButtonPanel.add(btnOK);
			pnlButtonPanel.add(btnCancel);
		}
		container.add(pnlButtonPanel, constraint);

		// Dialog settings
		this.setModal(true);
		this.pack();
		this.setResizable(ADDEDITDIALOG_WINDOW_RESIZABLE);
		this.setLocationRelativeTo(null);
	}

	/**
	 * OKButtonHandler
	 * 
	 * @author bjorn
	 * @description Creates/Edits the AddEditDialogs Task with the data the user
	 *              has entered.
	 */
	private void OKButtonHandler() {

		// Create variables with the data from the UI.
		String taskTitle = txtTitle.getText();
		int taskDueDateYear = Integer.parseInt("0" + txtDueDateYear.getText()) - 1900;
		int taskDueDateMonth = Integer
				.parseInt("0" + txtDueDateMonth.getText()) - 1;
		int taskDueDateDay = Integer.parseInt("0" + txtDueDateDay.getText());
		int taskDueDateHour = Integer.parseInt("0" + txtDueDateHour.getText());
		int taskDueDateMinute = Integer.parseInt("0"
				+ txtDueDateMinute.getText());
		int taskCategory = cmbCategory.getSelectedIndex();
		String taskDescription = txtDescription.getText();
		int taskPriority = sliPriority.getValue();


		// Make sure the user has entered correct data
		String errorMessage = "";
		Boolean noError = true;
		
		// There has to be a title
		if (txtTitle.getText().length() == 0) {
			errorMessage += ADDEDITDIALOG_ERROR_NOTITLE;
			noError = false;
		}
		// Month has to be between 1 and 12.
		if (taskDueDateMonth > 12 || taskDueDateMonth < 1) {
			errorMessage += ADDEDITDIALOG_ERROR_INVALIDMONTH;
			noError = false;
		}
		// Day has to be between 1 and 31.
		if (taskDueDateDay > 31 || taskDueDateMonth < 1) {
			errorMessage += ADDEDITDIALOG_ERROR_INVALIDDAY;
			noError = false;
		}
		// Hour has to be between 0 and 23
		if (taskDueDateHour > 23 || taskDueDateMonth < 0) {
			errorMessage += ADDEDITDIALOG_ERROR_INVALIDHOUR;
			noError = false;
		}
		// Minute has to be between 0 and 59.
		if (taskDueDateMinute > 59 || taskDueDateMinute < 0) {
			errorMessage += ADDEDITDIALOG_ERROR_INVALIDMINUTE;
			noError = false;
		}

		// If an error occured, display the error message.
		if (!noError) {
			JOptionPane
					.showMessageDialog(this, errorMessage,
							ADDEDITDIALOG_ERROR_DIALOG_TITLE,
							JOptionPane.ERROR_MESSAGE);
		} else {

			// Create the date object from the user information.
			Date taskDueDate = new Date(taskDueDateYear, taskDueDateMonth,
					taskDueDateDay, taskDueDateHour, taskDueDateMinute);
			
			// If the Date doesn't create the same date the user has entered, display an error message.
			// This happens if a user enters for example if the user enters 31th of February (a month which
			// only has 28 (or 29) days, the Date object will then refer to another date than the user
			// entered, which is bad usability.
			if (taskDueDate.getYear() != taskDueDateYear
					|| taskDueDate.getMonth() != taskDueDateMonth
					|| taskDueDate.getDate() != taskDueDateDay
					|| taskDueDate.getHours() != taskDueDateHour
					|| taskDueDate.getMinutes() != taskDueDateMinute) {
				JOptionPane.showMessageDialog(this,
						ADDEDITDIALOG_ERROR_DATEINVALID,
						ADDEDITDIALOG_ERROR_DIALOG_TITLE,
						JOptionPane.ERROR_MESSAGE);
			} else {

				// If no errors occured, we start the Task creation/changing.
				
				// Make sure the calling class knows we have clicked OK.
				clickedOK = true;
				
				// If the task is a new task, create it. Otherwise edit the
				// provided task.
				if (dialogMode == DialogMode.ADD_DIALOG) {
					task = new Task(1, taskTitle, taskDueDate, taskCategory,
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
	 * ShowAddDialog
	 * 
	 * @author bjorn
	 * @description Displays the AddEditDialog with empty fields for creating a
	 *              new task.
	 */
	public void ShowAddDialog() {
		dialogMode = DialogMode.ADD_DIALOG;
		clickedOK = false;
		this.setTitle(ADDEDITDIALOG_ADD_DIALOG_TITLE);
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
		dialogMode = DialogMode.EDIT_DIALOG;
		task = editTask;
		clickedOK = false;
		this.setTitle(ADDEDITDIALOG_EDIT_DIALOG_TITLE);
		txtTitle.setText(editTask.getTitle());
		txtDueDateYear.setText(Integer
				.toString(editTask.getDueDate().getYear() + 1900));
		txtDueDateMonth.setText(Integer.toString(editTask.getDueDate()
				.getMonth() + 1));
		txtDueDateDay
				.setText(Integer.toString(editTask.getDueDate().getDate()));
		txtDueDateHour.setText(Integer.toString(editTask.getDueDate()
				.getHours()));
		txtDueDateMinute.setText(Integer.toString(editTask.getDueDate()
				.getMinutes()));
		sliPriority.setValue(editTask.getPriority());
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
	public static void main(String[] args) {

		Task theTask = new Task(1, "first", new Date(2012 - 1900, 12 - 1, 1),
				1, "hello", 5, true);

		AddEditDialog addEdit = new AddEditDialog();
		addEdit.ShowAddDialog();
		if (addEdit.clickedOK()) {
			theTask = addEdit.getTask();
		}

		for (int i = 0; i < 2; i++) {
			addEdit.ShowEditDialog(theTask);
			if (addEdit.clickedOK()) {
				theTask = addEdit.getTask();
			}
		}
	}

}