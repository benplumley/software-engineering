package pim;

import java.io.File;
import java.util.*;

public class TaskManager extends Manager {
	private final Map<UUID, Task> tasks;
	private static final int SAVE_FILE_LENGTH = 7;

	public TaskManager() {
		this.tasks = new HashMap<>();
		generateDirectories();
		loadTasks();
	}

	protected void loadTasks() {
		for (List<String> lines : loadFiles("Data/My Tasks")) {
			Task task = new Task(lines.get(0), lines);
			this.tasks.put(task.getId(), task);
		}
	}

	/**
	 * Generates the save directories if they don't already exist
	 */
	@Override
	public void generateDirectories() {
		File file = new File("Data/My Tasks/");

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
		return lines.size() == SAVE_FILE_LENGTH && lines.get(0).length() > 0 && lines.get(1).length() > 0; // id and name is required
	}

	/**
	 * Adds an instance of Task to the map.
	 * This should not be directly called.
	 *
	 * @param savable Task to add
	 */
	@Override
	public void add(Savable savable) {
		if (savable instanceof Task) {
			Task task = (Task)savable;
			this.tasks.put(task.getId(), task);
		}
	}

	/**
	 * Removes an instance of Task from the map.
	 * This should not be directly called.
	 *
	 * @param savable Task to remove
	 */
	@Override
	public void remove(Savable savable) {
		if (savable instanceof Task) {
			Task task = (Task)savable;
			this.tasks.remove(task.getId());
		}
	}

	/**
	 * Gets a specific task by ID
	 * @param id ID of the task to get
	 * @return Task
	 */
	public Task getTask(UUID id) {
		return this.tasks.get(id);
	}

	/**
	 * Gets all valid tasks to be displayed
	 * @return Valid tasks
	 */
	public Collection<Task> getTasks() {
		return this.tasks.values();
	}
}
