package pim.gui;

import pim.gui.TaskPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI extends JFrame implements ActionListener {

	private JPanel homePanel;
	private JPanel taskPanel;

	public GUI() {
		initGUI();
	}

	public void initGUI() {
		setTitle("PIM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 280);
		setVisible(true);
		setResizable(false);

		this.homePanel = new JPanel();
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.PAGE_AXIS));
		JButton contactButton = new JButton("Contacts");
		JButton taskButton = new JButton("Tasks");
		this.homePanel.add(contactButton);
		contactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.homePanel.add(taskButton);
		taskButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(homePanel);
		homePanel.setVisible(true);
		this.taskPanel = new TaskPanel();
		add(taskPanel);
		this.taskPanel.setVisible(false);
		taskButton.addActionListener(this);
	}

	public void displayHome() {
		this.homePanel.setVisible(true);
		this.taskPanel.setVisible(false);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.homePanel.setVisible(false);
		this.taskPanel.setVisible(true);
		repaint();
	}
}
