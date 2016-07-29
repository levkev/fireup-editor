package ch.levkev.omeganote.modelling;

import java.util.ArrayList;

import ch.levkev.omeganote.modelling.interfaces.INotebook;
import ch.levkev.omeganote.modelling.interfaces.ISection;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * The Notebook class represents a folder on the filesystem with all sections
 * containing the Markdown files. It is used in the application whenever there
 * is an interaction with a notebook containing all sections.
 * 
 * @author kreemer
 * @version 1.0
 */
public class Notebook implements INotebook {
	
	/**
	 * Describes the name of the directory that holds the
	 * section files.
	 */
	public static final String DIR_CONTAINING_SECTIONS = "Sections";
	
	/**
	 * Name of the metadata file
	 */
	public static final String FILE_CONTAINING_METADATA = ".nb";
	
	private File file;
	
	private ArrayList<ISection> sections;
	

	/**
	 * Creates a notebook with all its sections. Its name
	 * will be the name of its home directory.
	 * @param path the path as a File Object
	 */
	public Notebook(File file) {
		this.file = file;
		this.generateStructure();
		this.sections = this.findSections();
	}
	
	/**
	 * Creates a notebook with all its sections. Its name
	 * will be the name of its home directory.
	 * @param path the path as a string
	 */
	public Notebook(String path) {
		this(new File(path));
	}
	
	/**
	 * Generates the structure of a notebook class
	 */
	private void generateStructure() {
		File sectionsDirectory = this.getSectionsDirectory();
		if (!sectionsDirectory.exists()) {
			sectionsDirectory.mkdir();
		}
		File metadataFile = this.getMetadataFile();
		if (!metadataFile.exists()) {
			try {
				metadataFile.createNewFile();
			} catch (IOException e) {
				// TODO: cleanup
				e.printStackTrace();
			}
		}
	}

	/**
	 * Scans the notebook for files describing sections.
	 * Assumes that sections are stored in the directory
	 * called Sections and that they end with .md
	 * 
	 * @param path - the path of the notebook
	 * @return all sections in the notebook as a files array
	 */
	private ArrayList<ISection> findSections() {
		File[] files = this.getSectionsDirectory().listFiles(new FilenameFilter() {
			public boolean accept(File directory, String name) {
				return name.endsWith(".md");
			}
		});
		
		
		ArrayList<ISection> sections = new ArrayList<ISection>();
		if (files != null) {
			for (File file : files)  {
				sections.add(new Section(file));
			}
		}
		
		return sections;
	}
	
	/**
	 * get the sections directory as a file object
	 * 
	 * @return File
	 */
	private File getSectionsDirectory() {
		return this.file.toPath().resolve(DIR_CONTAINING_SECTIONS).toFile();
	}
	
	/**
	 * get the metadata file as file object
	 * 
	 * @return File
	 */
	private File getMetadataFile() {
		return this.file.toPath().resolve(FILE_CONTAINING_METADATA).toFile();
	}
	
	@Override
	public String getName() {
		return this.file.getName();
	}

	@Override
	public String getPath() {
		return this.file.getPath();
	}

	@Override
	public ArrayList<ISection> getSections() {
		return this.sections;
	}
}
