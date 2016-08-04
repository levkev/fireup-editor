package ch.levkev.omeganote.modelling;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ch.levkev.omeganote.modelling.FolderBrowser;
import ch.levkev.omeganote.modelling.interfaces.IFolder;


public class FolderBrowserTest {
	
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
	public void canListFiles() {
		FolderBrowser browser = new FolderBrowser(this.tmp.getRoot());
		
		assertEquals(3, browser.listFiles().size());
	}
	
	@Test
	public void filesAreOrderedTheRightWay() {
		FolderBrowser browser = new FolderBrowser(this.tmp.getRoot());
		
		ArrayList<IFolder> folders = browser.listFiles();
		assertEquals("folder1", folders.get(0).getFile().getName());
		assertEquals("folder3", folders.get(1).getFile().getName());
		assertEquals("folder2", folders.get(2).getFile().getName());
		
	}
	
}
