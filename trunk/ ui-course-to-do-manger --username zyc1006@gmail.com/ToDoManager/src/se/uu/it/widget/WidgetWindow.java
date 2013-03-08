package se.uu.it.widget;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import se.uu.it.todomanger.controller.LanguageManager;
import se.uu.it.todomanger.ui.MainShowPanel;
import se.uu.it.todomanger.ui.MainWindow;
import se.uu.it.todomanger.ui.ToDoManagerMenuBar;
import se.uu.it.todomanger.ui.ToDoManagerToolBar;

public class WidgetWindow extends JFrame
{
	private static final int width  = 300;
	private static final int height = 500;
	
	/**
	 * Widget window's constructor, it initializes the whole thing
	 * 
	 * @param x the x-position to be used
	 * @param y the y-position to be used
	 */
	public WidgetWindow(int x, int y)
	{
		init(x, y);
	}
	
	/**
	 * Creates and positions the Widget appropriately.
	 * 
	 * @param x the x-location of the main application
	 * @param y the y-location of the main application
	 */
	private void init(int x, int y)
	{
		this.setLocation(x, y);
		this.setSize(width, height);
		this.setTitle(LanguageManager.getString("WidgetWindow_Title"));
		this.setContentPane(createPanel());
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Add a special window listener to make sure no more than one instance of
		// the widget is being displayed.
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				ToDoManagerMenuBar.widgetClosed();
				getInstance().setVisible(false);
			}
		});
	}
	
	/**
	 * Gets the instance of the WidgetWindow
	 */
	private WidgetWindow getInstance()
	{
		return this;
	}
	
	public static int getWidgetWidth()
	{
		return width;
	}
	
	// Same as in MainWindow
	private JPanel createPanel() {
		JPanel panel   = new JPanel(new BorderLayout());
		JPanel content = new JPanel();
		
		panel.add(content);
		return panel;
	}
}