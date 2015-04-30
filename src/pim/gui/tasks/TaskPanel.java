package pim.gui.tasks;

import pim.Task;
import pim.PIM;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class TaskPanel extends JPanel implements ActionListener {
	private JPanel listPanel;
	private AddTaskPanel addPanel;

	private JList<Task> taskList;

	private JButton addButton;
	private JButton viewButton;
	private JButton deleteButton;

	/**
	 * Constructs the TaskPanel class
	 */
	public TaskPanel() {
		initComponents();
	}

	/**
	 * Initializes all GUI elements to be shown on the panel
	 */
	public void initComponents() {
		initListPanel();

		this.listPanel.setVisible(true);
	}

	/**
	 * Initializes the List Panel where all the Tasks are displayed
	 */
	private void initListPanel() {
		this.listPanel = new JPanel(new GridLayout(2, 0));
		this.taskList = new JList<>(getListModel());
		this.taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.addButton = new JButton("Add");
		this.addButton.addActionListener(this);
		this.addButton.setActionCommand("ADD");

		this.viewButton = new JButton("View");
		this.viewButton.addActionListener(this);
		this.viewButton.setActionCommand("VIEW");
		this.viewButton.setEnabled(false);

		JButton homeButton = new JButton("Home");
		homeButton.addActionListener(this);
		homeButton.setActionCommand("ROOT");

		this.deleteButton = new JButton("Delete");
		this.deleteButton.addActionListener(this);
		this.deleteButton.setActionCommand("DELETE");
		this.deleteButton.setEnabled(false);

		JPanel buttonPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		buttonPanel.add(this.addButton, constraints);
		constraints.gridx = 1;
		constraints.gridy = 0;
		buttonPanel.add(this.viewButton, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		buttonPanel.add(this.deleteButton, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		buttonPanel.add(homeButton, constraints);

		this.taskList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (taskList.getSelectedIndex() == -1) {
					viewButton.setEnabled(false);
					deleteButton.setEnabled(false);
				}
				else {
					viewButton.setEnabled(true);
					deleteButton.setEnabled(true);
				}
			}
		});

		this.listPanel.add(buttonPanel);
		this.listPanel.add(this.taskList);

		add(this.listPanel);
		this.listPanel.setVisible(false);
	}

	/**
	 * Called when an event is fired from the form
	 * Handles all navigation for the TaskPanel
	 * @param e Event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().split(" ")[0];

		Task selectedTask = this.taskList.getSelectedValue();

		switch (command) {
			case "HOME":
				showListPanel();
				break;

			case "ROOT":
				PIM.getGui().displayHome();
				break;

			case "ADD":
				showAddPanel();
				break;

			case "VIEW":
				showViewPanel(selectedTask.getId());
				break;

			case "EDIT":
				showEditPanel();
				break;

			case "DELETE":
				selectedTask.delete();
				updateTaskList();
				repaint();
				break;
		}
	}

	/**
	 * Gets the list model containing all tasks to show
	 * @return ListModel of Tasks
	 */
	private DefaultListModel<Task> getListModel() {
		DefaultListModel<Task> listModel = new DefaultListModel<>();

		for (Task task : PIM.getTaskManager().getTasks()) {
			listModel.addElement(task);
		}

		return listModel;
	}

	/**
	 * Updates the JList with the most up to date elements
	 */
	public void updateTaskList() {
		Container c = this.taskList.getParent();
		c.remove(this.taskList);
		this.taskList.setModel(getListModel());
		this.viewButton.setEnabled(false);
		this.deleteButton.setEnabled(false);
		c.add(this.taskList);
	}

	/**
	 * Shows the main List Panel
	 */
	public void showListPanel() {
		this.listPanel.setVisible(true);

		if (this.addPanel != null) {
			this.addPanel.setVisible(false);
			remove(this.addPanel);
			this.addPanel = null;
		}
	}

	/**
	 * Shows the Add panel
	 */
	public void showAddPanel() {
		this.addPanel = new AddTaskPanel();

		this.listPanel.setVisible(false);
		add(addPanel);
		this.addPanel.setVisible(true);
	}

	/**
	 * Sets the Add panel to edit mode
	 */
	public void showEditPanel() {
		this.addPanel.unlockElements();
	}

	/**
	 * Shows the View Panel with pre filled data
	 * @param id ID of the task to show data of
	 */
	public void showViewPanel(UUID id) {
		if (this.addPanel != null) {
			this.addPanel.lockElements();
		}
		else {
			this.addPanel = new AddTaskPanel(PIM.getTaskManager().getTask(id));
			this.addPanel.lockElements();
			this.listPanel.setVisible(false);
			add(addPanel);
			this.addPanel.setVisible(true);
			this.listPanel.setVisible(false);
		}
	}
}