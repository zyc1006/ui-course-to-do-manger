package se.uu.it.widget;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import se.uu.it.todomanger.controller.LanguageManager;

/**
 * The widget's class
 * 
 * @author Mattias
 *
 */
public class WidgetWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	// The widget's height and width
	private static final int width  = 300;
	private static final int height = 500;
	
	private WidgetContent widgetContent;
	
	// Widget data
	// The instance of the widgetWindow
	public static WidgetWindow widgetWindow;
	// Keeps track of whether or not the widget has been opened
	private static boolean widgetOpen = false;
	
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
				WidgetWindow.setWidgetOpen(false);
				getInstance().setVisible(false);
			}
		});
	}
	
	// Gets the instance of the WidgetWindow
	private WidgetWindow getInstance()
	{
		return this;
	}
	
	/**
	 * Refreshes the widget's content
	 */
	public void refreshWidget()
	{
		this.setTitle(LanguageManager.getString("WidgetWindow_Title"));
		widgetContent.updateLanguage();
	}
	
	/**
	 * Gets the widget width
	 * 
	 * @return width
	 */
	public static int getWidgetWidth()
	{
		return width;
	}
	
	/**
	 * Gets the widget height
	 * 
	 * @return height
	 */
	public static int getWidgetHeight()
	{
		return height;
	}
	
	/**
	 * Sets the widgetOpen to false
	 */
	public static void setWidgetOpen(boolean val)
	{
		widgetOpen = val;
	}
	
	/**
	 * Checks whether or not the widget is open
	 */
	public static boolean widgetOpen()
	{
		return widgetOpen;
	}
	
	// Creates what's in the Widget
	private JPanel createPanel() {
		JPanel panel   = new JPanel(new BorderLayout());
		widgetContent = new WidgetContent();
		
		panel.add(widgetContent.initContent());
		
		return panel;
	}
}