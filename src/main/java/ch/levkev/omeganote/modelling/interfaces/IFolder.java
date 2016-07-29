package ch.levkev.omeganote.modelling.interfaces;

import java.io.File;

public interface IFolder extends Comparable<IFolder> {
	/**
	 * get the file object of this folder
	 * @return File object of the folder
	 */
	public File getFile();
	
	/**
	 * is this a notebook folder
	 * @return boolean true if this is a notebook folder
	 */
	public boolean isNotebook();
}
