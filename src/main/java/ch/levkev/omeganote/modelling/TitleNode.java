package ch.levkev.omeganote.modelling;

import java.util.ArrayList;

import ch.levkev.omeganote.modelling.interfaces.ITitle;

public class TitleNode {
	private ITitle title;
	private ArrayList<TitleNode> children;
	private boolean collapsed;
	
	public TitleNode(ITitle title) {
		this.title = title;
		this.children = new ArrayList<TitleNode>();
		this.collapsed = false;
	}
	
	public TitleNode addChild(ITitle title) {
		TitleNode node = new TitleNode(title);
		this.children.add(node);
		return node;
	}

	public int getDegree() {
		return this.title.getDegree();
	}
	
	public boolean isCollapsed() {
		return collapsed;
	}
	
	public void collapse() {
		collapsed = true;
	}
	
	public void expand() {
		collapsed = false;
	}

	public ITitle getTitle() {
		return title;
	}
	
	public ArrayList<TitleNode> getChildren() {
		return children;
	}
	
	public String toString() {
		if (children.isEmpty()) {
			return title.getTitle();
		}
		else {
			String string = title.getTitle() + ": [";
			for (TitleNode child : children) {
				string += child.toString() + ", ";
			}
			string = string.substring(0, string.length() - 2); // removing last comma
			string += "]";
			return string;
		}
	}
}