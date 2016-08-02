package ch.levkev.omeganote.modelling.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ch.levkev.omeganote.modelling.Section;

public class SectionTest {
	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();
	
	@Test
	public void canReadNameOfSection() throws IOException {
		File file = tmp.newFile("test1.md");
		assertEquals("test1", new Section(file).getName());
	}
	
	@Test
	public void recognisesTitles() throws IOException {
		File file = tmp.newFile("test2.md");
		FileWriter writer = new FileWriter(file);
		writer.write("#title1\n\nsomething\n#title2");
		writer.close();
		Section section = new Section(file);
		assertEquals(2, section.getTitleNodes().size());
	}
	
	@Test
	public void recognisesContent() throws IOException {
		File file = tmp.newFile("test3.md");
		FileWriter writer = new FileWriter(file);
		writer.write("gugus");
		writer.close();
		Section section = new Section(file);
		assertEquals("gugus", section.getContent());
	}
	
	@Test
	public void sectionCanBeSaved() throws IOException, FileNotFoundException {
		File file = tmp.newFile("test4.md");
		FileWriter writer = new FileWriter(file);
		writer.write("gugus");
		writer.close();
		Section section = new Section(file);
		section.save("newGugus");
		assertEquals("newGugus", section.getContent());
		assertEquals("newGugus", contentOf(file));
	}
	
	@Test
	public void canHandleEmptySection() throws IOException {
		// file containing empty strings
		File file = tmp.newFile("test5.md");
		FileWriter writer = new FileWriter(file);
		writer.write("");
		writer.close();
		Section section = new Section(file);
		assertEquals("test5", section.getName());
		assertEquals("", section.getContent());
		
		// file not containing anything
		file = tmp.newFile("test6.md");
		section = new Section(file);
		assertEquals("test6", section.getName());
		assertEquals("", section.getContent());
	}
	
	@Test
	public void sectionWithNameAndNoContentIsProperlyRecognised() throws IOException {
		File file = tmp.newFile("test7.md");
		Section section = new Section(file);
		assertEquals("test7", section.getName());
		assertEquals("", section.getContent());
	}
	
	@Test
	public void savingSectionAlsoUpdatesTitles() throws IOException {
		File file = tmp.newFile("test8.md");
		FileWriter writer = new FileWriter(file);
		writer.write("#gugus");
		writer.close();
		Section section = new Section(file);
		section.save("##gugus");
		assertEquals(2, section.getTitleNodes().get(0).getDegree());
	}
	
	@Test
	public void canChangeNameOfSectionAndCorrespondingFile() throws IOException {
		File file = tmp.newFile("test9.md");
		FileWriter writer = new FileWriter(file);
		writer.write("gugus");
		writer.close();
		Section section = new Section(file);
		section.rename("test9a");
		assertEquals("test9a.md", section.getFile().getName());
		assertEquals("test9a", section.getName());
	}
	
	@Test
	public void renamingPreservesContent() throws IOException {
		File file = tmp.newFile("test9.md");
		FileWriter writer = new FileWriter(file);
		writer.write("gugus");
		writer.close();
		Section section = new Section(file);
		section.rename("test9a");
		assertTrue(section.getFile().exists());
		assertEquals("gugus", contentOf(section.getFile()));
	}
	
	private String contentOf(File file) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file).useDelimiter("\\A");
		String content = scanner.next();
		scanner.close();
		return content;
	}
}
