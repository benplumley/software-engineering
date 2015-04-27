import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class TaskManager extends Manager {
	private final List<Task> taskList;
	private final Map<UUID, Task> tasks;
	private static final int SAVE_FILE_LENGTH = 7;

	public TaskManager() {
		this.tasks = new HashMap<>();
		this.taskList = new ArrayList<Task>();
		generateDirectories();
		loadTasks();
	}

	protected void loadTasks() {
		for (List<String> lines : loadFiles("Data/My Tasks")) {
			Task task = new Task(lines.get(0), lines);
			this.taskList.add(task);
			this.tasks.put(task.getId(), task);
		}
	}

	@Override
	public void generateDirectories() {
		File file = new File("Data/My Tasks/");

		if (!file.exists()) {
			file.mkdirs();
		}
	}

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

	public Task getTask(UUID id) {
		return this.tasks.get(id);
	}

	public List<Task> getTaskList() {
		return this.taskList;
	}
}
