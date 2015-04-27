package pim.gui.contacts;

import pim.Contact;
import pim.PIM;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class ContactsPanel extends JPanel implements ActionListener
{
	private JPanel listPanel;
	private AddContactPanel addPanel;
	private JPanel viewPanel;

	private JList contactsList;

	private JButton addButton;
	private JButton viewButton;

	public ContactsPanel() {
		initComponents();
	}

	public void initComponents() {
		initListPanel();

		this.listPanel.setVisible(true);
	}

	private void initListPanel() {
		this.listPanel = new JPanel();
		this.contactsList = new JList(getListModel());
		this.contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.addButton = new JButton("Add");
		this.addButton.addActionListener(this);
		this.addButton.setActionCommand("ADD");
		this.addButton.setEnabled(false);

		this.viewButton = new JButton("View");
		this.viewButton.addActionListener(this);
		this.viewButton.setActionCommand("VIEW");
		this.viewButton.setEnabled(false);

		this.contactsList.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (contactsList.getSelectedIndex() == -1) {
					addButton.setEnabled(false);
					viewButton.setEnabled(false);
				}
				else {
					addButton.setEnabled(true);
					viewButton.setEnabled(true);
				}
			}
		});

		this.listPanel.add(this.addButton);
		this.listPanel.add(this.viewButton);
		this.listPanel.add(this.contactsList);

		add(this.listPanel);
		this.listPanel.setVisible(false);
	}

	private void initViewPanel(UUID id) {
		this.viewPanel = new JPanel();
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("BACK");
		backButton.addActionListener(this);

		JButton editButton = new JButton("Edit");
		editButton.setActionCommand("DELETE " + id.toString());
		editButton.addActionListener(this);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setActionCommand("DELETE " + id.toString());
		deleteButton.addActionListener(this);

		this.viewPanel.add(backButton);
		this.viewPanel.add(editButton);
		this.viewPanel.add(deleteButton);

		add(this.viewPanel);
		this.viewPanel.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().split(" ")[0];

		String[] params = e.getActionCommand().replace(command, "").split(" ");

		Contact selectedContact = (Contact)this.contactsList.getSelectedValue();

		switch (command) {
			case "HOME":
				showListPanel();
				break;

			case "ADD":
				showAddPanel();
				break;

			case "VIEW":
				showViewPanel(UUID.fromString(params[0]));
				break;

			case "EDIT":
				showEditPanel(selectedContact.getId());
				break;

			case "DELETE":
				Contact contact = PIM.getContactManager().getContact(UUID.fromString(params[0]));

				contact.delete();
				break;
		}
	}

	private DefaultListModel<Contact> getListModel() {
		DefaultListModel<Contact> listModel = new DefaultListModel<Contact>();

		for (Contact contact : PIM.getContactManager().getContacts()) {
			listModel.addElement(contact);
		}

		return listModel;
	}

	public void updateContactsList() {
		this.contactsList.setModel(getListModel());
		this.viewButton.setEnabled(false);
		this.addButton.setEnabled(false);
	}

	public void showListPanel() {
		this.listPanel.setVisible(true);

		if (this.viewPanel != null) {
			this.viewPanel.setVisible(false);
			remove(this.viewPanel);
			this.viewPanel = null;
		}
		if (this.addPanel != null) {
			this.addPanel.setVisible(false);
			remove(this.addPanel);
			this.addPanel = null;
		}
	}

	public void showAddPanel() {
		this.addPanel = new AddContactPanel();

		this.listPanel.setVisible(false);
		add(addPanel);
		this.addPanel.setVisible(true);
	}

	public void showEditPanel(UUID id) {
		this.addPanel = new AddContactPanel(PIM.getContactManager().getContact(id));
		this.viewPanel.setVisible(false);

		add(this.addPanel);
		this.addPanel.setVisible(true);
	}

	public void showViewPanel(UUID id) {
		initViewPanel(id);

		this.viewPanel.setVisible(true);
		this.listPanel.setVisible(false);

		if (this.addPanel != null) {
			this.addPanel.setVisible(false);
			remove(this.addPanel);
			this.addPanel = null;
		}
	}
}
