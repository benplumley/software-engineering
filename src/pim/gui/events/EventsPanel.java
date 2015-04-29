package pim.gui.events;

import pim.*;
import pim.Event;
import pim.gui.contacts.AddContactPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class EventsPanel extends JPanel implements ActionListener {
	private JPanel listPanel;
	private AddEventPanel addPanel;

	private JList<Event> eventsList;

	private JButton addButton;
	private JButton viewButton;

	public EventsPanel() {
		initComponents();
	}

	public void initComponents() {
		initListPanel();

		this.listPanel.setVisible(true);
	}

	private void initListPanel() {
		this.listPanel = new JPanel(new GridLayout(2, 0));
		this.eventsList = new JList<>(getListModel());
		this.eventsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

		this.eventsList.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (eventsList.getSelectedIndex() == -1)
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
		this.listPanel.add(this.eventsList);

		add(this.listPanel);
		this.listPanel.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().split(" ")[0];

		String[] params = e.getActionCommand().replace(command, "").split(" ");

		Event selectedEvent = (Event)this.eventsList.getSelectedValue();

		switch (command) {
			case "HOME":
				showListPanel();
				break;

			case "ADD":
				showAddPanel();
				break;

			case "VIEW":
				showViewPanel(selectedEvent.getId());
				break;

			case "EDIT":
				showEditPanel(selectedEvent.getId());
				break;

			case "DELETE":
				Contact contact = PIM.getContactManager().getContact(UUID.fromString(params[0]));

				contact.delete();
				break;
		}
	}

	private DefaultListModel<Event> getListModel() {
		DefaultListModel<Event> listModel = new DefaultListModel<>();

		for (Event event : PIM.getEventManager().getEvents()) {
			listModel.addElement(event);
		}

		return listModel;
	}

	public void updateEventsList() {
		Container c = this.eventsList.getParent();
		c.remove(this.eventsList);
		this.eventsList.setModel(getListModel());
		this.viewButton.setEnabled(false);
		c.add(this.eventsList);
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
		this.addPanel = new AddEventPanel();

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
			this.addPanel = new AddEventPanel(PIM.getEventManager().getEvent(id));
			this.addPanel.lockElements();
			this.listPanel.setVisible(false);
			add(addPanel);
			this.addPanel.setVisible(true);
			this.listPanel.setVisible(false);
		}
	}
}
