import java.io.File;
import java.util.UUID;
import java.util.Date;

public class Task {
	private final UUID id;
	private String taskName;
	private Date dueDateTime;
	private boolean taskStatus;
	private String taskNotes;

	// this class will be instantiated to create a unique coursework/homework task.

	public Task(File saveFile) {
		this.id = UUID.fromString(saveFile.getName());

	}

	public Task(UUID uniqueID, String name, Date due,String notes) {
		id = uniqueID;
		taskName = name;
		dueDateTime = due;
		taskStatus = false;
		taskNotes = notes;
	}

	public UUID getID() {
		return id;
	}

	public String getTaskName() {
		return taskName;
	}

	public Date getDueDateTime() {
		return dueDateTime;
	}

	public boolean getTaskStatus() {
		return taskStatus;
	}

	public String getTaskNotes() {
		return taskNotes;
	}

	public void setTaskName(String newName) {
		taskName = newName;
	}

	public void setDueDateTime(Date newDueDate) {
		dueDateTime = newDueDate;
	}

	public void setTaskStatus(boolean newStatus) {
		taskStatus = newStatus;
	}

	public void setTaskNotes(String newNotes) {
		taskNotes = newNotes;
	}

}
