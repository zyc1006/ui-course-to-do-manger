package se.uu.it.todomanger.controller;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * LanguageManager
 * 
 * @author bjorn Handles the language of the program.
 */
public class LanguageManager {

	// The name of the bundle to load.
	private static final String baseName = "locale.ToDoManager";

	// Keeps track if the locale for the language manager has been set, since we
	// only set the default language the first time the constructor is called.
	private static Boolean localeSet = false;

	// Public references to locales that the program accepts.
	public static final Locale ENGLISH = Locale.ENGLISH;
	public static final Locale GERMAN = Locale.GERMAN;
	public static final Locale SWEDISH = new Locale("swe");

	// The ResourceBundle we use for loading words/phrases.
	private static ResourceBundle rb;

	/**
	 * LanguageManager()
	 * 
	 * @description Creates a new LanguageManager. If this is the first
	 *              LanguageManager, the default locale of the system is used,
	 *              otherwise, the previous set locale is used.
	 */
	public LanguageManager() {
		if (!localeSet) {
			rb = ResourceBundle.getBundle(baseName, Locale.getDefault());
		}
	}

	/**
	 * setLocale
	 * @param loc - The locale to be used for the LanguageManager.
	 * @description Sets the locale to be used for the LanguageManager.
	 */
	public static void setLocale(Locale loc) {
		rb = ResourceBundle.getBundle(baseName, loc);
		localeSet = true;
	}

	/**
	 * getString()
	 * @param str - the string to load.
	 * @return Loads the language string mapped to str.
	 */
	public static String getString(String str) {
		return rb.getString(str);
	}
}
