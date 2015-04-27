package pim;

import pim.gui.GUI;

public class PIM {

	private static EventManager events;
	private static TaskManager tasks;
	private static ContactManager contacts;
	private static GUI gui;

	public static void main(String[] args) {
		events = new EventManager();
		tasks = new TaskManager();
		contacts = new ContactManager();

		gui = new GUI();
	}

	public static EventManager getEventManager() {
		return events;
	}

	public static TaskManager getTaskManager() {
		return tasks;
	}

	public static ContactManager getContactManager() {
		return contacts;
	}
}
