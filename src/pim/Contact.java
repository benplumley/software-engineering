package pim;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Contact implements Savable {

	// this class will be instantiated to create a unique contact in the address book.

	private final UUID id;
	private String firstName;
	private String surname;
	private String mobileNumber; // phone numbers must be strings to preserve leading zeroes
	private String homeNumber;
	private String workNumber;
	private String email;
	private String group;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postcode;
	private String notes;

	public Contact() {
		this.id = UUID.randomUUID();
	}

	/**
	 * Constructs the Contact class from the lines provided.
	 * @param fileName Name of the file to be loaded
	 * @param lines Contents of the file to be loaded
	 */
	public Contact(String fileName, List<String> lines) {
		this.id = UUID.fromString(fileName);
		this.firstName = lines.get(1);
		this.surname = lines.get(2);
		this.mobileNumber = lines.get(3);
		this.homeNumber = lines.get(4);
		this.workNumber = lines.get(5);
		this.email = lines.get(7);
		this.group = lines.get(8);
		this.addressLine1 = lines.get(9);
		this.addressLine2 = lines.get(10);
		this.city = lines.get(11);
		this.postcode = lines.get(12);
		this.notes = lines.get(13);
	}

	/**
	 * Constructs the Contact class with given information
	 * @param firstName First name of the contact
	 * @param surname Surname of the contact
	 * @param mobileNumber Mobile Number for the contact
	 * @param homeNumber Home Number for the contact
	 * @param workNumber Work Number for the contact
	 * @param email Email for the contact
	 * @param group Group for the contact
	 * @param addressLine1 Address Line 1 for the contact
	 * @param addressLine2 Address Line 2 for the contact
	 * @param city City for the contact
	 * @param postcode Postcode for the contact
	 * @param notes Notes for the contact
	 */
	public Contact(String firstName, String surname, String mobileNumber, String homeNumber, String workNumber,
					String email, String group, String addressLine1, String addressLine2, String city, String postcode, String notes) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.surname = surname;
		this.mobileNumber = mobileNumber;
		this.homeNumber = homeNumber;
		this.workNumber = workNumber;
		this.email = email;
		this.group = group;
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
	 * Gets the contact's full name
	 * @return Full name of the contact
	 */
	public String getFullName() {
		return this.firstName + " " + this.surname;
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

	public String getWorkNumber() {
		return this.workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Saves all contact data to file
	 */
	@Override
	public void addOrUpdate() {
		try {
			File file = new File("Data/My Contacts/" + this.id.toString());
			if (!file.exists()) {
				file.createNewFile();

				PIM.getContactManager().add(this);
			}

			try (PrintWriter writer = new PrintWriter(file)) {
				writer.println(this.id.toString());
				writer.println(this.firstName);
				writer.println(this.surname);
				writer.println(this.mobileNumber);
				writer.println(this.homeNumber);
				writer.println(this.email);
				writer.println(this.group);
				writer.println(this.addressLine1);
				writer.println(this.addressLine2);
				writer.println(this.city);
				writer.println(this.postcode);
				writer.println(this.notes);
			}
		}
		catch (IOException ex) {

		}
	}

	/**
	 * Deletes the contact data from file
	 */
	@Override
	public void delete() {
		File file = new File("Data/My Contacts/" + this.id.toString());
		file.delete();

		PIM.getContactManager().remove(this);
	}

	@Override
	public String toString() {
		return getFullName();
	}
}
