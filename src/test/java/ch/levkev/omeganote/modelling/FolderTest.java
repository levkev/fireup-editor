package ch.levkev.omeganote.modelling;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ch.levkev.omeganote.modelling.Folder;

public class FolderTest {
	

	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();
	
	@Before
	public void setUp() throws IOException {
		this.tmp.newFolder("folder1");
		this.tmp.newFile("folder1/.nb");
		this.tmp.newFolder("folder2");
		this.tmp.newFolder("folder3");
		this.tmp.newFile("folder3/.nb");
	}
	
	@Test
	public void hasAccessToFileObject() {
		File file = new File(this.tmp.getRoot() + "/folder1");
		Folder folder = new Folder(file);
		
		assertEquals(file, folder.getFile());
	}
	
	@Test
	public void recognizeNotebookFolders() {
		File file = new File(this.tmp.getRoot() + "/folder1");
		Folder folder = new Folder(file);
		
		assertTrue(folder.isNotebook());
	}	
	
	@Test
	public void recognizeNormalFolder() {
		File file = new File(this.tmp.getRoot() + "/folder2");
		Folder folder = new Folder(file);
		
		assertFalse(folder.isNotebook());
	}

	@Test
	public void notebookFolderIsGreaterThanNormalFolder() {
		File file1 = new File(this.tmp.getRoot() + "/folder1");
		File file2 = new File(this.tmp.getRoot() + "/folder2");
		Folder folder1 = new Folder(file1);
		Folder folder2 = new Folder(file2);
		
		assertEquals(-1, folder1.compareTo(folder2));
		assertEquals(1, folder2.compareTo(folder1));
	}
	
	@Test
	public void notebookFolderWillBeSortedAlphanumericallyNameIsGreaterThanNormalFolder() {
		File file1 = new File(this.tmp.getRoot() + "/folder1");
		File file2 = new File(this.tmp.getRoot() + "/folder3");
		Folder folder1 = new Folder(file1);
		Folder folder2 = new Folder(file2);
		
		assertEquals(-1, folder1.compareTo(folder2));
		assertEquals(1, folder2.compareTo(folder1));
	}
	
	@Test
	public void twoNotebookFolderWillBeHandledEqually() {
		File file1 = new File(this.tmp.getRoot() + "/folder1");
		File file2 = new File(this.tmp.getRoot() + "/folder1");
		Folder folder1 = new Folder(file1);
		Folder folder2 = new Folder(file2);
		
		assertEquals(0, folder1.compareTo(folder2));
		assertEquals(0, folder2.compareTo(folder1));
	}
	
	@Test
	public void folderWillReturnRightToString() {
		File file1 = new File(this.tmp.getRoot() + "/folder1");
		Folder folder1 = new Folder(file1);
		
		assertEquals("folder1", folder1.toString());
	}
}
