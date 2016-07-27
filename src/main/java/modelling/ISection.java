package modelling;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Is responsible for storing and managing the
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
	 * 
	 * @param content
	 * @throws IOException
	 */
	public void save(String content) throws IOException;
}
