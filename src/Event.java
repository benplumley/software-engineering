import java.io.File;
import java.util.Date;
import java.util.UUID;

public class Event {

	// this class will be instantiated to create a unique event. Recurring events instantiate only
	// once for the whole series.
	
    private UUID id;
	private String eventName;
	private String location; //An arbitary 'location' of the event, set by the user.
	private Date startDate;
	private Date endDate;
	
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
