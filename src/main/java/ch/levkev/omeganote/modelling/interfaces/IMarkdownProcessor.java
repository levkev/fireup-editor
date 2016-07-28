package ch.levkev.omeganote.modelling.interfaces;

/**
 * Is responsible for processing markdown section
 * plaintext files into HTML strings.
 */
public interface IMarkdownProcessor {
	public String markdownToHtml(String markdownContent);
}
