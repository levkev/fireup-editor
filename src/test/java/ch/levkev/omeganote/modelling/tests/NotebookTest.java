package ch.levkev.omeganote.modelling.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ch.levkev.omeganote.modelling.Notebook;

public class NotebookTest {
	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();

	@Test
	public void addsSections() throws IOException {
		tmp.newFolder(Notebook.DIR_CONTAINING_SECTIONS);
		tmp.newFile(Notebook.DIR_CONTAINING_SECTIONS + "/section1.md");
		tmp.newFile(Notebook.DIR_CONTAINING_SECTIONS + "/section2.md");
		
		Notebook notebook = new Notebook(tmp.getRoot().getPath());
		assertEquals(2, notebook.getSections().size());
	}

	@Test
	public void canHandleEmptyNotebook() throws IOException {
		Notebook notebook = new Notebook(tmp.getRoot().getPath());
		assertEquals(0, notebook.getSections().size());
	}
	
	@Test
	public void ignoresSectionsThatDoNotHaveTheExpectedSuffix() throws IOException {
		tmp.newFolder(Notebook.DIR_CONTAINING_SECTIONS);
		tmp.newFile(Notebook.DIR_CONTAINING_SECTIONS + "/noSection");
		Notebook notebook = new Notebook(tmp.getRoot().getPath());
		assertEquals(0, notebook.getSections().size());
	}
	
	@Test
	public void canCallConstructorWithFileObject() {
		Notebook notebook = new Notebook(tmp.getRoot());
		
		assertEquals(tmp.getRoot().getPath(), notebook.getPath());
		
	}
	
	@Test
	public void newNotebookWillGenerateDirectory() {
		new Notebook(tmp.getRoot());
		
		assertTrue(tmp.getRoot().toPath().resolve(Notebook.DIR_CONTAINING_SECTIONS).toFile().exists());
	}
	
	@Test
	public void newNotebookWillGenerateMetadataFile() {
		new Notebook(tmp.getRoot());
		
		assertTrue(tmp.getRoot().toPath().resolve(Notebook.FILE_CONTAINING_METADATA).toFile().exists());
	}
}
