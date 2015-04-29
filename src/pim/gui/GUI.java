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

	/**
	 * Constructs the GUI class
	 * Initializes all GUI elements to be shown on the frame
	 */
	public GUI() {
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
		this.homePanel.add(eventButton);
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

	/**
	 * Gets the ContactsPanel panel
	 * @return ContactsPanel instance
	 */
	public ContactsPanel getContactsPanel() {
		return this.contactsPanel;
	}

	/**
	 * Gets the TaskPanel panel
	 * @return TaskPanel instance
	 */
	public TaskPanel getTasksPanel() {
		return this.taskPanel;
	}

	/**
	 * Gets the EventsPanel panel
	 * @return EventsPanel instance
	 */
	public EventsPanel getEventsPanel() {
		return this.eventsPanel;
	}

	/**
	 * Displays the Home panel on the frame
	 */
	public void displayHome() {
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "HOME");

		repaint();
	}

	/**
	 * Displays the Contacts panel on the frame
	 */
	private void displayContacts() {
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "CONTACTS");
	}

	/**
	 * Displays the Tasks panel on the frame
	 */
	private void displayTasks() {
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "TASKS");
	}

	/**
	 * Displays the Events panel on the frame
	 */
	private void displayEvents() {
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "EVENTS");
	}

	/**
	 * Called when any event is fired on the frame
	 * Handles all navigation for the home panel
	 * @param e Event
	 */
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
