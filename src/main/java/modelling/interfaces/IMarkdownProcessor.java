package modelling.interfaces;

/**
 * Is responsible for processing markdown section
 * plaintext files into HTML strings.<br><br>
 */
public interface IMarkdownProcessor {
	public String markdownToHtml(String markdownContent);
}
