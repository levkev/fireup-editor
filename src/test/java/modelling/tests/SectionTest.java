package modelling.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import modelling.Section;
import modelling.Title;
import modelling.interfaces.ITitle;

public class SectionTest {
	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();
	
	@Test
	public void canReadNameOfSection() throws IOException {
		File file = tmp.newFile("test1.tmp");
		FileWriter writer = new FileWriter(file);
		writer.write("name\ngugus");
		writer.close();
		assertEquals("name", new Section(file).getName());
	}
	
	@Test
	public void recognisesTitlesAndTheirDegrees() throws IOException {
		File file = tmp.newFile("test2.tmp");
		FileWriter writer = new FileWriter(file);
		ArrayList<ITitle> titles = new ArrayList<ITitle>();
		titles.add(new Title("title1", 1));
		titles.add(new Title("title2", 1));
		titles.add(new Title("title3", 3));
		writer.write("name\n\n#title1\n\nsomething\n#title2\n###title3");
		writer.close();
		Section section = new Section(file);
		ArrayList<ITitle> sectionTitles = section.getTitles();
		for (int i = 0; i < 3; i++) {
			assertEquals(titles.get(i).getTitle(), sectionTitles.get(i).getTitle());
			assertEquals(titles.get(i).getDegree(), sectionTitles.get(i).getDegree());
		}
	}
	
	@Test
	public void recognisesContent() throws IOException {
		File file = tmp.newFile("test3.tmp");
		FileWriter writer = new FileWriter(file);
		writer.write("name\ngugus");
		writer.close();
		Section section = new Section(file);
		assertEquals("gugus", section.getContent());
	}
	
	@Test
	public void sectionCanBeSaved() throws IOException, FileNotFoundException {
		File file = tmp.newFile("test4.tmp");
		FileWriter writer = new FileWriter(file);
		writer.write("name\ngugus");
		writer.close();
		Section section = new Section(file);
		section.save("newName\nnewGugus");
		assertEquals("newGugus", section.getContent());
		assertEquals("newName", section.getName());
		assertEquals("newName\nnewGugus", contentOf(file));
	}
	
	@Test
	public void canHandleEmptySection() throws IOException {
		// file containing empty strings
		File file = tmp.newFile("test5.tmp");
		FileWriter writer = new FileWriter(file);
		writer.write("");
		writer.close();
		Section section = new Section(file);
		assertEquals("", section.getName());
		assertEquals("", section.getContent());
		
		// file not containing anything
		file = tmp.newFile("test6.tmp");
		section = new Section(file);
		assertEquals("", section.getName());
		assertEquals("", section.getContent());
	}
	
	private String contentOf(File file) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file).useDelimiter("\\A");
		String content = scanner.next();
		scanner.close();
		return content;
	}
}
