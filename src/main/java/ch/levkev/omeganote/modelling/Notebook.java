package ch.levkev.omeganote.modelling;

import java.util.ArrayList;

import ch.levkev.omeganote.modelling.interfaces.INotebook;
import ch.levkev.omeganote.modelling.interfaces.ISection;

import java.io.File;

public class Notebook implements INotebook {
	
	/**
	 * Describes the name of the directory that holds the
	 * section files.
	 */
	public static final String DIR_CONTAINING_SECTIONS = "Sections";
	
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
		ArrayList<File> sectionFiles = findSections();
		generateSections(sectionFiles);
	}
	
	private void generateSections(ArrayList<File> sectionFiles) {
		if (sectionFiles == null) return;
		for (File file : sectionFiles) {
			sections.add(new Section(file));
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
	private ArrayList<File> findSections() {
		File[] files = new File(path).listFiles();
		File[] potentialSectionFiles = findPotentialSectionFiles(files);
		return recogniseSections(potentialSectionFiles);
	}

	private File[] findPotentialSectionFiles(File[] files) {
		File sectionDir = new File(path + "/" + DIR_CONTAINING_SECTIONS);
		sectionDir.mkdir();
		return sectionDir.listFiles();
	}
	
	private ArrayList<File> recogniseSections(File[] potentialSectionFiles) {
		ArrayList<File> sectionFiles = new ArrayList<File>();
		for (File file : potentialSectionFiles) {
			if (file.getName().endsWith(Section.SECTION_SUFFIX)) {
				sectionFiles.add(file);
			}
		}
		return sectionFiles;
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
