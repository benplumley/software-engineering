import javax.swing.*;
import java.awt.*;
public class GUI {

	public GUI() {
		initGUI();
	}

	public void initGUI() {
		JFrame guiFrame = new JFrame("PIM");
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setSize(142,198);
		guiFrame.setVisible(true);
		JPanel homePanel = new JPanel();
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.PAGE_AXIS));
		JButton contactButton = new JButton("Contacts");
		JButton taskButton = new JButton("Tasks");
		homePanel.add(contactButton);
		contactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		homePanel.add(taskButton);
		taskButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		guiFrame.add(homePanel);
		homePanel.setVisible(true);
	}

}
