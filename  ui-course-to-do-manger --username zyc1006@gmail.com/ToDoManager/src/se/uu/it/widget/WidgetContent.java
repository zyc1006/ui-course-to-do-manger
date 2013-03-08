package se.uu.it.widget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.dao.DataSource;
import se.uu.it.todomanger.model.Task;

/**
 * The content of the widget
 * 
 * @author Matthew
 *
 */
public class WidgetContent
{
	
	private DefaultTableModel model;
	private JTable table;
	
	/**
	 * Initialize the content
	 * 
	 * @return what's to be displayed in the widget
	 */
	public JScrollPane initContent()
	{
		// Set the table model
		model = new DefaultTableModel();
		model.addColumn(LanguageManager.getString("TaskTable_Column_Title_Label"));
		model.addColumn(LanguageManager.getString("TaskTable_Column_DueDate_Label"));
		
		table = new JTable(100, 2);
		table.setModel(model);
		
		ArrayList<Task> taskList = DataSource.taskArrayList;
		
		// Simple date formats for comparison
		SimpleDateFormat sdf  = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
		
		// Fill the table
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
					model.addRow(new Object[] {taskList.get(i).getTitle(), dueDate});
				}
			}
			catch(Exception e)
			{}
		}
		
		// Set content and bounds of the JScrollPane
		JScrollPane content = new JScrollPane(table);
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