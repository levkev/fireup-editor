package modelling.interfaces;

/**
 * Parses md text files to return plain HTML.
 */
public interface ISectionParser {
	public String parseFile(String markdownContent);
}
