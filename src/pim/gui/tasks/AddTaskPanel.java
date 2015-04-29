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
	private JTextField statusField;

	private JButton addButton;
	private JButton editButton;
	private JButton cancelButton;

	public AddTaskPanel() {
		super(new GridBagLayout());
		this.task = new Task();
		this.nameField = new JTextField();
		this.notesField = new JTextField();
		this.statusField = new JTextField();


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
		JLabel dueLabel = new JLabel("Due Date/Time");
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


		JPanel pane = new JPanel(new GridLayout(7, 1));
		pane.setPreferredSize(new Dimension(180, 390));
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
		JScrollPane scrollPane = new JScrollPane(pane);
		scrollPane.setMinimumSize(new Dimension(200, 270));
		scrollPane.setPreferredSize(new Dimension(180, 270));
		add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		unlockElements();
	}

	public AddTaskPanel(Task task) {
		this();
		this.task = task;

		this.nameField.setText(task.getTaskName());
		this.statusField.setText(task.getTaskStatusString());


		Calendar c = Calendar.getInstance();
		c.setTime(this.task.getDueDateTime());
		this.dueDatePicker.getModel().setDay(c.get(Calendar.DAY_OF_MONTH));
		this.dueDatePicker.getModel().setMonth(c.get(Calendar.MONTH));
		this.dueDatePicker.getModel().setYear(c.get(Calendar.YEAR));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "ADD":
				if (this.nameField.getText().length() == 0) {
					JOptionPane.showMessageDialog(this, "You need to enter a task name!", "Oops!", JOptionPane.ERROR_MESSAGE);
				}
				this.task.setTaskName(this.nameField.getText());
				this.task.setDueDateTime((Date)this.dueDatePicker.getModel().getValue());
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

	public void lockElements() {
		this.nameField.setEnabled(false);
		this.dueDatePicker.setEnabled(false);
		this.notesField.setEnabled(false);
	}

	public void unlockElements() {
		this.nameField.setEnabled(true);
		this.dueDatePicker.setEnabled(true);
		this.notesField.setEnabled(true);
	}
}