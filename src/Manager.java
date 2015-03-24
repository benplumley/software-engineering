import java.util.Random;
import java.util.Scanner;
import java.io.*;

public abstract class Manager {

	private static int MAX_GUID;

	protected Manager() {
		File configFile = new File("/Data/config.txt");
		try (Scanner fileReader = new Scanner(configFile)) {
			MAX_GUID = Integer.parseInt(fileReader.nextLine().split(" = ")[1]);
		} catch (FileNotFoundException e) {
			// create a config file with default values
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			// create a line in the config file with the default value
		}

	}

	protected int generateGUID() {
		boolean conflictingID = true;
		// Random rand
		while (conflictingID) { // generate a new GUID

		}
		
		return 0;
	}

}
