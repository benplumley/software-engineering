package pim.gui;

import pim.Contact;
import pim.QOTD;
import pim.gui.contacts.ContactsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI extends JFrame implements ActionListener {

	private JPanel homePanel;
	private TaskPanel taskPanel;
	private ContactsPanel contactsPanel;

	public GUI() {
		initGUI();
	}

	public ContactsPanel getContactsPanel() {
		return this.contactsPanel;
	}

	public void initGUI() {
		setTitle("PIM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 280);
		setResizable(true);

		this.homePanel = new JPanel();
		this.homePanel.setLayout(new BoxLayout(this.homePanel, BoxLayout.PAGE_AXIS));
		JButton contactButton = new JButton("Contacts");
		contactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		contactButton.setActionCommand("CONTACTS");
		contactButton.addActionListener(this);

		JButton taskButton = new JButton("Tasks");
		taskButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskButton.setActionCommand("TASKS");
		taskButton.addActionListener(this);

		JLabel quoteTodayLabel = new JLabel("<html><div style='text-align: center;'>" + QOTD.getTodaysQuote() + "</div></html>");
		quoteTodayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.homePanel.add(taskButton);
		this.homePanel.add(contactButton);
		this.homePanel.add(quoteTodayLabel);

		this.taskPanel = new TaskPanel();
		this.contactsPanel = new ContactsPanel();
		add(taskPanel);
		add(contactsPanel);
		add(homePanel);

		this.taskPanel.setVisible(false);
		this.contactsPanel.setVisible(false);
		this.homePanel.setVisible(true);
		setVisible(true);
	}

	public void displayHome() {
		this.taskPanel.setVisible(false);
		this.contactsPanel.setVisible(false);
		this.homePanel.setVisible(true);

		repaint();
	}

	private void displayContacts() {
		this.homePanel.setVisible(false);
		this.taskPanel.setVisible(false);
		this.contactsPanel.setVisible(true);
	}

	private void displayTasks() {
		this.homePanel.setVisible(false);
		this.contactsPanel.setVisible(false);
		this.taskPanel.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "CONTACTS":
				displayContacts();
				break;

			case "TASKS":
				displayTasks();
				break;
		}
		repaint();
	}
}
