package se.uu.it.todomanger.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;

import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.dao.CategoryDataSource;
import se.uu.it.todomanger.dao.DataSource;
/**
 * A task table model which will be linked with an instance of JTable. Task
 * table model defines the column names of a JTable Task table model can add and
 * remove rows from JTable
 * 
 * @author Yucheng
 * 
 */
public class NewTaskTableModel extends AbstractTableModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//a task list contains all the task 
	private ArrayList<Task> taskList;
	//a list contains all the column titles
	private ArrayList<String> localColumnTitles;
	
	private int currentCategoryId = -1;
	
	/**
	 * set the current seleced category
	 * @param currentCategoryId
	 */
	public void setCurrentCategoryId(int currentCategoryId) {
		this.currentCategoryId = currentCategoryId;
	}


	/**
	 * Constructor
	 * @param taskList
	 */
	@SuppressWarnings("unchecked")
	public NewTaskTableModel(ArrayList<Task> taskList){
		this.taskList = (ArrayList<Task>) taskList.clone();
		this.setTaskTableText();
		
	}
	
	
	/**
	 * reset the column text of the a task table
	 */
	public void setTaskTableText() {
		// Create the list of column header names
		this.localColumnTitles = new ArrayList<String>();
		this.localColumnTitles.add("id");
		this.localColumnTitles.add(LanguageManager.getString("TaskTable_Column_Title_Label"));
		this.localColumnTitles.add(LanguageManager.getString("TaskTable_Column_Category_Label"));
		this.localColumnTitles.add(LanguageManager.getString("TaskTable_Column_Priority_Label"));
		this.localColumnTitles.add(LanguageManager.getString("TaskTable_Column_DueDate_Label"));
		this.localColumnTitles.add(LanguageManager.getString("TaskTable_Column_Completed_Label"));
		this.localColumnTitles.add("cid");
		fireTableStructureChanged();
	}
	
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:return Integer.class;
		case 1:return String.class;
		case 2:return String.class;
		case 3:return Integer.class;
		case 4:return String.class;
		case 5:
			return Boolean.class;
		case 6:return Integer.class;
		default:
			return String.class;
		
		}
	}

	
	@Override
	public String getColumnName(int column) {
		return localColumnTitles.get(column);
	}


	@Override
	public int getRowCount() {
		return taskList.size();
	}

	@Override
	public int getColumnCount() {
		return localColumnTitles.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Task task = taskList.get(rowIndex);
		switch (columnIndex) {
		case 0:return task.getId();
		case 1:return task.getTitle();
		case 2:return ((Category)CategoryDataSource.categoryHashMap.get(task.getCategory())).getCategoryTitle();
		case 3:return task.getPriority();
		case 4:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String date = sdf.format(task.getDueDate());
			return date;
		case 5:return task.isCompleted();
		case 6:return task.getCategory();
		default:
			return null;
		}
	}


	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Task task = taskList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			task.setId((Integer)aValue);
			break;
		case 1:
			task.setTitle((String)aValue);
			break;
//		case 2:
//			task.setCategory((Integer)aValue);
//			break;
		case 3:
			task.setPriority((Integer)aValue);
			break;
		case 4:
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date;
			try {
				date = sdf.parse((String)aValue);
				task.setDueDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case 5:
			task.setCompleted((Boolean)aValue);
			break;
		case 6:
			task.setCategory((Integer)aValue);
			break;
		default:
			break;		
		}
		fireTableCellUpdated(rowIndex, columnIndex);
		
	}


	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 5:
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * get the task according to the row number
	 * @param row
	 * @return
	 */
	public Task getTask(int row){
		return taskList.get(row);
	}
	
	/**
	 * add a task into the table
	 * @param task
	 */
	public void addTask(Task task){
		insertTask(taskList.size(), task);
	}
	/**
	 * insert a task task in the specified row
	 * @param row
	 * @param task
	 */
	public void insertTask(int row, Task task){
	//	taskList.add(row, task);
		DataSource.taskArrayList.add(task);
		//fireTableRowsInserted(row, row);
		showTaskInCategory();
	}
	
	/**
	 * remove the task from the specified row
	 * @param row
	 */
	public void removeTask(int row){
//		taskList.remove(row);
		DataSource.deleteTask(getTask(row));
		showTaskInCategory();
	//	fireTableRowsDeleted(row, row);
	}
	
	/**
	 * update the task at the specified row
	 * @param row
	 * @param task
	 */
	public void editTask(int row, Task task){
//		removeTask(row);
//		insertTask(row, task);
//		fireTableRowsUpdated(row, row);
		DataSource.updateTask(task);
		showTaskInCategory();
	}
	
	public void showTaskInCategory(){
		if(currentCategoryId == -1){
			taskList = (ArrayList<Task>) DataSource.taskArrayList.clone();
		}
		else{
			taskList = new ArrayList<Task>();
			for(Task task : DataSource.taskArrayList){
				if (task.getCategory() == currentCategoryId){
					taskList.add(task);
				}
			}
		}
		fireTableDataChanged();
	}
	
	
//	/**
//	 * reset the column text of the a task table
//	 */
//	public void setTaskTableText() {
//		// Create the list of column header names
//		String[] localColumnTitles = { "id",
//				LanguageManager.getString("TaskTable_Column_Title_Label"),
//				LanguageManager.getString("TaskTable_Column_Category_Label"),
//				LanguageManager.getString("TaskTable_Column_Priority_Label"),
//				LanguageManager.getString("TaskTable_Column_DueDate_Label") };
//
//		// Set the column header names
//		this.setColumnIdentifiers(localColumnTitles);
//	}
//	
	private HashMap<Integer,Task> toHashMap(ArrayList<Task> taskList){
		HashMap<Integer,Task> taskHashMap = new HashMap<Integer, Task>();
		for(Task task: taskList){
			taskHashMap.put(task.getId(), task);
		}
		return taskHashMap;
	}
	
	/**
	 * private {@link ArrayList} toArrayList() <br>
	 * Convert hashmap to arraylist
	 * @return an arraylist contains all tasks to be displayed
	 */
	private ArrayList<Task> toArrayList(HashMap<Integer, Task> taskHashMap) {
		ArrayList<Task> taskArrayList = new ArrayList<Task>();
		if (taskHashMap != null) {
			Iterator<Entry<Integer, Task>> it = taskHashMap.entrySet()
					.iterator();
			while (it.hasNext()) {
				Entry<Integer, Task> entry = (Entry<Integer, Task>) it.next();
				taskArrayList.add(entry.getValue());
			}
		}
		return taskArrayList;
	}

}
