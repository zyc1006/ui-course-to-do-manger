package se.uu.it.todomanger.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

import se.uu.it.todomanger.controller.CategoryManager;
import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.controller.TaskManager;

/**
 * A task table model which will be linked with an instance of JTable. Task
 * table model defines the column names of a JTable Task table model can add and
 * remove rows from JTable
 * 
 * @author Yucheng
 * 
 */
public class TaskTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	/**
	 * Column name of the task table
	 */
	private static String[] columnName = { "0", "1", "2", "3", "4" };

	/**
	 * Create the table model and set headers to internationalized names.
	 * 
	 */
	public TaskTableModel() {

		// User the superclasses constructor
		super(columnName, 0);

		// Load the resource bundle for the current locale.
		String[] localColumnTitles = { "id",
				LanguageManager.getString("TaskTable_Column_Title_Label"),
				LanguageManager.getString("TaskTable_Column_Category_Label"),
				LanguageManager.getString("TaskTable_Column_Priority_Label"),
				LanguageManager.getString("TaskTable_Column_DueDate_Label"),
				LanguageManager.getString("TaskTable_Column_Completed_Label") };

		// Set the column header names
		this.setColumnIdentifiers(localColumnTitles);

	}
	
	/**
	 * reset the column text of the a task table
	 */
	public void setTaskTableText() {
		// Create the list of column header names
		String[] localColumnTitles = { "id",
				LanguageManager.getString("TaskTable_Column_Title_Label"),
				LanguageManager.getString("TaskTable_Column_Category_Label"),
				LanguageManager.getString("TaskTable_Column_Priority_Label"),
				LanguageManager.getString("TaskTable_Column_DueDate_Label"),
				LanguageManager.getString("TaskTable_Column_Completed_Label")};

		// Set the column header names
		this.setColumnIdentifiers(localColumnTitles);
	}

	/**
	 * Add a task into the table
	 * 
	 * @param task
	 */
	private void addTaskAsRow(Task task) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		CategoryManager cm = CategoryManager.getInstance();
		Object[] row = { task.getId(), task.getTitle(),
				"", task.getPriority(),
				sdf.format(task.getDueDate()) };


		//Need to store the categories in the xml-file too.
		//After that get the categories with cm.getCategories().get(task.getCategory()).getCategoryTitle()
		
		super.addRow(row);
	}

	/**
	 * sort the tasks by comparator
	 * @param task list a array list of task to be sorted
	 * @param comparator for sorting
	 */
	public void displayAllTasksByOrder(ArrayList<Task> taskList,
			Comparator<Task> comparator) {

		setRowCount(0);
		Collections.sort(taskList, comparator);

		for (int i = 0; i < taskList.size(); i++) {
			addTaskAsRow(taskList.get(i));
		}

	}

	/**
	 * the cell in task table is not editable
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
