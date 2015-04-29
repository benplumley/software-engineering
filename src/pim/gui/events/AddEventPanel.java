package pim.gui.events;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import pim.Event;
import pim.PIM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class AddEventPanel extends JPanel implements ActionListener {
	private Event event;

	private JTextField eventNameField;
	private JTextField locationField;
	private JTextField notesField;
	private JTextField categoryField;
	private JDatePickerImpl startDatePicker;

	private JButton addButton;
	private JButton editButton;
	private JButton cancelButton;

	/**
	 * Constructs the AddEventPanel class
	 * Initializes and adds all GUI elements to the panel
	 */
	public AddEventPanel() {
		super(new GridBagLayout());
		this.event = new Event();
		this.eventNameField = new JTextField();
		this.locationField = new JTextField();
		this.notesField = new JTextField();
		this.categoryField = new JTextField();

		Properties prop = new Properties();
		prop.put("text.today", "Today");
		prop.put("text.month", "Month");
		prop.put("text.year", "Year");
		this.startDatePicker = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), prop), new DateComponentFormatter());

		this.eventNameField.addActionListener(this);
		this.eventNameField.setActionCommand("ADD");
		this.locationField.addActionListener(this);
		this.locationField.setActionCommand("ADD");
		this.notesField.addActionListener(this);
		this.notesField.setActionCommand("ADD");
		this.categoryField.addActionListener(this);
		this.categoryField.setActionCommand("ADD");

		JLabel eventNameLabel = new JLabel("Event Name");
		JLabel locationLabel = new JLabel("Location");
		JLabel notesLabel = new JLabel("Notes");
		JLabel categoryLabel = new JLabel("Category");
		JLabel startDateLabel = new JLabel("Start Date");;

		this.addButton = new JButton("Add");
		this.addButton.addActionListener(this);
		this.addButton.setActionCommand("ADD");

		this.cancelButton = new JButton("Cancel");
		this.cancelButton.addActionListener(this);
		this.cancelButton.setActionCommand("CANCEL");

		this.editButton = new JButton("Edit");
		this.editButton.addActionListener(this);
		this.editButton.setActionCommand("EDIT");


		JPanel pane = new JPanel(new GridLayout(15, 0));
		pane.setPreferredSize(new Dimension(180, 550));
		pane.add(eventNameLabel);
		pane.add(eventNameField);
		pane.add(locationLabel);
		pane.add(locationField);
		pane.add(notesLabel);
		pane.add(notesField);
		pane.add(categoryLabel);
		pane.add(categoryField);
		pane.add(startDateLabel);
		pane.add(startDatePicker);
		pane.add(new JLabel(""));

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

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		this.startDatePicker.getModel().setDay(cal.get(Calendar.DAY_OF_MONTH));
		this.startDatePicker.getModel().setMonth(cal.get(Calendar.MONTH));
		this.startDatePicker.getModel().setYear(cal.get(Calendar.YEAR));
		this.startDatePicker.getModel().setSelected(true);

		unlockElements();
	}

	/**
	 * Constructs the AddEventPanel class
	 * Sets values for all form elements to edit Event
	 * @param event Event to edit
	 */
	public AddEventPanel(Event event) {
		this();
		this.event = event;

		this.eventNameField.setText(event.getName());
		this.locationField.setText(event.getLocation());
		this.notesField.setText(event.getNotes());
		this.categoryField.setText(event.getCategory());
		Calendar c = Calendar.getInstance();
		c.setTime(this.event.getStartDate());
		this.startDatePicker.getModel().setDay(c.get(Calendar.DAY_OF_MONTH));
		this.startDatePicker.getModel().setMonth(c.get(Calendar.MONTH));
		this.startDatePicker.getModel().setYear(c.get(Calendar.YEAR));
		this.startDatePicker.getModel().setSelected(true);
	}

	/**
	 * Called when an event is fired from the form
	 * Handles any button clicks on the form
	 * @param e Event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "ADD":
				Date startDate = (Date)this.startDatePicker.getModel().getValue();
				if (this.eventNameField.getText().length() == 0) {
					JOptionPane.showMessageDialog(this, "You need to enter an event name!", "Oops!", JOptionPane.ERROR_MESSAGE);
					return;
				}

				this.event.setName(this.eventNameField.getText());
				this.event.setLocation(this.locationField.getText());
				this.event.setNote(this.notesField.getText());
				this.event.setCategory(this.categoryField.getText());
				this.event.setStartDate(startDate);

				this.event.addOrUpdate();
				PIM.getGui().getEventsPanel().updateEventsList();
				PIM.getGui().getEventsPanel().showListPanel();
				break;

			case "EDIT":
				unlockElements();
				break;

			case "CANCEL":
				PIM.getGui().getEventsPanel().showListPanel();
				break;
		}
	}

	/**
	 * Locks all GUI input elements on the form, stops editing of fields
	 */
	public void lockElements() {
		this.eventNameField.setEnabled(false);
		this.locationField.setEnabled(false);
		this.startDatePicker.setEnabled(false);
		this.notesField.setEnabled(false);
		this.categoryField.setEnabled(false);

		this.addButton.setVisible(false);
		this.editButton.setVisible(true);
	}

	/**
	 * Unlocks all GUI input elements on the form, enables editing of fields
	 */
	public void unlockElements() {
		this.eventNameField.setEnabled(true);
		this.locationField.setEnabled(true);
		this.startDatePicker.setEnabled(true);
		this.notesField.setEnabled(true);
		this.categoryField.setEnabled(true);

		this.addButton.setVisible(true);
		this.editButton.setVisible(false);
	}
}
