package modelling;

/**
 * Is responsible for processing markdown section
 * plaintext files into HTML strings.<br><br>
 * MarkdownProcessor is actually a proxy to hide
 * algorithmic complexity of the <code>SectionParser</code>
 * class.
 */
public interface IMarkdownProcessor {
	public String processSection(ISection section);
}
