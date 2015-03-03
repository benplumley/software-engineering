import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class EventManager {

	private ArrayList<Event> eventList = new ArrayList<Event>();
	private static final int SAVE_FILE_LENGTH = 10;

	public EventManager() {

	}

	private void loadEventsFromFile() {
		File[] saveFiles = new File("/Data/My Events").listFiles();
		for (File saveFile : saveFiles) { // iterates through the files in the directory
			if (fileValid(saveFile)) {
				eventList.add(new Event(saveFile));
			}
		}
	}

	private boolean fileValid(File toCheck) {
		ArrayList<String> lines = new ArrayList<String>();
		try (Scanner fileReader = new Scanner(toCheck)) {
			int i = 0;
			while (fileReader.hasNext()) {
				lines.add(new String(fileReader.nextLine()));
			}
			if (lines.size() != SAVE_FILE_LENGTH) { // file did not contain the right number of lines
				return false;
			} else if (lines.get(0).length() == 0) { // no GUID was provided
				return false;
			} else if (lines.get(1).length() == 0) { // no event name was provided
				return false;
			} else if (lines.get(2).length() == 0) { // no start date was provided
				return false;
			} else if (lines.get(3).length() == 0) { // no end date was provided
				return false;
			}
			return true;
		} catch (FileNotFoundException e) { // the file does not exist
			return false;
		}
	}

}
