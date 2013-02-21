package se.uu.it.todomanger.ui;

import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import se.uu.it.todomanger.controller.LanguageManager;

public class HelpDialog extends JDialog{
	
	public HelpDialog()
	{
		/*try {
			resLocale = LanguageManager.getDefaultResourceBundle();
		} catch (MissingResourceException mre) {
		    System.err.println("res/locale/ToDoManager.properties not found");
		    System.exit(1);
		}*/
		StringBuilder sb = new StringBuilder();
		sb.append(LanguageManager.getString("HelpDialog_Line1") + "\n");
		sb.append(LanguageManager.getString("HelpDialog_Line2") + "\n\n");
		sb.append(LanguageManager.getString("HelpDialog_Line3") + "\n");
		sb.append("Bjorn Forsberg.       Email:bjorn.forsberg.5755@student.uu.se\n");
		sb.append("Mattias Andersson.    Email:mattias.andersson.3705@student.uu.se\n");
		sb.append("Sara Frisk.           Email:sara.frisk.2154@student.uu.se\n");
		sb.append("shiyu zhou.           Email:shiyu.zhou.4704@student.uu.se\n");
		sb.append("Yucheng Zhou.         Email:yucheng.zhou.3489@student.uu.se\n");
		JOptionPane.showMessageDialog(null, sb.toString(), "Help", 1);
	}
}
