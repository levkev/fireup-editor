package ch.levkev.omeganote.modelling.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.levkev.omeganote.modelling.MarkdownProcessor;

public class MarkdownProcessorTest {

	// basically garantees that markdown parsing works
	@Test
	public void parsesBasicMarkdown() {
		MarkdownProcessor processor = new MarkdownProcessor();
		String result = processor.markdownToHtml("#title");
		assertEquals("<h1>title</h1>", result);
		result = processor.markdownToHtml("##title2\n###title3");
		assertEquals("<h2>title2</h2>\n<h3>title3</h3>", result);
	}
}
