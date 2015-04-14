import java.io.File;
import java.util.Date;
import java.util.UUID;

public class Event {

	// this class will be instantiated to create a unique event. Recurring events instantiate only
	// once for the whole series.
	DateFormat format;
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
		format = new SimpleDateFormat("dd, MM, yyyy, hh, mm", Locale.ENGLISH);
		this.id = UUID.fromString(fileName);
		this.eventName = lines.get(1);
		this.startDate= format.parse(lines.get(2));
		this.endDate = format.parse(lines.get(3));
		this.location = lines.get(4);
		this.attendees = lines.get(5);
		this.category = lines.get(6);
		this.notes = lines.get(7);
	}

	/**
	 * Construct new event with specified id.
	 * @param uid UUID to set as the id.
	 */
	public Event(String eventName,String startDate,String endDate,String location,String attendees,String category,String notes) {
		format = new SimpleDateFormat("dd, MM, yyyy, hh, mm", Locale.ENGLISH);
		this.id = UUID.randomUID();
		this.eventName = eventName;
		this.startDate= format.parse(startDate);
		this.endDate = format.parse(endDate);
		this.location = location;
		this.attendees = attendees;
		this.category = category;
		this.notes = notes;
	}

	//Accessors and mutators.

	/**
	 * Set the location.
	 * @param locationSet Location string to set.
	 */
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
	 * @param setStartDate start date to set.
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
}
