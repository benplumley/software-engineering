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

public class TaskPanel extends JPanel implements ActionListener{
  private JPanel listPanel;
	private AddTaskPanel addPanel;

	private JList<Task> taskList;

	private JButton addButton;
	private JButton viewButton;

  public TaskPanel(){
    initComponents();
  }

  public void initComponents() {
		initListPanel();

		this.listPanel.setVisible(true);
	}

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

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(this.addButton);
		buttonPanel.add(this.viewButton);

		this.taskList.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if (taskList.getSelectedIndex() == -1)
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
		this.listPanel.add(this.taskList);

		add(this.listPanel);
		this.listPanel.setVisible(false);
	}

  @Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().split(" ")[0];

		String[] params = e.getActionCommand().replace(command, "").split(" ");

		Task selectedTask = (Task)this.taskList.getSelectedValue();

		switch (command) {
			case "HOME":
				showListPanel();
				break;

			case "ADD":
				showAddPanel();
				break;

			case "VIEW":
				showViewPanel(selectedTask.getId());
				break;

			case "EDIT":
				showEditPanel(selectedTask.getId());
				break;

			case "DELETE":
				Task task = PIM.getTaskManager().getTask(UUID.fromString(params[0]));

				task.delete();
				break;
		}
	}

	private DefaultListModel<Task> getListModel() {
		DefaultListModel<Task> listModel = new DefaultListModel<>();

		for (Task task : PIM.getTaskManager().getTasks()) {
			listModel.addElement(task);
		}

		return listModel;
	}

	public void updateTaskList() {
		Container c = this.taskList.getParent();
		c.remove(this.taskList);
		this.taskList.setModel(getListModel());
		this.viewButton.setEnabled(false);
		c.add(this.taskList);
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
		this.addPanel = new AddTaskPanel();

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
			this.addPanel = new AddTaskPanel(PIM.getTaskManager().getTask(id));
			this.addPanel.lockElements();
			this.listPanel.setVisible(false);
			add(addPanel);
			this.addPanel.setVisible(true);
			this.listPanel.setVisible(false);
		}
	}
}
