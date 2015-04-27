package pim.gui;

import pim.Contact;
import pim.PIM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactsPanel extends JPanel implements ActionListener
{
	private JList contactsList;
	public ContactsPanel() {
		initComponents();
	}

	public void initComponents() {
		this.contactsList = new JList(getListModel());
		add(this.contactsList);

		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);
		addButton.setActionCommand("ADD");

		JButton viewButton = new JButton("View");
		viewButton.addActionListener(this);
		viewButton.setActionCommand("VIEW");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "ADD":
				// show add panel
				break;

			case "VIEW":
				// show view panel
				break;
		}
	}

	private DefaultListModel getListModel() {
		DefaultListModel listModel = new DefaultListModel();

		for (Contact contact : PIM.getContactManager().getContacts()) {
			listModel.addElement(contact.getFullName());
		}

		return listModel;
	}
}
