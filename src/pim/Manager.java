package pim;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public abstract class Manager {
	/**
	 * Loads all the files in a given directory and checks that they are valid
	 * @param path Path to load all files in a given folder
	 */
	public List<List<String>> loadFiles(String path) {

		List<List<String>> files = new ArrayList<>();

		try {
			File[] saveFiles = new File(path).listFiles();
			for (File saveFile : saveFiles) {
				List<String> lines = Files.readAllLines(saveFile.toPath(), Charset.forName("ISO-8859-1"));
				if (isFileValid(lines)) {
					files.add(lines);
				}
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}

		return files;
	}

	/**
	 * Generates the save directories if they don't already exist
	 */
	public abstract void generateDirectories();

	/**
	 * Checks if a file is valid to load
	 * @param lines Lines of the file
	 * @return True if should be loaded
	 */
	protected abstract boolean isFileValid(List<String> lines);

	/**
	 * Adds an instance of Savable.
	 * This should not be directly called.
	 *
	 * @param savable Savable to add
	 */
	public abstract void add(Savable savable);

	/**
	 * Removes an instance of Savable.
	 * This should not be directly called.
	 *
	 * @param savable Savable to remove
	 */
	public abstract void remove(Savable savable);
}
