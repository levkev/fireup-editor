package modelling.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelling.MarkdownProcessor;

public class MarkdownProcessorTest {

	@Test
	public void parsesBasicMarkdown() {
		MarkdownProcessor processor = new MarkdownProcessor();
		String result = processor.processSection("#title");
		assertEquals("<h1>title</h1>", result);
		result = processor.processSection("##title2\n###title3");
		assertEquals("<h2>title2</h2>\n<h3>title3</h3>", result);
	}

}
