package modelling;

/**
 * Parses section files to return plain HTML.
 */
public interface ISectionParser {
	public ISection parseFile(String content);
}
