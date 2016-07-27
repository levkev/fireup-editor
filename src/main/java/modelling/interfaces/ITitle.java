package modelling.interfaces;

/**
 * Data class for managing titles and their degree (e.g. 
 * <code>#FirstDegreeTitle</code>, 
 * <code>##SecondDegreeTitle</code>).
 */
public interface ITitle {
	public String getTitle();
	public int getDegree();
}
