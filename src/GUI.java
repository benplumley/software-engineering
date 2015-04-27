import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI {
	private static JFrame guiFrame;
	public GUI() {
		initGUI();
	}

	public void initGUI() {
		guiFrame = new JFrame("PIM");
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setSize(128,180);
		guiFrame.setVisible(true);
		guiFrame.setResizable(false);
		final JPanel homePanel = new JPanel();
		homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.PAGE_AXIS));
		JButton contactButton = new JButton("Contacts");
		JButton taskButton = new JButton("Tasks");
		homePanel.add(contactButton);
		contactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		homePanel.add(taskButton);
		taskButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		guiFrame.add(homePanel);
		homePanel.setVisible(true);
		final TaskPanel taskPanel = new TaskPanel();
		guiFrame.add(taskPanel);
		taskPanel.setVisible(false);
		guiFrame.setVisible(true);
		taskButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
									homePanel.setVisible(false);
									taskPanel.setVisible(true);
									guiFrame.repaint();
								}
              });
	}

}
