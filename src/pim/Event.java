package pim;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Event implements Savable {

	// this class will be instantiated to create a unique event. Recurring events instantiate only
	// once for the whole series.
	private final DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	private final UUID id;
	private String eventName;
	private Date startDate;
	private String location; //An arbitary 'location' of the event, set by the user.
	private String notes;
	private String category;

	public Event() {
		this.id = UUID.randomUUID();
	}

	public Event(String fileName, List<String> lines) {
		this.id = UUID.fromString(fileName);
		this.eventName = lines.get(1);
		try {
			this.startDate= format.parse(lines.get(2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.location = lines.get(3);
		this.category = lines.get(4);
		this.notes = lines.get(5);
	}

	/**
	 * Gets the unique ID
	 * @return Unique ID
	 */
	public UUID getId()
	{
		return this.id;
	}

	public void setNote(String noteSet){
		notes = noteSet;
	}

	public String getNotes(){
		return notes;
	}

	public void setCategory(String categorySet){
		category = categorySet;
	}

	public String getCategory(){
		return category;
	}

	public void setLocation(String locationSet){
		location = locationSet;
	}
	/**
	 * Get the location.
	 * @return String location of event.
	 */
	public String getLocation(){
		return location;
	}
	/**
	 * Set the name.
	 * @param nameSet Name to set.
	 */
	public void setName(String nameSet){
		eventName = nameSet;
	}
	/**
	 * Get the name.
	 * @return String name of event.
	 */
	public String getName(){
		return eventName;
	}
	/**
	 * Set the start date.
	 * @param setStartDate start date to set.
	 */
	public void setStartDate(Date setStartDate){
		startDate = setStartDate;
	}
	/**
	 * Get the start date.
	 * @return Start Date of the event.
	 */
	public Date getStartDate(){
		return startDate;
	}

	/**
	 * Saves all event data to file
	 */
	@Override
	public void addOrUpdate() {
		try {
			File file = new File("Data/My Events/" + this.id.toString());
			if (!file.exists()) {
				file.createNewFile();
				PIM.getEventManager().add(this);
			}

			try (PrintWriter writer = new PrintWriter(file)) {
				writer.println(this.id.toString());
				writer.println(this.eventName);
				writer.println(this.format.format(startDate));
				writer.println(this.location);
				writer.println(this.category);
				writer.println(this.notes);
			}
		}
		catch (IOException ex) {

		}
	}

	/**
	 * Deletes all event data from file
	 */
	@Override
	public void delete() {
		File file = new File("Data/My Events/" + this.id.toString());
		file.delete();

		PIM.getEventManager().remove(this);
	}

	@Override
	public String toString() {
		return this.eventName + " " + this.format.format(this.startDate);
	}
}
