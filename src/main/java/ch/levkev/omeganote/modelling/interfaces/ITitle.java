package ch.levkev.omeganote.modelling.interfaces;

/**
 * Data class for managing titles and their degree. Constructor
 * should be able to create instances from strings like "#title".
 */
public interface ITitle {
	public String getTitle();
	public int getDegree();
}
