package se.uu.it.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import se.uu.it.todomanger.controller.LanguageManager;

public class WidgetContent
{
	public JScrollPane initContent()
	{
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn(LanguageManager.getString("TaskTable_Column_Title_Label"));
		model.addColumn(LanguageManager.getString("TaskTable_Column_DueDate_Label"));
		
		JTable table = new JTable(100, 2);
		table.setModel(model);
		
		
		//model.addRow(new Object[] {"Titel here", "Due date here"});
		
		JScrollPane content = new JScrollPane(table);
		content.setBounds(0, 0, WidgetWindow.getWidgetWidth(), WidgetWindow.getWidgetHeight());
		
		return content;
	}
}