package se.uu.it.todomanger.ui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import se.uu.it.todomanger.controller.LanguageManager;

/**
 * Shows a dialog describing the program.
 * @author ???
 * @author bjorn
 */
public class AboutUsDialog extends JDialog
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
