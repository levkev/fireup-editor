package ch.levkev.omeganote.modelling;

import static org.junit.Assert.*;

import org.junit.Test;

import ch.levkev.omeganote.modelling.Title;
import ch.levkev.omeganote.modelling.TitleNode;

public class TitleNodeTest {

	@Test
	public void arrayStringRepresentationIsAccurate() {
		TitleNode node = new TitleNode(new Title("#1"));
		node.addChild(new Title("##2"));
		node.addChild(new Title("##3"));
		node.getChildren().get(1).addChild(new Title("###4"));
		node.addChild(new Title("##5"));
		assertEquals("1: [2, 3: [4], 5]", node.toArrayStringRepresentation());
	}

}
