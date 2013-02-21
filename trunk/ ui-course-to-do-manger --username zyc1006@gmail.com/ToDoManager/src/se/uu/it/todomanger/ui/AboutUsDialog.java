package se.uu.it.todomanger.ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import se.uu.it.todomanger.controller.LanguageManager;

/**
 * AboutUsDialog
 * @author ???
 * @author bjorn
 * @description Shows a dialog describing the program.
 *
 */
public class AboutUsDialog extends JDialog
{
	
	public AboutUsDialog()
	{
		
		StringBuilder sb = new StringBuilder();
		sb.append(LanguageManager.getString("AboutUsDialog_Text"));
		sb.append("\n\n\n\n\n\n\n");
		for(int i = 0; i < 50 ; i++)
		{
			sb.append("  ");
		}
		sb.append(LanguageManager.getString("AboutUsDialog_Version") + " 1.0 \n");
		for(int i = 0; i < 50 ; i++)
		{
			sb.append("  ");
		}
		sb.append(LanguageManager.getString("AboutUsDialog_Group") + " 12 \n\n");
		JOptionPane.showMessageDialog(null, sb.toString(), LanguageManager.getString("AboutUsDialog_Text"), 1);
	}
}
