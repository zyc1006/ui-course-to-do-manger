package se.uu.it.todomanger.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
	
	
	public static final String ENGLISH = "locale.ToDoManager";
	public static final String GERMAN = "locale.ToDoManager_de";
	public static final String SWEDISH = "locale.ToDoManager_sv_SE";
	
	private static LanguageManager lm;	
	private static ResourceBundle rb;
	
	private LanguageManager(String baseName) {
		rb = ResourceBundle.getBundle(baseName,Locale.getDefault());
	}
	
	public static ResourceBundle resetResourceBundle(String baseName){
		lm = new LanguageManager(baseName);
		return rb;
	}
	
	public static ResourceBundle getDefaultResourceBundle(){
		if(rb == null){
			lm = new LanguageManager(LanguageManager.ENGLISH);
		}	
		return rb;
	}
	
}
