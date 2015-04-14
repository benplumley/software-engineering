import java.io.File;
import java.util.UUID;
import java.util.Date;
import java.io.*;

public class Task implements Savable {
	private final UUID id;
	private String taskName;
	private Date dueDateTime;
	private boolean taskStatus;
	private String taskNotes;

	// this class will be instantiated to create a unique coursework/homework task.
	public Contact(String fileName, List<String> lines) {
		this.id = UUID.fromString(fileName);
		this.taskName = lines.get(1);
		this.dueDateTime = lines.get(2);
		this.taskStatus = Boolean.parseBoolean(lines.get(3));
		this.taskNotes = lines.get(4);
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

	@Override
	public void addOrUpdate() {
		try {
			File file = new File("/Data/My Tasks/" + this.id.toString());
			if (!file.exists()) {
				file.createNewFile();
			}

			try (PrintWriter writer = new PrintWriter(file)) {
				writer.println(this.id.toString());
				writer.println(this.taskName.toString());
				writer.println(this.dueDateTime.toString());
				writer.println(this.taskStatus.toString());
				writer.println(this.taskNotes.toString());
			}
		}
		catch (IOException ex) {

		}
	}

	@Override
	public void delete() {
		try {
			File file = new File("/Data/My Tasks/" + this.id.toString());
			file.delete();
		}
		catch (IOException ex) {

		}
	}
}
