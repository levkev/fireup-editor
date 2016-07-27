package modelling;

public class Title implements ITitle {

	private String title;
	private int degree;
	
	public Title(String title, int degree) {
		this.title = title;
		this.degree = degree;
	}
	
	@Override
	public String getTitle() {
		return title;	
	}

	@Override
	public int getDegree() {
		return degree;
	}

	@Override
	public String toString() {
		return title;
	}
	
	public boolean equals(Object otherTitle) {
		return (title == ((Title)otherTitle).getTitle() 
				&& degree == ((Title)otherTitle).getDegree());
	}
}
