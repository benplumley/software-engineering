package pim;

import pim.gui.GUI;

public class PIM {

	private static EventManager events;
	private static TaskManager tasks;
	private static ContactManager contacts;
	private static GUI gui;

	/**
	 * Main entry point of the application
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		events = new EventManager();
		tasks = new TaskManager();
		contacts = new ContactManager();

		gui = new GUI();
	}

	/**
	 * Gets the EventManager instance
	 * @return EventManager instance
	 */
	public static EventManager getEventManager() {
		return events;
	}

	/**
	 * Gets the TaskManager instance
	 * @return TaskManager instance
	 */
	public static TaskManager getTaskManager() {
		return tasks;
	}

	/**
	 * Gets the ContactManager instance
	 * @return ContactManager instance
	 */
	public static ContactManager getContactManager() {
		return contacts;
	}

	/**
	 * Gets the GUI instance
	 * @return GUI instance
	 */
	public static GUI getGui() {
		return gui;
	}

	/**
	 * Checks if a String is a positive integer.
	 * @param str String to check
	 * @return True if is numeric and positive
	 */
	public static boolean checkIsNumeric(String str) {
		try {
			int i = Integer.parseInt(str);

			if (i < 0) {
				return false;
			}

			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
}
