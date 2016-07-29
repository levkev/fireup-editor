package ch.levkev.omeganote.modelling;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;

import ch.levkev.omeganote.modelling.interfaces.IFolder;

/**
 * this class is used to listing a directory and give special proxy objects for notebook directories
 * 
 * @author kreemer
 * @version 1.0
 */
public class FolderBrowser {

	private File path;
	
	public FolderBrowser(File path) {
		assert(path != null);
		this.path = path;
	}
	
	/**
	 * list all folders in the right ordering
	 * 
	 * @return ArrayList<IFolder> list of folder
	 */
	public ArrayList<IFolder> listFiles() {
		ArrayList<IFolder> folders = new ArrayList<IFolder>();
		
		File[] directories = this.path.listFiles(new FileFilter() {
			public boolean accept(File dir) {
				return dir.isDirectory();
			}
		});
		
		for (File directory : directories) {
			folders.add(new Folder(directory));
		}
		Collections.sort(folders);
		return folders;
	}
	
}
