package pim.gui.tasks;

import pim.Task;
import pim.PIM;
import pim.gui.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskPanel extends JPanel implements ActionListener {
  private Task task;

  private JTextField nameField;
  private JTextField dueDateField;
  private JTextField notesField;
  private JTextField statusField;

  private JButton addButton;
	private JButton editButton;
	private JButton cancelButton;

  public AddTaskPanel() {
		super(new GridBagLayout());
		this.task = new Task();
		this.nameField = new JTextField();
		this.dueDateField = new JTextField();
		this.notesField = new JTextField();
    this.statusField = new JTextField();

    this.nameField.addActionListener(this);
		this.nameField.setActionCommand("ADD");
		this.dueDateField.addActionListener(this);
		this.dueDateField.setActionCommand("ADD");
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

    JPanel pane = new JPanel(new GridLayout(6, 0));
		pane.setPreferredSize(new Dimension(180, 550));
    pane.add(nameLabel);
    pane.add(nameField);
    pane.add(dueLabel);
    pane.add(dueDateField);
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
		scrollPane.setMaximumSize(new Dimension(200, 270));
		scrollPane.setMinimumSize(new Dimension(200, 270));
		scrollPane.setPreferredSize(new Dimension(200, 270));
		add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		unlockElements();
	}

  public AddTaskPanel(Task task) {
		this();
		this.task = task;

		this.nameField.setText(task.getTaskName());
		this.dueDateField.setText(task.getDueDateTimeString());
		this.statusField.setText(task.getTaskStatusString());
  }

  @Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand()) {
			case "ADD":
				this.task.setTaskName(this.nameField.getText());
				this.task.setDueDateTime(this.dueDateField.getText());
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
		this.dueDateField.setEnabled(false);
		this.notesField.setEnabled(false);
  }

  public void unlockElements() {
		this.nameField.setEnabled(true);
		this.dueDateField.setEnabled(true);
		this.notesField.setEnabled(true);
  }
}
