package modelling;

import java.util.ArrayList;

import modelling.interfaces.INotebook;
import modelling.interfaces.ISection;

import java.io.File;

public class Notebook implements INotebook {
	
	/**
	 * Describes the name of the directory that holds the
	 * section files.
	 */
	public final String DIR_CONTAINING_SECTIONS = "Sections";
	
	private String name;
	private String path;
	private ArrayList<ISection> sections;
	
	/**
	 * Creates a notebook with all its sections. Its name
	 * will be the name of its home directory.
	 * @param path the path as a string
	 */
	public Notebook(String path) {
		this.name = new File(path).getName();
		this.path = path;
		this.sections = new ArrayList<ISection>();
		File[] sectionFiles = findSections();
		generateSections(sectionFiles);
	}
	
	private void generateSections(File[] sectionFiles) {
		if (sectionFiles == null) return;
		for (File file : sectionFiles) {
			sections.add(new Section(file));
		}
	}

	/**
	 * Scans the notebook for files describing sections.
	 * Assumes that sections are stored in the directory
	 * called Sections. 
	 * 
	 * @param path - the path of the notebook
	 * @return all sections in the notebook as a files array
	 */
	private File[] findSections() {
		File[] sectionFiles;
		File[] files = new File(path).listFiles();
		if (files == null) return null;
		for (File file : files) {
			if (file.isDirectory()) {
				if (file.getName().equals(DIR_CONTAINING_SECTIONS)) {
					sectionFiles = file.listFiles();
					return sectionFiles;
				}
			}
		}
		return null;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public ArrayList<ISection> getSections() {
		return sections;
	}
}
