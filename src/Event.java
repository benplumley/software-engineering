import java.io.File;
import java.util.Date;
import java.util.UUID;

public class Event {

	// this class will be instantiated to create a unique event. Recurring events instantiate only
	// once for the whole series.
	
    private UUID id;
	private String eventName;
	private String location; //An arbitary 'location' of the event, set by the user.
	private Date date;
	
	//Constructors

	/**
	 * Construct new event given a file.
	 * @param saveFile File to load.
	 */
	public Event(File saveFile) {
		id =  UUID.fromString(saveFile.getName());
	}
	/**
	 * Construct new event with specified id.
	 * @param uid UUID to set as the id.
	 */
	public Event(UUID uid) {
		id = uid;
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
	 * Set the date.
	 * @param setDate date to set.
	 */
	public void setDate(Date setDate){
		date = setDate;
	}
	/**
	 * Get the date.
	 * @return Date of the event.
	 */
	public Date getDate(){
		return date;
	}
}
