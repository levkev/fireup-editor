package modelling.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import modelling.Notebook;

public class NotebookTest {
	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();

	@Test
	public void addsSections() throws IOException {
		tmp.newFolder("Sections");
		tmp.newFile("Sections/section1");
		tmp.newFile("Sections/section2");
		Notebook notebook = new Notebook(tmp.getRoot());
		assertEquals(2, notebook.getSections().size());
	}

}
