package modelling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import modelling.interfaces.ISection;
import modelling.interfaces.ITitle;

public class Section implements ISection {

	/**
	 * The name of the section, declared by the top line of
	 * the section file.
	 */
	private String name;
	
	/**
	 * A list of titles, declared by a <code>#</code> sign.
	 */
	private ArrayList<ITitle> titles;
	
	/**
	 * Everything in the file except for the first line
	 * (which contains the name).
	 */
	private String content;
	

	// needed for save functionality
	private File file;
	
	public Section(File file) {
		String fileContent = readContent(file);
		name = firstLineOf(fileContent);
		content = withoutFirstLine(fileContent);
		titles = findTitles();
		this.file = file;
	}
	
	private String readContent(File file) {
		String content = null;
		try {
			Scanner scanner = new Scanner(file);
			content = scanner.useDelimiter("\\A").next();
			scanner.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			return "";
		}
		return content;
	}
	
	private ArrayList<ITitle> findTitles() {
		ArrayList<ITitle> titles = new ArrayList<ITitle>();
		@SuppressWarnings("resource") // eclipse does not notice that the scanner will be closed
		Scanner lineScanner = new Scanner(content).useDelimiter("\n");
		while (lineScanner.hasNext()) {
			String line = lineScanner.next();
			if (line.startsWith("#")) {
				titles.add(new Title(line));
			}
		}
		lineScanner.close();
		return titles;
	}

	private String firstLineOf(String s) {
		if (!s.contains("\n")) return s;
		return s.substring(0, s.indexOf("\n"));
	}
	
	@Override
	public void save(String contentIncludingName) throws IOException {
		name = firstLineOf(contentIncludingName);
		content = withoutFirstLine(contentIncludingName);
		titles = findTitles();
		FileWriter writer = new FileWriter(file);
		writer.write(contentIncludingName);
		writer.close();
	}
	
	private String withoutFirstLine(String s) {
		if (!s.contains("\n")) return "";
 		try {
			return s.substring(s.indexOf("\n")+1);
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ArrayList<ITitle> getTitles() {
		return titles;
	}

	@Override
	public String getContent() {
		return content;
	}
}
