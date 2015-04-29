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

	/**
	 * Constructs the Contact class and generates random ID
	 */
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
		this.email = lines.get(6);
		this.group = lines.get(7);
		this.addressLine1 = lines.get(8);
		this.addressLine2 = lines.get(9);
		this.city = lines.get(10);
		this.postcode = lines.get(11);
		this.notes = lines.get(12);
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

	/**
	 * Gets the work number
	 * @return String the contact's work number
	 */
	public String getWorkNumber() {
		return this.workNumber;
	}

	/**
	 * Sets the work number
	 * @param workNumber Work number to set
	 */
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	/**
	 * Gets the email address
	 * @return String the contact's email address
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email address
	 * @param email Email address to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the group
	 * @return String the contact's group
	 */
	public String getGroup() {
		return this.group;
	}

	/**
	 * Sets the contacts group
	 * @param group Contact to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * Gets the contact's address line 1
	 * @return Contact's address line 1
	 */
	public String getAddressLine1() {
		return this.addressLine1;
	}

	/**
	 * Sets the address line 1
	 * @param addressLine1 Address line 1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * Gets the contact's address line 2
	 * @return Contact's address line 2
	 */
	public String getAddressLine2() {
		return this.addressLine2;
	}

	/**
	 * Sets the address line 2
	 * @param addressLine2 Address line 2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * Gets the contacts city
	 * @return Contact's city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * Sets the contact's city
	 * @param city City to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the contact's postcode
	 * @return Contact's postcode
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Set the contact's  postcode
	 * @param postcode Postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Gets the contact's notes
	 * @return Contact's notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Sets the contact's notes
	 * @param notes Notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Saves all the contact's data to a file
	 * Creates the file if it doesn't already exist
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
				writer.println(this.workNumber);
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

	/**
	 * Overrides toString for JList on ContactsPanel
	 * @return String of contacts FULL name
	 */
	@Override
	public String toString() {
		return getFullName();
	}
}
