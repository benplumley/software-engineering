import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class Event implements Savable {

	// this class will be instantiated to create a unique event. Recurring events instantiate only
	// once for the whole series.
	private final DateFormat format;
	private UUID id;
	private String eventName;
	private Date startDate;
	private Date endDate;
	private String location; //An arbitary 'location' of the event, set by the user.
	private String attendees;
	private String notes;
	private String category;


	//Constructors
	public Event(String fileName, List<String> lines) {
		format = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ENGLISH);
		this.id = UUID.fromString(fileName);
		this.eventName = lines.get(1);
		try {
			this.startDate= format.parse(lines.get(2));
			this.endDate = format.parse(lines.get(3));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.location = lines.get(4);
		this.attendees = lines.get(5);
		this.category = lines.get(6);
		this.notes = lines.get(7);
	}

	/**
	 * Construct new event with specified id.
	 */
	public Event(String eventName,String startDate,String endDate,String location,String attendees,String category,String notes) {
		format = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ENGLISH);
		this.id = UUID.randomUUID();
		this.eventName = eventName;
		try {
			this.startDate= format.parse(startDate);
			this.endDate = format.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.location = location;
		this.attendees = attendees;
		this.category = category;
		this.notes = notes;
	}

	//Accessors and mutators.

	/**
	 * Gets the unique ID
	 * @return Unique ID
	 */
	public UUID getId()
	{
		return this.id;
	}

	/**
	 * Set the location.
	 * @param attendeeSet Location string to set.
	 */
	public void setAttendees(String attendeeSet){
		attendees = attendeeSet;
	}

	public String getAttendees(){
		return attendees;
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
	 * Set the end date.
	 * @param setEndDate end date to set.
	 */
	public void setEndDate(Date setEndDate){
		endDate = setEndDate;
	}
	/**
	 * Get the end date.
	 * @return End Date of the event.
	 */
	public Date getEndDate(){
		return endDate;
	}

	@Override
	public void addOrUpdate() {
		try {
			File file = new File("/Data/My Events/" + this.id.toString());
			if (!file.exists()) {
				file.createNewFile();
				PIM.getEventManager().add(this);
			}

			try (PrintWriter writer = new PrintWriter(file)) {
				writer.println(this.id.toString());
				writer.println(this.eventName);
				writer.println(this.format.format(startDate));
				writer.println(this.format.format(endDate));
				writer.println(this.location);
				writer.println(this.attendees);
				writer.println(this.category);
				writer.println(this.notes);
			}
		}
		catch (IOException ex) {

		}
	}

	@Override
	public void delete() {
		File file = new File("/Data/My Tasks/" + this.id.toString());
		file.delete();

		PIM.getEventManager().remove(this);
	}
}
