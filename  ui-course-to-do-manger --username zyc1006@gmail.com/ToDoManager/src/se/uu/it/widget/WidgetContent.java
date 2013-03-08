package se.uu.it.widget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.model.Task;
import se.uu.it.todomanger.ui.TaskListRenderer;

/**
 * The content of the widget
 * 
 * @author Matthew
 *
 */
public class WidgetContent
{
	
//	private DefaultTableModel model;
	private JTable table;
	private JList list;
	private DefaultListModel listModel;
	
	/**
	 * Initialize the content
	 * 
	 * @return what's to be displayed in the widget
	 */
	public JScrollPane initContent()
	{
		// Set the table model
	//	model = new DefaultTableModel();
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setCellRenderer(new TaskListRenderer());
		
		ArrayList<Task> taskList = DataSource.taskArrayList;
		SimpleDateFormat sdf  = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
		for(int i = 0; i < taskList.size(); i++)
		{
			String dueDate = sdf.format(taskList.get(i).getDueDate());
			dueDate = dueDate.substring(0, 10);			
			String today = sdf2.format(new Date());			
			try
			{
				sdf2.parse(dueDate);
				
				if(dueDate.equals(today))
				{
					listModel.addElement(taskList.get(i).getTitle());
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	//	model.addColumn(LanguageManager.getString("TaskTable_Column_Title_Label"));
	//	model.addColumn(LanguageManager.getString("TaskTable_Column_DueDate_Label"));
		
	//	table = new JTable(100, 2);
	//	table.setModel(model);
		
		
		
		// Simple date formats for comparison
		
		
		// Fill the table
//		for(int i = 0; i < taskList.size(); i++)
//		{
//			String dueDate = sdf.format(taskList.get(i).getDueDate());
//			dueDate = dueDate.substring(0, 10);
//			
//			String today = sdf2.format(new Date());
//			
//			try
//			{
//				sdf2.parse(dueDate);
//				
//				if(dueDate.equals(today))
//				{
//					model.addRow(new Object[] {taskList.get(i).getTitle(), dueDate});
//				}
//			}
//			catch(Exception e)
//			{}
//		}
		
		// Set content and bounds of the JScrollPane
		JScrollPane content = new JScrollPane(list);
		content.setBounds(0, 0, WidgetWindow.getWidgetWidth(), WidgetWindow.getWidgetHeight());
		
		return content;
	}
	
	/**
	 * Updates the language of the table.
	 */
	public void updateLanguage() {
		table.getColumnModel().getColumn(0).setHeaderValue(LanguageManager.getString("TaskTable_Column_Title_Label"));
		table.getColumnModel().getColumn(1).setHeaderValue(LanguageManager.getString("TaskTable_Column_DueDate_Label"));
		table.updateUI();
	}
}