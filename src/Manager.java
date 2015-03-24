import java.util.Random;
import java.util.Scanner;
import java.io.*;

public abstract class Manager {

	protected Manager() {

	}

	public List<List<String>> loadFiles(String path) {

		List<List<String>> files = new ArrayList<>();

		try {
			File[] saveFiles = new File(path).listFiles();
			for (File saveFile : saveFiles) { // iterates through the files in the directory
				List<String> lines = Files.readAllLines(Paths.get(toCheck.getAbsolutePath()), Charset.defaultCharset());

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

	protected abstract boolean isValidFile(List<String> lines);
}
