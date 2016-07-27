package modelling;

import org.pegdown.PegDownProcessor;

import modelling.interfaces.IMarkdownProcessor;

public class MarkdownProcessor implements IMarkdownProcessor {

	@Override
	public String processSection(String markdownContent) {
		PegDownProcessor processor = new PegDownProcessor();
		return processor.markdownToHtml(markdownContent);
	}
	
}
