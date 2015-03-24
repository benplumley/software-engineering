import java.io.File;
import java.util.UUID;

public class Contact {

	// this class will be instantiated to create a unique contact in the address book.

	private final UUID id;
	private String firstName;
	private String surname;
	private String mobileNumber; // phone numbers must be strings to preserve leading zeroes
	private String homeNumber;
	private String workNumber;
	private List<String> otherNumbers;
	private List<String> emails;
	private List<Object> groups; // todo this
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postcode;
	private String notes;

	/**
	 * Constructs the Contact class from a given file
	 * @param saveFile File to load from
	 */
	public Contact(String fileName, List<String> lines) {
		this.id = UUID.fromString(saveFile.getName());

	}

	/**
	 * Constructs the Contact class with a given unique ID
	 * @param uniqueID ID of the contact
	 */
	public Contact(UUID uniqueID, String firstName, String surname, String mobileNumber, String homeNumber, String workNumber, List<String> otherNumbers,
					List<String> emails, List<Object> groups, String addressLine1, String addressLine2, String city, String postcode, String notes) {
		this.id = uniqueID;
		this.firstName = firstName;
		this.surname = surname;
		this.mobileNumber = mobileNumber;
		this.homeNumber = homeNumber;
		this.workNumber = workNumber;
		this.otherNumbers = otherNumbers;
		this.emails = emails;
		this.groups = groups; // todo this
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.postcode = postcode;
		this.notes = notes;
	}

	/**
	 * Gets the unique ID
	 * @return UUID The unique ID linked to the contact
	 */
	public UUID getId() {
		return this.id;
	}

	/**
	 * Gets the first name
	 * @return String the contact's first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name
	 * @param value First name to set
	 */
	public void setFirstName(String value) {
		this.firstName = value;
	}

	/**
	 * Gets the surname
	 * @return String the contact's surname
	 */
	public String getSurname() {
		return this.surname;
	}

	/**
	 * Sets the surname
	 * @param value Surname to set
	 */
	public void setSurname(String value) {
		this.surname = value;
	}

	/**
	 * Gets the mobile number
	 * @return String the contact's mobile number
	 */
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	/**
	 * Sets the mobile number
	 * @param value Mobile number to set
	 */
	public void setMobileNumber(String value) {
		this.mobileNumber = value;
	}

	/**
	 * Gets the home number
	 * @return String the contact's home number
	 */
	public String getHomeNumber() {
		return this.homeNumber;
	}

	/**
	 * Sets the home number
	 * @param value Home number to set
	 */
	public void setHomeNumber(String value) {
		this.homeNumber = value;
	}

}