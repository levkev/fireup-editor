package ch.levkev.omeganote.modelling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ch.levkev.omeganote.modelling.interfaces.ISection;

public class Section implements ISection {

	public static final String SECTION_SUFFIX = ".md";
	
	private String name;
	private ArrayList<TitleNode> titleNodes;
	private String content;
	private File file;
	
	public Section(File file) {
		this.name = extractName(file);
		this.content = readContent(file);
		this.titleNodes = findTitleNodes();
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
			content = "";
		}
		return content;
	}
	
	private String extractName(File file) {
		return file.getName().replace(SECTION_SUFFIX, "");
	}
	
	private ArrayList<TitleNode> findTitleNodes() {
		TitleTreeListBuilder builder = new TitleTreeListBuilder();
		@SuppressWarnings("resource") // eclipse does not notice that the scanner will be closed
		Scanner lineScanner = new Scanner(content).useDelimiter("\n");
		while (lineScanner.hasNext()) {
			String line = lineScanner.next();
			if (line.startsWith("#")) {
				builder.add(new Title(line));
			}
		}
		lineScanner.close();
		return builder.getTrees();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(String content) throws IOException {
		this.content = content;
		titleNodes = findTitleNodes();
		FileWriter writer = new FileWriter(file);
		writer.write(content);
		writer.close();
	}
	
	public void rename(String newName) {
		this.name = newName;
		String path = file.getParent();
		File newFile = new File(path + "/" + name + SECTION_SUFFIX);
		file.renameTo(newFile);
		this.file = newFile;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ArrayList<TitleNode> getTitleNodes() {
		return titleNodes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContent() {
		return content;
	}

	public File getFile() {
		return file;
	}
	
}
