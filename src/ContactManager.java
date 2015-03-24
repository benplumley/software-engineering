import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class ContactManager extends Manager {

	private final Map<UUID, Contact> contacts;
	private static final int SAVE_FILE_LENGTH = 15;

	public ContactManager() {
		this.contacts = new HashMap<>();

		loadContacts();
	}

	protected void loadContacts() {
		for (List<String> lines : loadFiles("/Data/My Contacts")) {
			Contact contact = new Contact(lines.get(0), lines);
		}
	}

	@Override
	protected boolean isFileValid(List<String> lines) {
		if (lines.size() != SAVE_FILE_LENGTH) {
			return false;
		}

		return lines.get(0).length() > 0 && lines.get(1).length() > 0; // id and first name is required
	}

}
