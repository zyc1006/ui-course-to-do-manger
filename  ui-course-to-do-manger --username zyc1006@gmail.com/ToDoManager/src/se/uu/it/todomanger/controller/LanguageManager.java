package se.uu.it.todomanger.controller;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Handles the language of the program.
 * 
 * @author bjorn
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
	public static final Locale CHINESE = Locale.CHINESE;

	// The ResourceBundle we use for loading words/phrases.
	private static ResourceBundle rb;

	/**
	 * Creates a new LanguageManager. If this is the first LanguageManager, the
	 * default locale of the system is used, otherwise, the previous set locale
	 * is used.
	 * 
	 * @author bjorn
	 */
	public LanguageManager() {
		if (!localeSet) {
			rb = ResourceBundle.getBundle(baseName, Locale.getDefault());
			localeSet = true;
		}
	}

	/**
	 * Sets the locale to be used for the LanguageManager.
	 * 
	 * @param loc
	 *            - The locale to be used for the LanguageManager.
	 * @author bjorn
	 */
	public static void setLocale(Locale loc) {
		rb = ResourceBundle.getBundle(baseName, loc);
		localeSet = true;
	}

	/**
	 * Get the string mapped to str in the current locale.
	 * 
	 * @param str
	 *            - the string to load.
	 * @return Returns the language string mapped to str.
	 * @author bjorn
	 * @Precondition The locale of the LanguageManager has been set using the
	 *               setLocale(loc) method.
	 */
	public static String getString(String str) {
		if (localeSet) {
			return rb.getString(str);
		} else {
			throw (new IllegalStateException(
					"The LanguageManager Locale has not been set using the setLocale(loc) method."));
		}
	}
}
