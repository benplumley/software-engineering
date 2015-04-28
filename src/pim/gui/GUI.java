package pim.gui;

import pim.QOTD;
import pim.gui.contacts.ContactsPanel;
import pim.gui.tasks.TaskPanel;
import pim.gui.events.EventsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI extends JFrame implements ActionListener {

	private JPanel homePanel;
	private TaskPanel taskPanel;
	private ContactsPanel contactsPanel;
	private EventsPanel eventsPanel;

	public GUI() {
		initGUI();
	}

	public ContactsPanel getContactsPanel() {
		return this.contactsPanel;
	}

	public TaskPanel getTasksPanel() {
		return this.taskPanel;
	}

	public EventsPanel getEventsPanel() {
		return this.eventsPanel;
	}

	public void initGUI() {
		setTitle("PIM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 280);
		setResizable(true);
		setLayout(new CardLayout());

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

		JButton eventButton = new JButton("Events");
		eventButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		eventButton.setActionCommand("EVENTS");
		eventButton.addActionListener(this);

		JLabel quoteTodayLabel = new JLabel("<html><div style='text-align: center;'>" + QOTD.getTodaysQuote() + "</div></html>");
		quoteTodayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.homePanel.add(taskButton);
		this.homePanel.add(contactButton);
		this.homePanel.add(quoteTodayLabel);

		this.taskPanel = new TaskPanel();
		this.contactsPanel = new ContactsPanel();
		this.eventsPanel = new EventsPanel();
		add(this.taskPanel, "TASKS");
		add(this.contactsPanel, "CONTACTS");
		add(this.eventsPanel, "EVENTS");
		add(this.homePanel, "HOME");

		this.taskPanel.setVisible(false);
		this.contactsPanel.setVisible(false);
		this.homePanel.setVisible(true);
		setVisible(true);
	}

	public void displayHome() {
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "HOME");

		repaint();
	}

	private void displayContacts() {
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "CONTACTS");
	}

	private void displayTasks() {
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "TASKS");
	}

	private void displayEvents() {
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "EVENTS");
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

			case "EVENTS":
				displayEvents();
				break;
		}
		repaint();
	}
}
