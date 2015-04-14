import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

public class TaskManager extends Manager {

	private final Map<UUID, Task> tasks;
	private static final int SAVE_FILE_LENGTH = 7;

	public TaskManager() {
		this.tasks = new HashMap<>();

		loadTasks();
	}

	protected void loadTasks() {
		for (List<String> lines : loadFiles("/Data/My Tasks")) {
			Task task = new Task(lines.get(0), lines);

			this.tasks.put(task.getId(), task); //getID undefined for type task
		}
	}

	@Override
	protected boolean isFileValid(List<String> lines) {
		return lines.size() == SAVE_FILE_LENGTH && lines.get(0).length() > 0 && lines.get(1).length() > 0; // id and name is required
	}
}
