package ch.levkev.omeganote.modelling;

import ch.levkev.omeganote.modelling.interfaces.ISection;
import ch.levkev.omeganote.modelling.interfaces.ISectionParser;

public class SectionParser implements ISectionParser {
	
	@Override
	public String sectionToHtml(ISection section) {
		MarkdownProcessor processor = new MarkdownProcessor();
		// will process mathjax here
		return processor.markdownToHtml(section.getContent());
	}

}
