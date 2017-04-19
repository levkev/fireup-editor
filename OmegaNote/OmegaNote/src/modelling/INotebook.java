package modelling;

/**
 * This class describes the basic structure of
 * notebooks. It is responsible for managing the
 * folder in which the data for the notebook
 * (e.g. section files, settings, ...) is stored.
 */
public interface INotebook {
	public String getName();
	public String getPath();
	public ISection[] getSections();
	
	public boolean hasCustomOutputCss();
	public boolean hasCustomPrintCss();
}
