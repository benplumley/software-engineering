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
		this.listPanel = new JPanel(new GridLayout(2, 0));
		this.contactsList = new JList(getListModel());
		this.contactsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.addButton = new JButton("Add");
		this.addButton.addActionListener(this);
		this.addButton.setActionCommand("ADD");

		this.viewButton = new JButton("View");
		this.viewButton.addActionListener(this);
		this.viewButton.setActionCommand("VIEW");
		this.viewButton.setEnabled(false);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.addButton);
		buttonPanel.add(this.viewButton);

		this.contactsList.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (contactsList.getSelectedIndex() == -1)
				{
					viewButton.setEnabled(false);
				}
				else
				{
					viewButton.setEnabled(true);
				}
			}
		});

		this.listPanel.add(buttonPanel);
		this.listPanel.add(this.contactsList);

		add(this.listPanel);
		this.listPanel.setVisible(false);
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
				showViewPanel(selectedContact.getId());
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
		DefaultListModel<Contact> listModel = new DefaultListModel<>();

		for (Contact contact : PIM.getContactManager().getContacts()) {
			listModel.addElement(contact);
		}

		return listModel;
	}

	public void updateContactsList() {
		Container c = this.contactsList.getParent();
		c.remove(this.contactsList);
		this.contactsList.setModel(getListModel());
		this.viewButton.setEnabled(false);
		c.add(this.contactsList);
	}

	public void showListPanel() {
		this.listPanel.setVisible(true);

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
		this.addPanel.unlockElements();
	}

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
