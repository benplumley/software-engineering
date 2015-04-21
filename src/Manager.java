import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.Files;

public abstract class Manager {

	protected Manager() {

	}

	/**
	 * Loads all the files in a given directory and checks that they are valid
	 * @param path Path to load all files in a given folder
	 */
	public List<List<String>> loadFiles(String path) {

		List<List<String>> files = new ArrayList<>();

		try {
			File[] saveFiles = new File(path).listFiles();
			for (File saveFile : saveFiles) {
				List<String> lines = Files.readAllLines(saveFile.toPath(), Charset.defaultCharset());
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

	protected abstract boolean isFileValid(List<String> lines);

	public abstract void add(Savable savable);
	public abstract void remove(Savable savable);
}
