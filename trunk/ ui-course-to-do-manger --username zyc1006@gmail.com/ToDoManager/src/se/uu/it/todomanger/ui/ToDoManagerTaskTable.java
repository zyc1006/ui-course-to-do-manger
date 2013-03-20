
package se.uu.it.todomanger.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.controller.ReminderTimerManager;
import se.uu.it.todomanger.controller.TaskManager;
import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.model.NewTaskTableModel;
import se.uu.it.todomanger.model.Task;

/**
 * A singleton of task table for ToDoManager
 * @author Yucheng
 *
 */
public class ToDoManagerTaskTable extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static ToDoManagerTaskTable taskTable = null;
	private static NewTaskTableModel taskTableModel = null;
	
	
	private ToDoManagerTaskTable (TableModel tableModel){
		super(tableModel);
	}
	
	/**
	 * returns the singleton of task table
	 * @return
	 */
	public static ToDoManagerTaskTable getInstance(){
		if(null == taskTable){
			taskTableModel = new NewTaskTableModel(DataSource.taskArrayList);
			taskTable = new ToDoManagerTaskTable(taskTableModel);
			taskTable.hideColumn(0);//hide id column
			taskTable.hideColumn(6);//hide category id column
			taskTable.setColumnStyle(2, SwingConstants.CENTER);//category,center
			taskTable.setColumnStyle(3, SwingConstants.CENTER);//priority, center
			taskTable.setColumnStyle(4, SwingConstants.CENTER);//duedate, center
			initTaskTable();
		}
		return taskTable;
	}
	
	//if we need more initialization on task table, add it here
	private static void initTaskTable(){
		
		taskTable.setRowSelectionAllowed(true);
		taskTable.setColumnSelectionAllowed(false);
		//Single selection only
		taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskTable.setAutoCreateRowSorter(true);
		taskTable.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseReleased(MouseEvent e) {
				
				// Get the row selected
				int r = taskTable.rowAtPoint(e.getPoint());
				if(r >= 0 && r < taskTable.getRowCount())
				{
					taskTable.setRowSelectionInterval(r, r);
				}
				else
				{
					taskTable.clearSelection();
				}
				
				// Right click
				if(SwingUtilities.isRightMouseButton(e))
				{
					final int selectedRow = taskTable.getSelectedRow();
					
					JPopupMenu contextMenu = new JPopupMenu();
					
					// Add items
					JMenuItem editItem   = new JMenuItem(LanguageManager.getString("MenuBar_Edit_Task_Option"), KeyEvent.VK_A);
					JMenuItem deleteItem = new JMenuItem(LanguageManager.getString("MenuBar_Delete_Task_Option"), KeyEvent.VK_B);
					contextMenu.add(editItem);
					contextMenu.add(deleteItem);
					
					// Display the context menu
					contextMenu.show(e.getComponent(), e.getX(), e.getY());
					
					// Add action listeners
					// Edit task
					editItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent event) {
							int modelRow = taskTable.getRowSorter().convertRowIndexToModel(selectedRow);
							Task task = ((NewTaskTableModel)taskTable.getModel()).getTask(modelRow);
							
							AddEditDialog addEditDialog = new AddEditDialog();
							addEditDialog.ShowEditDialog(task);
							
							if(addEditDialog.clickedOK())
							{
								// Add a task here
								TaskManager tm = TaskManager.getInstance();
								tm.editTask(modelRow, addEditDialog.getTask());
								// tm.displayTaskByDueDateAsc();
								// add Timer  to monitor
								ReminderTimerManager.getInstance().TimeMonitorTask(addEditDialog.getTask());
							}
							else
							{
								System.out.println("cancel");
							}
						}
					});
					
					// Delete task
					deleteItem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent event) {
							int modelRow = taskTable.getRowSorter().convertRowIndexToModel(selectedRow);
							TaskManager tm = TaskManager.getInstance();
							tm.deleteTask(modelRow);
						}
					});
				}
				// Left single click
				else if (e.getClickCount() == 1) {
					int selectedRow = taskTable.getSelectedRow();
					if (selectedRow >= 0) {
						int modelRow = taskTable.getRowSorter().convertRowIndexToModel(selectedRow);
						Task task = ((NewTaskTableModel)taskTable.getModel()).getTask(modelRow);
						ReminderTimerManager.getInstance().resetTaskTimer(task);
					}
				}
				// double click
				else if (e.getClickCount() == 2) {
					int selectedRow = taskTable.getSelectedRow();
					if (selectedRow >= 0) {
					
						int modelRow = taskTable.getRowSorter().convertRowIndexToModel(selectedRow);
						Task task = ((NewTaskTableModel)taskTable.getModel()).getTask(modelRow);
						
						AddEditDialog addEditDialog = new AddEditDialog();
						addEditDialog.ShowEditDialog(task);
						if (addEditDialog.clickedOK()) {
							// Add a task here
							TaskManager tm = TaskManager.getInstance();
							tm.editTask(modelRow, addEditDialog.getTask());
					//		tm.displayTaskByDueDateAsc();
							//add Timer  to monitor
							ReminderTimerManager.getInstance().TimeMonitorTask(addEditDialog.getTask());
						} else {
							System.out.println("cancel");
						}
					}
				}
				super.mouseClicked(e);
			}
			
		});
	}
	
//	/**
//	 * public void displayAllTasksByOrder ({@link ArrayList} taskList, {@link Comparator} comparator)<br>
//	 * Display all tasks by specified comparator
//	 * 
//	 * @param taskList 
//	 * A task list includes the tasks to be displayed
//	 * @param comparator 
//	 * In what order the tasks will be displayed
//	 * @see TaskManager
//	 */
////	public void displayAllTasksByOrder(ArrayList<Task> taskList, Comparator<Task> comparator){
////
////		taskTableModel.displayAllTasksByOrder(taskList, comparator);
////	}
	
	
	//hide column by column index
	private void hideColumn(int index) {
		TableColumn tc = this.getColumnModel().getColumn(index);
		tc.setMaxWidth(0);
		tc.setPreferredWidth(0);
		tc.setWidth(0);
		tc.setMinWidth(0);
		this.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(0);
		this.getTableHeader().getColumnModel().getColumn(index).setMinWidth(0);
	}
	
	//set the alignment for priority column
	private void setColumnStyle(int column, int alignment){
		TableColumn tablecolumn = taskTable.getColumnModel().getColumn(column);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(alignment);
		tablecolumn.setCellRenderer(render);
	}
	
	/**
	 * set text of the task table
	 */
	public void setTaskTableText(){
		((NewTaskTableModel)taskTable.getModel()).setTaskTableText();
		taskTable.hideColumn(0);
		taskTable.hideColumn(6);
	}
	
	
	
}
