package ch.levkev.omeganote.modelling;

import java.io.File;

import ch.levkev.omeganote.modelling.interfaces.IFolder;

public class Folder implements IFolder, Comparable<IFolder> {
	
	private File file;
	
	public Folder(File file) {
		this.file = file;
	}

	@Override
	public File getFile() {
		return this.file;
	}

	@Override
	public boolean isNotebook() {
		return this.file.toPath().resolve(".nb").toFile().exists();
	}

	@Override
	public int compareTo(IFolder o) {
		if (this.isNotebook() && !o.isNotebook()) {
			return -1;
		} else if(!this.isNotebook() && o.isNotebook()) {
			return 1;
		} else {
			int compareTo = this.file.getName().compareTo(o.getFile().getName());
			if (compareTo > 0) {
				return 1;
			} else if (compareTo < 0) {
				return -1;
			}
		}
		return 0;
	}

}
