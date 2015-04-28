package pim.gui.contacts;

import pim.Contact;
import pim.PIM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddContactPanel extends JPanel implements ActionListener
{
	private Contact contact;

	private JTextField firstNameField;
	private JTextField surnameField;
	private JTextField mobileField;
	private JTextField homeNumberField;
	private JTextField workNumberField;
	private JTextField emailField;
	private JTextField groupField;
	private JTextField addressLine1Field;
	private JTextField addressLine2Field;
	private JTextField cityField;
	private JTextField postcodeField;
	private JTextField notesField;

	private JButton addButton;
	private JButton editButton;
	private JButton cancelButton;

	public AddContactPanel() {
		super(new GridLayout(13, 1));
		this.contact = new Contact();
		this.firstNameField = new JTextField();
		this.surnameField = new JTextField();
		this.mobileField = new JTextField();
		this.homeNumberField = new JTextField();
		this.workNumberField = new JTextField();
		this.emailField = new JTextField();
		this.groupField = new JTextField();
		this.addressLine1Field = new JTextField();
		this.addressLine2Field = new JTextField();
		this.cityField = new JTextField();
		this.postcodeField = new JTextField();
		this.notesField = new JTextField();

		this.firstNameField.addActionListener(this);
		this.firstNameField.setActionCommand("ADD");
		this.surnameField.addActionListener(this);
		this.surnameField.setActionCommand("ADD");
		this.mobileField.addActionListener(this);
		this.mobileField.setActionCommand("ADD");
		this.homeNumberField.addActionListener(this);
		this.homeNumberField.setActionCommand("ADD");
		this.workNumberField.addActionListener(this);
		this.workNumberField.setActionCommand("ADD");
		this.emailField.addActionListener(this);
		this.emailField.setActionCommand("ADD");
		this.groupField.addActionListener(this);
		this.groupField.setActionCommand("ADD");
		this.addressLine1Field.addActionListener(this);
		this.addressLine1Field.setActionCommand("ADD");
		this.addressLine2Field.addActionListener(this);
		this.addressLine2Field.setActionCommand("ADD");
		this.cityField.addActionListener(this);
		this.cityField.setActionCommand("ADD");
		this.postcodeField.addActionListener(this);
		this.postcodeField.setActionCommand("ADD");
		this.notesField.addActionListener(this);
		this.notesField.setActionCommand("ADD");

		JLabel firstNameLabel = new JLabel("First Name");
		JLabel surnameLabel = new JLabel("Surname");
		JLabel mobileLabel = new JLabel("Mobile Number");
		JLabel homeNumberLabel = new JLabel("Home Number");
		JLabel workNumberLabel = new JLabel("Work Number");
		JLabel emailLabel = new JLabel("Email");
		JLabel groupLabel = new JLabel("Group");
		JLabel address1Label = new JLabel("Address Line 1");
		JLabel address2Label = new JLabel("Address Line 2");
		JLabel cityLabel = new JLabel("City");
		JLabel postcodeLabel = new JLabel("Postcode");
		JLabel notesLabel = new JLabel("Notes");

		this.addButton = new JButton("Add");
		this.addButton.addActionListener(this);
		this.addButton.setActionCommand("ADD");

		this.cancelButton = new JButton("Cancel");
		this.cancelButton.addActionListener(this);
		this.cancelButton.setActionCommand("CANCEL");

		this.editButton = new JButton("Edit");
		this.editButton.addActionListener(this);
		this.editButton.setActionCommand("EDIT");

		add(firstNameLabel);
		add(firstNameField);
		add(surnameLabel);
		add(surnameField);
		add(mobileLabel);
		add(mobileField);
		add(homeNumberLabel);
		add(homeNumberField);
		add(workNumberLabel);
		add(workNumberField);
		add(emailLabel);
		add(emailField);
		add(groupLabel);
		add(groupField);
		add(address1Label);
		add(addressLine1Field);
		add(address2Label);
		add(addressLine2Field);
		add(cityLabel);
		add(cityField);
		add(postcodeLabel);
		add(postcodeField);
		add(notesLabel);
		add(notesField);

		JPanel panel = new JPanel(new GridLayout(1, 2));
		panel.add(this.addButton);
		panel.add(this.cancelButton);
		panel.add(this.editButton);
		add(panel);
	}

	public AddContactPanel(Contact contact) {
		this();
		this.contact = contact;

		this.firstNameField.setText(contact.getFirstName());
		this.surnameField.setText(contact.getSurname());
		this.mobileField.setText(contact.getMobileNumber());
		this.homeNumberField.setText(contact.getHomeNumber());
		this.workNumberField.setText(contact.getWorkNumber());
		this.emailField.setText(contact.getEmail());
		this.groupField.setText(contact.getGroup());
		this.addressLine1Field.setText(contact.getAddressLine1());
		this.addressLine2Field.setText(contact.getAddressLine2());
		this.cityField.setText(contact.getCity());
		this.postcodeField.setText(contact.getPostcode());
		this.notesField.setText(contact.getNotes());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand()) {
			case "ADD":
				this.contact.setFirstName(this.firstNameField.getText());
				this.contact.setSurname(this.surnameField.getText());
				this.contact.setWorkNumber(this.workNumberField.getText());
				this.contact.setMobileNumber(this.mobileField.getText());
				this.contact.setHomeNumber(this.homeNumberField.getText());
				this.contact.setEmail(this.emailField.getText());
				this.contact.setGroup(this.groupField.getText());
				this.contact.setAddressLine1(this.addressLine1Field.getText());
				this.contact.setAddressLine2(this.addressLine2Field.getText());
				this.contact.setCity(this.cityField.getText());
				this.contact.setPostcode(this.postcodeField.getText());
				this.contact.setNotes(this.notesField.getText());

				this.contact.addOrUpdate();
				PIM.getGui().getContactsPanel().updateContactsList();
				break;

			case "CANCEL":
				if (contact.getFirstName() == null) {
					PIM.getGui().getContactsPanel().showListPanel();
				}
				else {
					PIM.getGui().getContactsPanel().showViewPanel(contact.getId());
				}
				break;
		}
	}

	public void lockElements() {
		this.firstNameField.setEnabled(false);
		this.surnameField.setEnabled(false);
		this.mobileField.setEnabled(false);
		this.homeNumberField.setEnabled(false);
		this.workNumberField.setEnabled(false);
		this.emailField.setEnabled(false);
		this.groupField.setEnabled(false);
		this.addressLine1Field.setEnabled(false);
		this.addressLine2Field.setEnabled(false);
		this.cityField.setEnabled(false);
		this.postcodeField.setEnabled(false);
		this.notesField.setEnabled(false);

		this.addButton.setVisible(false);
		this.cancelButton.setVisible(false);
		this.editButton.setVisible(true);
	}

	public void unlockElements() {
		this.firstNameField.setEnabled(true);
		this.surnameField.setEnabled(true);
		this.mobileField.setEnabled(true);
		this.homeNumberField.setEnabled(true);
		this.workNumberField.setEnabled(true);
		this.emailField.setEnabled(true);
		this.groupField.setEnabled(true);
		this.addressLine1Field.setEnabled(true);
		this.addressLine2Field.setEnabled(true);
		this.cityField.setEnabled(true);
		this.postcodeField.setEnabled(true);
		this.notesField.setEnabled(true);

		this.addButton.setVisible(true);
		this.cancelButton.setVisible(true);
		this.editButton.setVisible(false);
	}
}