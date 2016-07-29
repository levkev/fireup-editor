package ch.levkev.omeganote.modelling.interfaces;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is responsible for storing and managing the
 * corresponding md file in the notebook folder.
 */
public interface ISection {

	public String getName();
	public ArrayList<ITitle> getTitles();

	/**
	 * Returns a markdown version of the content stored
	 * within the section file.
	 */
	public String getContent();
	
	/**
	 * Saves the file to hold the new content. Also updates the section 
	 * instance according to the received string.
	 * @param content - markdown content of the section
	 */
	public void save(String content) throws IOException;
}
