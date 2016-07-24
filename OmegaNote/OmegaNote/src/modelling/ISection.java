package modelling;

/**
 * Is responsible for providing a HTML version
 * of the section file stored in the notebook
 * folder.
 */
public interface ISection {
	public String getName();
	public ITitle[] getTitles();
	
	/**
	 * Returns a markdown version of the content
	 * stored within the section file.
	 */
	public String getContent();
	
	/**
	 * Returns a HTML version of the content stored
	 * within the section file.
	 */
	public String getHTML();
}
