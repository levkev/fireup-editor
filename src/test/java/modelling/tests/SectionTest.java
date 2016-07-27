package modelling.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import modelling.Section;

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
	public void recognisesTitles() throws IOException {
		File file = tmp.newFile("test2.tmp");
		FileWriter writer = new FileWriter(file);
		writer.write("name\n\n#title1\n\nsomething\n#title2\n###title3");
		writer.close();
		Section section = new Section(file);
		assertEquals(3, section.getTitles().size());
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
	
	@Test
	public void sectionWithNameAndNoContentIsProperlyRecognised() throws IOException {
		File file = tmp.newFile("test7.tmp");
		FileWriter writer = new FileWriter(file);
		writer.write("name");
		writer.close();
		Section section = new Section(file);
		assertEquals("name", section.getName());
		assertEquals("", section.getContent());
	}
	
	@Test
	public void savingSectionAlsoUpdatesTitles() throws IOException {
		File file = tmp.newFile("test8.tmp");
		FileWriter writer = new FileWriter(file);
		writer.write("name\n#gugus");
		writer.close();
		Section section = new Section(file);
		section.save("newName\n##gugus");
		assertEquals(2, section.getTitles().get(0).getDegree());
	}
	
	private String contentOf(File file) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file).useDelimiter("\\A");
		String content = scanner.next();
		scanner.close();
		return content;
	}
}
