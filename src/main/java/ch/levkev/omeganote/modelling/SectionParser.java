package ch.levkev.omeganote.modelling;

import ch.levkev.omeganote.modelling.interfaces.ISection;
import ch.levkev.omeganote.modelling.interfaces.ISectionParser;

public class SectionParser implements ISectionParser {
	
	@Override
	public String sectionToHtml(ISection section) {
		MarkdownProcessor processor = new MarkdownProcessor();
		String html =  processor.markdownToHtml(section.getContent());
		MathJaxInjector injector = new MathJaxInjector();
		return injector.inject(html);
	}
}
