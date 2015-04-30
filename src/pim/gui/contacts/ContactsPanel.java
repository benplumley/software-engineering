package pim.gui.contacts;

import pim.Contact;
import pim.PIM;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class ContactsPanel extends JPanel implements ActionListener
{
	private JPanel listPanel;
	private AddContactPanel addPanel;

	private JList<Contact> contactsList;

	private JButton addButton;
	private JButton viewButton;
	private JButton deleteButton;

	/**
	 * Constructs the ContactsPanel class
	 */
	public ContactsPanel() {
		initComponents();
	}

	/**
	 * Initializes all components for the ContactsPanel
	 */
	public void initComponents() {
		initListPanel();

		this.listPanel.setVisible(true);
	}

	/**
	 * Constructs the List Panel view to hold all contacts in a JList
	 */
	private void initListPanel() {
		this.listPanel = new JPanel(new GridLayout(2, 0));
		this.contactsList = new JList<>(getListModel());
		this.contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.addButton = new JButton("Add");
		this.addButton.addActionListener(this);
		this.addButton.setActionCommand("ADD");

		this.viewButton = new JButton("View");
		this.viewButton.addActionListener(this);
		this.viewButton.setActionCommand("VIEW");
		this.viewButton.setEnabled(false);

		JButton homeButton = new JButton("Home");
		homeButton.addActionListener(this);
		homeButton.setActionCommand("ROOT");

		this.deleteButton = new JButton("Delete");
		this.deleteButton.addActionListener(this);
		this.deleteButton.setActionCommand("DELETE");
		this.deleteButton.setEnabled(false);

		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		buttonPanel.add(this.addButton, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		buttonPanel.add(this.viewButton, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		buttonPanel.add(this.deleteButton, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		buttonPanel.add(homeButton, constraints);

		this.contactsList.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (contactsList.getSelectedIndex() == -1)
				{
					viewButton.setEnabled(false);
					deleteButton.setEnabled(false);
				}
				else
				{
					viewButton.setEnabled(true);
					deleteButton.setEnabled(true);
				}
			}
		});

		this.listPanel.add(buttonPanel);
		this.listPanel.add(this.contactsList);

		add(this.listPanel);
		this.listPanel.setVisible(false);
	}

	/**
	 * Called when an event is fired from the form
	 * Handles navigation for the ContactsPanel
	 * @param e Event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().split(" ")[0];
		Contact selectedContact = this.contactsList.getSelectedValue();

		switch (command) {
			case "HOME":
				showListPanel();
				break;

			case "ROOT":
				PIM.getGui().displayHome();
				break;

			case "ADD":
				showAddPanel();
				break;

			case "VIEW":
				showViewPanel(selectedContact.getId());
				break;

			case "EDIT":
				showEditPanel();
				break;

			case "DELETE":
				selectedContact.delete();
				updateContactsList();
				repaint();
				break;
		}
	}

	/**
	 * Gets the List Model containing all contacts to be displayed on the form
	 * @return DefaultListModel containing Contact instances
	 */
	private DefaultListModel<Contact> getListModel() {
		DefaultListModel<Contact> listModel = new DefaultListModel<>();

		for (Contact contact : PIM.getContactManager().getContacts()) {
			listModel.addElement(contact);
		}

		return listModel;
	}

	/**
	 * Updates the JList containing all the contacts
	 */
	public void updateContactsList() {
		Container c = this.contactsList.getParent();
		c.remove(this.contactsList);
		this.contactsList.setModel(getListModel());
		this.viewButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
		c.add(this.contactsList);
	}

	/**
	 * Shows the panel with the list of contacts
	 */
	public void showListPanel() {
		this.listPanel.setVisible(true);

		if (this.addPanel != null) {
			this.addPanel.setVisible(false);
			remove(this.addPanel);
			this.addPanel = null;
		}
	}

	/**
	 * Shows the panel with all the fields for adding/editing contacts
	 */
	public void showAddPanel() {
		this.addPanel = new AddContactPanel();

		this.listPanel.setVisible(false);
		add(addPanel);
		this.addPanel.setVisible(true);
	}

	/**
	 * Sets the add panel into edit mode
	 */
	public void showEditPanel() {
		this.addPanel.unlockElements();
	}

	/**
	 * Sets the add panel to display a contact's details and puts it into view mode
	 * @param id ID of the contact to show
	 */
	public void showViewPanel(UUID id) {
		if (this.addPanel != null) {
			this.addPanel.lockElements();
		}
		else {
			this.addPanel = new AddContactPanel(PIM.getContactManager().getContact(id));
			this.addPanel.lockElements();
			this.listPanel.setVisible(false);
			add(addPanel);
			this.addPanel.setVisible(true);
			this.listPanel.setVisible(false);
		}
	}
}
