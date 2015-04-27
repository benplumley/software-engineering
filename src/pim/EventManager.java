package pim;

import java.io.File;
import java.util.*;

public class EventManager extends Manager {

	private Map<UUID, Event> events;
	private static final int SAVE_FILE_LENGTH = 10;

	public EventManager() {
		this.events = new HashMap<>();

		generateDirectories();
		loadEvents();
	}

	protected void loadEvents() {
		for (List<String> lines : loadFiles("Data/My Events")) {
			Event event = new Event(lines.get(0), lines);

			this.events.put(event.getId(), event);
		}
	}

	@Override
	public void generateDirectories() {
		File file = new File("Data/My Events/");

		if (!file.exists()) {
			file.mkdirs();
		}
	}

	@Override
	protected boolean isFileValid(List<String> lines) {
		return lines.size() == SAVE_FILE_LENGTH && lines.get(0).length() > 0 &&
		 lines.get(1).length() > 0 && lines.get(2).length() > 0 && lines.get(3).length() > 0; // id, event name, start date and end date is required
	}

	/**
	 * Adds an instance of Event to the map.
	 * This should not be directly called.
	 *
	 * @param savable Event to add
	 */
	@Override
	public void add(Savable savable) {
		if (savable instanceof Event) {
			Event event = (Event)savable;
			this.events.put(event.getId(), event);
		}
	}

	/**
	 * Removes an instance of Event to the map.
	 * This should not be directly called.
	 *
	 * @param savable Event to remove
	 */
	@Override
	public void remove(Savable savable) {
		if (savable instanceof Event) {
			Event event = (Event)savable;
			this.events.remove(event.getId());
		}
	}

	public Event getEvent(UUID id) {
		return this.events.get(id);
	}

	public Collection<Event> getEvents()
	{
		return this.events.values();
	}
}
