package se.uu.it.todomanger.ui;

import java.awt.*;
import javax.swing.*;

/**
 * A simple listcellrenderer used for task list widget
 * @author Yucheng
 *
 */
public class TaskListRenderer extends JLabel implements ListCellRenderer {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String s = value.toString();
		setText(s);
		if (isSelected) {
			setBackground(new Color(43,97,238));
			
			setForeground(Color.white);
		}
		else{
			setBackground(new Color(123, 211, 244));
			setForeground(Color.white);
		}

		
		
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setOpaque(true);
		return this;
	}
}