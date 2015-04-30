package pim;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Task implements Savable {
	private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);;

	private final UUID id;
	private String taskName;
	private Date dueDate;
	private boolean taskStatus;
	private String taskNotes;

	/**
	 * Constructs the Task class with data
	 * @param fileName Name of the file (The ID)
	 * @param lines Lines within the file
	 */
	public Task(String fileName, List<String> lines) {
		this.id = UUID.fromString(fileName);
		this.taskName = lines.get(1);
		try {
			this.dueDate = this.dateFormat.parse(lines.get(2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.taskStatus = Boolean.parseBoolean(lines.get(3));
		this.taskNotes = lines.get(4);
	}

	/**
	 * Constructs the Task class assigning a random UUID for ID
	 */
	public Task() {
		this.id = UUID.randomUUID();
		this.taskStatus = false;
	}

	/**
	 * Gets the task's ID
	 * @return Task ID
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Gets the task's name
	 * @return Task Name
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * Gets the Task's due date
	 * @return Task due date
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Gets the task status
	 * @return Task status
	 */
	public boolean getTaskStatus() {
		return taskStatus;
	}

	/**
	 * Gets the task status string
	 * @return Task status string
	 */
	public String getTaskStatusString() {
		return this.taskStatus ? "Complete" : "Incomplete";
	}

	/**
	 * Gets the task notes
	 * @return Task notes
	 */
	public String getTaskNotes() {
		return taskNotes;
	}

	/**
	 * Sets the task's name
	 * @param newName Task name to set
	 */
	public void setTaskName(String newName) {
		taskName = newName;
	}

	/**
	 * Sets the task's due date
	 * @param date Task due date to set
	 */
	public void setDueDate(Date date) {
		dueDate = date;
	}

	/**
	 * Sets the task's status
	 * @param newStatus Task status to set
	 */
	public void setTaskStatus(boolean newStatus) {
		taskStatus = newStatus;
	}

	/**
	 * Sets the task notes
	 * @param newNotes Task nots to set
	 */
	public void setTaskNotes(String newNotes) {
		taskNotes = newNotes;
	}

	/**
	 * Saves all the task's data to a file
	 * Creates the file if it doesn't already exist
	 */
	@Override
	public void addOrUpdate() {
		try {
			File file = new File("Data/My Tasks/" + this.id.toString());
			if (!file.exists()) {
				file.createNewFile();

				PIM.getTaskManager().add(this);
			}

			try (PrintWriter writer = new PrintWriter(file)) {
				writer.println(this.id.toString());
				writer.println(this.taskName.toString());
				writer.println(this.dueDate != null ? this.dateFormat.format(dueDate) : "");
				writer.println(this.taskStatus);
				writer.println(this.taskNotes.toString());
			}
		}
		catch (IOException ex) {

		}
	}

	/**
	 * Deletes all task data from file
	 */
	@Override
	public void delete() {
		File file = new File("Data/My Tasks/" + this.id.toString());
		file.delete();

		PIM.getTaskManager().remove(this);
	}

	/**
	 * Overrides toString for JList on TasksPanel
	 * @return String of task name
	 */
	@Override
	public String toString() {
		return this.taskName + " - " + this.dateFormat.format(this.dueDate);
	}
}
