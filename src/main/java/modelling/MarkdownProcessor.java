package modelling;

import modelling.interfaces.IMarkdownProcessor;
import modelling.interfaces.ISection;

public class MarkdownProcessor implements IMarkdownProcessor {

	@Override
	public String processSection(ISection section) {
		SectionParser parser = new SectionParser();
		String processed = parser.parseFile(section.getContent());
		return processed;
	}
	
}
