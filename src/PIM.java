public class PIM {

	private EventManager events = new EventManager();
	private TaskManager tasks = new TaskManager();
	private ContactManager contacts = new ContactManager();
	private GUI gui = new GUI();

	public PIM() {

	}

	public static void main(String[] args) {
		PIM thisPIM = new PIM();
	}

}
