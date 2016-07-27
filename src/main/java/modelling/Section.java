package modelling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
		name = firstLineOf(file);
		content = readContent(file);
		titles = findTitles(file);
		this.file = file;
	}
	
	private String readContent(File file) {
		String content = null;
		try {
			Scanner scanner = new Scanner(file);
			content = withoutFirstLine(scanner.useDelimiter("\\A").next());
			scanner.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			return "";
		}
		return content;
	}
	
	private ArrayList<ITitle> findTitles(File file) {
		try {
			ArrayList<ITitle> titles = new ArrayList<ITitle>();
			FileReader fileReader = new FileReader(file);
			BufferedReader lineReader = new BufferedReader(fileReader);
			String line = lineReader.readLine();
			while (line != null) {
				if (line.startsWith("#")) {
					int degree = line.length() - line.replace("#", "").length();
					String name = line.replace("#", "");
					titles.add(new Title(name, degree));
				}
				line = lineReader.readLine();
			}
			lineReader.close();
			return titles;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		return null;
	}

	// TODO: more elegant solution
	private String firstLineOf(File file) {
		try {
			String name;
			FileReader fileReader = new FileReader(file);
			BufferedReader nameReader = new BufferedReader(fileReader);
			name = nameReader.readLine();
			nameReader.close();
			if (name == null) return "";
			return name;
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		return "";
	}
	
	private String firstLineOf(String s) {
		return s.substring(0, s.indexOf("\n"));
	}
	
	/**
	 * 
	 * @param contentIncludingName
	 * @throws IOException
	 */
	@Override
	public void save(String contentIncludingName) throws IOException {
		name = firstLineOf(contentIncludingName);
		content = withoutFirstLine(contentIncludingName);
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
