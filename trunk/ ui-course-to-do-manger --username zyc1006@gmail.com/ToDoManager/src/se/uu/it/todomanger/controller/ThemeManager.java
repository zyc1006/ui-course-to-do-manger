package se.uu.it.todomanger.controller;

import javax.swing.UIManager;

/**
 * Handles the theme of the program.
 * 
 * @author Mattias
 */
public class ThemeManager
{
	// Keeps track of the theme currently in use
	private static int theme;
	
	/**
	 * Sets the theme to be used for the ThemeManager
	 * 
	 * @param theme
	 * 				- the theme integer to be used by the ThemeManager
	 */
	public static void setTheme(int theme)
	{
		// Instantiate the class name (theme) to be used
		String className = "";
		
		// System default theme
		if(theme==1)
		{
			className = UIManager.getCrossPlatformLookAndFeelClassName();
			ThemeManager.theme = theme;
		}
		// Motif theme
		else if(theme==2)
		{
			className = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			ThemeManager.theme = theme;
		}
		try
		{
			UIManager.setLookAndFeel(className);
			
			// Set the theme
			ThemeManager.theme = theme;
		}
		catch(Exception error)
		{
			System.out.println("Error changing theme");
		}
	}
	
	/**
	 * Returns the current theme in use
	 * 
	 * @return the current theme in use by the ThemeManager
	 */
	public static int getTheme()
	{
		return theme;
	}
}