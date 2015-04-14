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

			this.contacts.put(contact.getId(), contact);
		}
	}

	@Override
	protected boolean isFileValid(List<String> lines) {
		return lines.size() == SAVE_FILE_LENGTH && lines.get(0).length() > 0 && lines.get(1).length() > 0; // id and first name is required
	}

}
