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
	private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ENGLISH);;

	private final UUID id;
	private String taskName;
	private Date dueDateTime;
	private boolean taskStatus;
	private String taskNotes;

	public Task(String fileName, List<String> lines) {
		this.id = UUID.fromString(fileName);
		this.taskName = lines.get(1);
		try {
			this.dueDateTime = this.dateFormat.parse(lines.get(2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.taskStatus = Boolean.parseBoolean(lines.get(3));
		this.taskNotes = lines.get(4);
	}

	public Task() {
		this.id = UUID.randomUUID();
		this.taskStatus = false;
	}

	public UUID getId() {
		return id;
	}

	public String getTaskName() {
		return taskName;
	}

	public Date getDueDateTime() {
		return dueDateTime;
	}

	public String getDueDateTimeString() {
		return dateFormat.format(dueDateTime);
	}

	public boolean getTaskStatus() {
		return taskStatus;
	}

	public String getTaskStatusString() {
		if(taskStatus == true) {
			return "Done";
		}
		else {
			return "Incomplete";
		}
	}

	public String getTaskNotes() {
		return taskNotes;
	}

	public void setTaskName(String newName) {
		taskName = newName;
	}

	public void setDueDateTime(String newDueDate) {
		if(newDueDate.length() > 0) {
			try
			{
				dueDateTime = dateFormat.parse(newDueDate);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void setTaskStatus(boolean newStatus) {
		taskStatus = newStatus;
	}

	public void setTaskNotes(String newNotes) {
		taskNotes = newNotes;
	}

	/**
	 * Saves all task data to file
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
				writer.println(this.dateFormat.format(dueDateTime));
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

	//Overrides the toString method. Used in the GUI list.
	@Override
	public String toString() {
		return taskName;
	}
}
