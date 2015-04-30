package pim;

import java.io.File;
import java.util.*;

public class EventManager extends Manager {

	private Map<UUID, Event> events;
	private static final int SAVE_FILE_LENGTH = 6;

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

	/**
	 * Generates the save directories if they don't already exist
	 */
	@Override
	public void generateDirectories() {
		File file = new File("Data/My Events/");

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
		return lines.size() == SAVE_FILE_LENGTH && lines.get(0).length() > 0 &&
		 lines.get(1).length() > 0 && lines.get(2).length() > 0; // id, event name, start date are required
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

	/**
	 * Gets a specific event by ID
	 * @param id ID of the event to get
	 * @return Specific Event
	 */
	public Event getEvent(UUID id) {
		return this.events.get(id);
	}

	/**
	 * Gets all valid Events to be displayed and sorts by date
	 * @return Valid events
	 */
	public Collection<Event> getEvents() {
		List<Event> activeEvents = new ArrayList<>();
		Date currentDate = new Date();
		for (Event event : this.events.values()) {
			Calendar startDate = Calendar.getInstance();
			Calendar nowCal = Calendar.getInstance();
			startDate.setTime(event.getStartDate());
			nowCal.setTime(currentDate);
			boolean sameDay = startDate.get(Calendar.YEAR) == nowCal.get(Calendar.YEAR) &&
					startDate.get(Calendar.DAY_OF_YEAR) == nowCal.get(Calendar.DAY_OF_YEAR);

			if (currentDate.after(event.getStartDate()) && !sameDay) {
				continue;
			}

			activeEvents.add(event);
		}

		Collections.sort(activeEvents, new Comparator<Event>()
		{
			@Override
			public int compare(Event o1, Event o2)
			{
				return o1.getStartDate().after(o2.getStartDate()) ? 1 : -1;
			}
		});

		return activeEvents;
	}
}
