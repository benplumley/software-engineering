public class PIM {

	private EventManager events;
	private TaskManager tasks;
	private ContactManager contacts;
	private GUI gui;

	public PIM() {
		this.events = new EventManager();
		this.tasks = new TaskManager();
		this.contacts = new ContactManager();
	}

	public static void main(String[] args) {
		PIM thisPIM = new PIM();
	}

}
