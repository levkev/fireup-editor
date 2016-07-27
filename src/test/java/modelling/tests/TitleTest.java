package modelling.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelling.Title;

public class TitleTest {

	@Test
	public void recognisesTitleAndItsDegree() {
		Title title = new Title("##title");
		assertEquals("title", title.getTitle());
		assertEquals(2, title.getDegree());
	}

}
