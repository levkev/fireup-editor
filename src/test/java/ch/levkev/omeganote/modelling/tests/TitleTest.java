package ch.levkev.omeganote.modelling.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.levkev.omeganote.modelling.Title;

public class TitleTest {

	@Test
	public void recognisesTitleAndItsDegree() {
		Title title = new Title("##title");
		assertEquals("title", title.getTitle());
		assertEquals(2, title.getDegree());
	}
}
