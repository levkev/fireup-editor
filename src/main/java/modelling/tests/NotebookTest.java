package modelling.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import modelling.Notebook;

public class NotebookTest {

	@Test
	public void addsSections() throws IOException {
		File sectionFolder = new File("tmp/Sections");
		sectionFolder.mkdirs();
		File section1 = new File("tmp/Sections/section1");
		File section2 = new File("tmp/Sections/section2");
		
		FileWriter writer1 = new FileWriter(section1);
		writer1.write("a");
		writer1.close();
		section1.createNewFile();
		
		FileWriter writer2 = new FileWriter(section2);
		writer2.write("b");
		writer2.close();
		section2.createNewFile();
		
		Notebook notebook = new Notebook("tmp");
		assertEquals(2, notebook.getSections().size());
		
		section1.delete();
		section2.delete();
		sectionFolder.delete();
	}

}
