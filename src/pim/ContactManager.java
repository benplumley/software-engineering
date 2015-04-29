package pim;

import java.io.File;
import java.util.*;

public class ContactManager extends Manager {

	private final Map<UUID, Contact> contacts;
	private static final int SAVE_FILE_LENGTH = 13;

	/**
	 * Constructs the ContactManager class
	 */
	public ContactManager() {
		this.contacts = new HashMap<>();

		generateDirectories();
		loadContacts();
	}

	/**
	 * Initializes all contacts
	 */
	protected void loadContacts() {
		for (List<String> lines : loadFiles("Data/My Contacts")) {
			Contact contact = new Contact(lines.get(0), lines);

			this.contacts.put(contact.getId(), contact);
		}
	}

	/**
	 * Generates the save directories if they don't already exist
	 */
	@Override
	public void generateDirectories() {
		File file = new File("Data/My Contacts/");

		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * Checks if a file is valid to load
	 * @param lines Lines of the file
	 * @return True if should be loaded
	 */
	@Override
	protected boolean isFileValid(List<String> lines) {
		return lines.size() == SAVE_FILE_LENGTH && lines.get(0).length() > 0 && lines.get(1).length() > 0; // id and first name is required
	}

	/**
	 * Adds an instance of Contact to the map.
	 * This should not be directly called.
	 *
	 * @param savable Contact to add
	 */
	@Override
	public void add(Savable savable) {
		if (savable instanceof Contact) {
			Contact contact = (Contact)savable;
			this.contacts.put(contact.getId(), contact);
		}
	}

	/**
	 * Removes an instance of Contact from the map.
	 * This should not be directly called.
	 *
	 * @param savable Contact to remove
	 */
	@Override
	public void remove(Savable savable) {
		if (savable instanceof Contact) {
			Contact contact = (Contact)savable;
			this.contacts.remove(contact.getId());
		}
	}

	/**
	 * Gets a specific contact by ID
	 * @param id ID of the contact to get
	 * @return Contact
	 */
	public Contact getContact(UUID id) {
		return this.contacts.get(id);
	}

	/**
	 * Gets all valid contacts to be displayed
	 * @return Valid contacts
	 */
	public Collection<Contact> getContacts() {
		return this.contacts.values();
	}
}
