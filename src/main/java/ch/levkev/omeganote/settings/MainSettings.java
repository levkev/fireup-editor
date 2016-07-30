package ch.levkev.omeganote.settings;

import java.io.File;
import java.util.prefs.Preferences;

/**
 * this class holds all other settings classes and provide some general settings
 * 
 * @author kreemer
 * @version 1.0
 */
public class MainSettings {
	
	private FormattingSettings formattingSettings;
	private SpellingSettings spellingSettings;
	private AutocorrectSettings autocorrectSettings;
	public static final String HOME_DIRECTORY_KEY = "homeDir";
	private Preferences prefs;
	
	public MainSettings(FormattingSettings formatting, SpellingSettings spelling, AutocorrectSettings autoCorrect) {
		this.formattingSettings = formatting;
		this.spellingSettings = spelling;
		this.autocorrectSettings = autoCorrect;
		prefs = Preferences.userRoot().node(MainSettings.class.getName());
	}

	public AutocorrectSettings getAutocorrectSettings() {
		return this.autocorrectSettings;
	}

	public FormattingSettings getFormattingSettings() {
		return this.formattingSettings;
	}
	
	public SpellingSettings getSpellingSettings() {
		return this.spellingSettings;
	}
	
	public File getHomeDirectory() {
		return new File(prefs.get(HOME_DIRECTORY_KEY, System.getProperty("user.home")));
	}
	
	public void setHomeDirectory(String value) {
		prefs.put(HOME_DIRECTORY_KEY, value);
	}
}
