package ch.levkev.omeganote.settings;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class MainSettingsTest {

	private MainSettings mainSettings;
	private AutocorrectSettings autoCorrectSettings;
	private FormattingSettings formattingSettings;
	private SpellingSettings spellingSettings;
	private String homeDirectory = "/tmp/";
	private Preferences prefs;
	
	@Before
	public void setUp() throws BackingStoreException {
		prefs = Preferences.userRoot().node(MainSettings.class.getName());
		prefs.clear();
		this.autoCorrectSettings = mock(AutocorrectSettings.class);
		this.formattingSettings = mock(FormattingSettings.class);
		this.spellingSettings = mock(SpellingSettings.class);
		this.mainSettings = new MainSettings(this.formattingSettings, this.spellingSettings, this.autoCorrectSettings);
	}
	
	@Test
	public void canRetrieveAutoCorrectSettings() {
		assertEquals(this.autoCorrectSettings, this.mainSettings.getAutocorrectSettings());
	}
	
	@Test
	public void canRetrieveFormattingSettings() {
		assertEquals(this.formattingSettings, this.mainSettings.getFormattingSettings());
	}
	
	@Test
	public void canRetrieveSpellingSettings() {
		assertEquals(this.spellingSettings, this.mainSettings.getSpellingSettings());
	}
	
	@Test
	public void homeDirectoryIsInitiallyUserDirectory() {
		File file = new File(System.getProperty("user.home"));
		
		assertEquals(file.getPath(), this.mainSettings.getHomeDirectory().getPath());
	}
	
	@Test
	public void homeDirectoryIsAnotherWhenReadFromProperties() {
		this.prefs.put(MainSettings.HOME_DIRECTORY_KEY, "/tmp");
		assertThat(this.mainSettings.getHomeDirectory().getPath(), Matchers.either(Matchers.is("/tmp")).or(Matchers.is("\\tmp")));
	}

	@Test
	public void canSaveHomeDirectoryKey() {
		this.mainSettings.setHomeDirectory("/tmp");
		assertThat(this.mainSettings.getHomeDirectory().getPath(), Matchers.either(Matchers.is("/tmp")).or(Matchers.is("\\tmp")));
	}
	@After
	public void tearDown() throws BackingStoreException {
		this.prefs.removeNode();
	}
}
