import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class EventManager extends Manager {

	private Map<UUID, Event> events;
	private static final int SAVE_FILE_LENGTH = 10;

	public EventManager() {
		events = new HashMap<>();
		loadEvents();
	}

	protected void loadEvents() {
		for (List<String> lines : loadFiles("/Data/My Events")) {
			Event event = new Event(lines.get(0), lines);
		}
	}

	@Override
	protected boolean isFileValid(List<String> lines) {
		return lines.size() == SAVE_FILE_LENGTH && lines.get(0).length() > 0 &&
		 lines.get(1).length() > 0 && lines.get(2).length() > 0 && lines.get(3).length() > 0; // id, event name, start date and end date is required
	}

	@Override
	public void add(Savable savable) {
		if (savable instanceof Event) {
			Event event = (Event)savable;
			this.events.put(event.getId(), event);
		}
	}

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
}
