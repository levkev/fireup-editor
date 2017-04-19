package modelling;

/**
 * Data class for managing titles and their
 * degree (e.g. #FirstDegreeTitle, 
 * ##SecondDegreeTitle)
 */
public interface ITitle {
	public String getTitle();
	public int getDegree();
}
