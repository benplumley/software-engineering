package pim.gui.tasks;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import pim.Task;
import pim.PIM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class AddTaskPanel extends JPanel implements ActionListener {
	private Task task;

	private JTextField nameField;
	private JDatePickerImpl dueDatePicker;
	private JTextField notesField;

	private JButton addButton;
	private JButton editButton;
	private JButton cancelButton;

	/**
	 * Constructs the AddTaskPanel class
	 * Initializes all GUI elements to be shown on the panel
	 */
	public AddTaskPanel() {
		super(new GridBagLayout());
		this.task = new Task();
		this.nameField = new JTextField();
		this.notesField = new JTextField();

		Properties prop = new Properties();
		prop.put("text.today", "Today");
		prop.put("text.month", "Month");
		prop.put("text.year", "Year");
		this.dueDatePicker = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), prop), new DateComponentFormatter());

		this.nameField.addActionListener(this);
		this.nameField.setActionCommand("ADD");
		this.notesField.addActionListener(this);
		this.notesField.setActionCommand("ADD");

		JLabel nameLabel = new JLabel("Task Name");
		JLabel dueLabel = new JLabel("Due Date");
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


		JPanel pane = new JPanel(new GridLayout(7, 0));
		pane.setPreferredSize(new Dimension(180, 200));
		pane.add(nameLabel);
		pane.add(nameField);
		pane.add(dueLabel);
		pane.add(dueDatePicker);
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

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		this.dueDatePicker.getModel().setDay(cal.get(Calendar.DAY_OF_MONTH));
		this.dueDatePicker.getModel().setMonth(cal.get(Calendar.MONTH));
		this.dueDatePicker.getModel().setYear(cal.get(Calendar.YEAR));
		this.dueDatePicker.getModel().setSelected(true);

		add(pane);
		unlockElements();
	}

	/**
	 * Constructs the AddTaskPanel class
	 * Sets values from an already existing class to the fields
	 * @param task Task to set values of
	 */
	public AddTaskPanel(Task task) {
		this();
		this.task = task;

		this.nameField.setText(task.getTaskName());
		this.notesField.setText(task.getTaskNotes());

		Calendar c = Calendar.getInstance();
		c.setTime(this.task.getDueDate());
		this.dueDatePicker.getModel().setDay(c.get(Calendar.DAY_OF_MONTH));
		this.dueDatePicker.getModel().setMonth(c.get(Calendar.MONTH));
		this.dueDatePicker.getModel().setYear(c.get(Calendar.YEAR));

		this.addButton.setText("Save");
	}

	/**
	 * Called when an event is fired from the form
	 * Handles all button presses from the form
	 * @param e Event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "ADD":
				Date dueDate = (Date) this.dueDatePicker.getModel().getValue();
				Date dateNow = new Date();

				Calendar dueCal = Calendar.getInstance();
				Calendar nowCal = Calendar.getInstance();
				dueCal.setTime(dueDate);
				nowCal.setTime(dateNow);
				boolean sameDay = dueCal.get(Calendar.YEAR) == nowCal.get(Calendar.YEAR) &&
						dueCal.get(Calendar.DAY_OF_YEAR) == nowCal.get(Calendar.DAY_OF_YEAR);

				if (this.nameField.getText().length() == 0) {
					JOptionPane.showMessageDialog(this, "You need to enter a task name!", "Oops!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if (dueDate == null) {
					JOptionPane.showMessageDialog(this, "You need to set a due date!", "Oops!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else if (!dueDate.after(new Date()) && !sameDay) {
					JOptionPane.showMessageDialog(this, "You cannot set a date before today!", "Oops!", JOptionPane.ERROR_MESSAGE);
					return;
				}

				this.task.setTaskName(this.nameField.getText());
				this.task.setDueDate(dueDate);
				this.task.setTaskNotes(this.notesField.getText());

				this.task.addOrUpdate();
				PIM.getGui().getTasksPanel().updateTaskList();
				PIM.getGui().getTasksPanel().showListPanel();
				break;

			case "EDIT":
				unlockElements();
				break;

			case "CANCEL":
				PIM.getGui().getTasksPanel().showListPanel();
				break;
		}
	}

	/**
	 * Locks all GUI input elements on the form, disables editing of values
	 */
	public void lockElements() {
		this.nameField.setEnabled(false);
		this.dueDatePicker.setEnabled(false);
		this.notesField.setEnabled(false);

		this.addButton.setVisible(false);
		this.editButton.setVisible(true);
	}

	/**
	 * Unlocks all GUI input elements on the form, enables editing of values
	 */
	public void unlockElements() {
		this.nameField.setEnabled(true);
		this.dueDatePicker.setEnabled(true);
		this.notesField.setEnabled(true);

		this.addButton.setVisible(true);
		this.editButton.setVisible(false);
	}
}