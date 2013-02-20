package se.uu.it.todomanger.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import se.uu.it.todomanger.controller.LanguageManager;

public class AboutUsDialog extends JDialog
{
	private ResourceBundle resLocale;
	
	public AboutUsDialog()
	{
		/*try {
			resLocale = LanguageManager.getDefaultResourceBundle();
		} catch (MissingResourceException mre) {
		    System.err.println("res/locale/ToDoManager.properties not found");
		    System.exit(1);
		}*/
		StringBuilder sb = new StringBuilder();
		sb.append("This program is our work for User Interface Programming. \n\n\n\n\n\n\n");
		for(int i = 0; i < 50 ; i++)
		{
			sb.append("  ");
		}
		sb.append("version 1.0 \n");
		for(int i = 0; i < 50 ; i++)
		{
			sb.append("  ");
		}
		sb.append("Group 12 \n\n");
		JOptionPane.showMessageDialog(null, sb.toString(), "about us", 1);
	}
}
