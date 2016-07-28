package ch.levkev.omeganote.modelling;

import org.pegdown.PegDownProcessor;

import ch.levkev.omeganote.modelling.interfaces.IMarkdownProcessor;

public class MarkdownProcessor implements IMarkdownProcessor {

	@Override
	public String markdownToHtml(String markdownContent) {
		PegDownProcessor processor = new PegDownProcessor();
		return processor.markdownToHtml(markdownContent);
	}
}
