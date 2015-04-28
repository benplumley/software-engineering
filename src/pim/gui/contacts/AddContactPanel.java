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
		super(new GridBagLayout());
		this.contact = new Contact();
		this.firstNameField = new JTextField(2);
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

		
		JPanel pane = new JPanel(new GridLayout(26, 0));
		pane.setPreferredSize(new Dimension(180, 550));
		pane.add(firstNameLabel);
		pane.add(firstNameField);
		pane.add(surnameLabel);
		pane.add(surnameField);
		pane.add(mobileLabel);
		pane.add(mobileField);
		pane.add(homeNumberLabel);
		pane.add(homeNumberField);
		pane.add(workNumberLabel);
		pane.add(workNumberField);
		pane.add(emailLabel);
		pane.add(emailField);
		pane.add(groupLabel);
		pane.add(groupField);
		pane.add(address1Label);
		pane.add(addressLine1Field);
		pane.add(address2Label);
		pane.add(addressLine2Field);
		pane.add(cityLabel);
		pane.add(cityField);
		pane.add(postcodeLabel);
		pane.add(postcodeField);
		pane.add(notesLabel);
		pane.add(notesField);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		panel.add(this.addButton, c);
		c.gridx = 1;
		panel.add(this.editButton, c);
		c.gridx = 2;
		panel.add(this.cancelButton, c);
		pane.add(panel);
		JScrollPane scrollPane = new JScrollPane(pane);
		scrollPane.setMaximumSize(new Dimension(200, 270));
		scrollPane.setMinimumSize(new Dimension(200, 270));
		scrollPane.setPreferredSize(new Dimension(200, 270));
		add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		unlockElements();
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
				PIM.getGui().getContactsPanel().showListPanel();
				break;

			case "EDIT":
				unlockElements();
				break;

			case "CANCEL":
				PIM.getGui().getContactsPanel().showListPanel();
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
		this.editButton.setVisible(false);
	}
}
