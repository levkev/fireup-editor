package ch.levkev.omeganote.modelling;

import java.util.ArrayList;
import java.util.Stack;

import ch.levkev.omeganote.modelling.interfaces.ITitle;

/**
 * class to simplify title tree generation from list of titles
 *
 */
public class TitleTreeListBuilder {
	private ArrayList<TitleNode> nodes;
	private Stack<TitleNode> titleNodeStack;
	
	public TitleTreeListBuilder() {
		nodes = new ArrayList<TitleNode>();
		titleNodeStack = new Stack<TitleNode>();
	}
	
	public void add(ITitle title) {
		if (title.getDegree() == 1) {
			if (!titleNodeStack.isEmpty()) {
				titleNodeStack.pop();
			}
			titleNodeStack.push(new TitleNode(title));
			nodes.add(titleNodeStack.peek());
		}
		else {
			boolean childAdded = false;
			while (!childAdded) {
				int degree = title.getDegree();
				int rootDegree = 0;
				if (!titleNodeStack.isEmpty()) {
					rootDegree = titleNodeStack.peek().getDegree();
				}
				if (degree < rootDegree) {
					titleNodeStack.pop();
				}
				else if (degree == rootDegree) {
					titleNodeStack.pop();
					TitleNode newTitleNode = titleNodeStack.peek().addChild(title);
					titleNodeStack.push(newTitleNode);
					childAdded = true;
				}
				else {
					if (!titleNodeStack.isEmpty()) {
						TitleNode newTitleNode = titleNodeStack.peek().addChild(title);
						titleNodeStack.push(newTitleNode);
						childAdded = true;
					}
					else {
						titleNodeStack.push(new TitleNode(title));
						nodes.add(titleNodeStack.peek());
						childAdded = true;
					}
				}
			}
		}
	}
	
	public ArrayList<TitleNode> getTrees() {
		return nodes;
	}
}
