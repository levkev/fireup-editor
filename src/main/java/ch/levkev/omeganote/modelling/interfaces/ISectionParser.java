package ch.levkev.omeganote.modelling.interfaces;

/**
 * Parses section content to return plain HTML.
 */
public interface ISectionParser {

	public String sectionToHtml(ISection section);
}
