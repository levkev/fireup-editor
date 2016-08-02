package ch.levkev.omeganote.modelling.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ch.levkev.omeganote.modelling.Title;
import ch.levkev.omeganote.modelling.TitleNode;
import ch.levkev.omeganote.modelling.TitleTreeListBuilder;
import ch.levkev.omeganote.modelling.interfaces.ITitle;

public class TitleTreeListBuilderTest {

	@Test
	public void returnsEmptyListWhenNoTitleIsAdded() {
		TitleTreeListBuilder builder = new TitleTreeListBuilder();
		assertEquals(new ArrayList<TitleNode>(), builder.getTrees());
	}
	
	@Test
	public void correctlyOrganisesDegreeOneTitles() {
		TitleTreeListBuilder builder = new TitleTreeListBuilder();
		ArrayList<ITitle> titles = new ArrayList<ITitle>();
		for (int i = 0; i < 4; i++)
			titles.add(new Title("#" + i));		
		for (int i = 0; i < 4; i++)
			builder.add(titles.get(i));
		for (int i = 0; i < 4; i++) {
			assertEquals(titles.get(i), builder.getTrees().get(i).getTitle());
		}
	}
	
	@Test
	public void correctlyOrganizesComplexSetOfTitles() {
		/*
		 * 				 1
		 * 				/  \
		 * 			   2    3
		 * 			 /   \
		 * 		    4	  5
		 */
		
		ArrayList<ITitle> titles = new ArrayList<ITitle>();
		titles.add(new Title("#1"));
		titles.add(new Title("##2"));
		titles.add(new Title("###4"));
		titles.add(new Title("###5"));
		titles.add(new Title("##3"));
		titles.add(new Title("#a"));
		titles.add(new Title("##b"));
		titles.add(new Title("###d"));
		titles.add(new Title("###e"));
		titles.add(new Title("##c"));
		
		TitleTreeListBuilder builder = new TitleTreeListBuilder();
		for (int i = 0; i < 10; i++) 
			builder.add(titles.get(i));
		
		String expected0 = "1: [2: [4, 5], 3]";
		String expected1 = "a: [b: [d, e], c]";
		
		ArrayList<TitleNode> result = builder.getTrees();
		assertEquals(expected0, result.get(0).toArrayStringRepresentation());
		assertEquals(expected1, result.get(1).toArrayStringRepresentation());
	}
	
	@Test
	public void doesNotDependOnStartingWithFirstDegreeTitle() {
		TitleTreeListBuilder builder = new TitleTreeListBuilder();
		builder.add(new Title("##2"));
		// success if no exception is thrown
	}
}
