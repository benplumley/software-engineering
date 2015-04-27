package pim.gui;

import pim.PIM;
import pim.Task;
import java.awt.event.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.*;
import java.awt.*;
public class TaskPanel extends JPanel {
  private static JList myTasks;
  private static String panelStatus;
  //Creates a TaskPanel object
  public TaskPanel(){
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    initComponents();
  }

  public void initComponents(){
    myTasks = new JList((PIM.getTaskManager().getTasks()).toArray());
    JScrollPane myTasksScroll = new JScrollPane(myTasks);
    JButton addTask = new JButton("A");
    JButton editTask = new JButton("E");
    JButton deleteTask = new JButton("D");
    JButton homeButton = new JButton("Home");
    JPanel taskButtons = new JPanel(); //Stores the task buttons
    taskButtons.setLayout(new BoxLayout(taskButtons, BoxLayout.X_AXIS));
    taskButtons.add(addTask);
    taskButtons.add(editTask);
    taskButtons.add(deleteTask);
    add(taskButtons);
    add(myTasksScroll);
    add(homeButton);
    homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    homeButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        }
    });
    addTask.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          panelStatus = "A";
        }
    });
    myTasks.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent evt) {
        if (panelStatus.equals("A")) {
          System.out.println("Add");
        }
        else if (panelStatus.equals("E")) {
          System.out.println("Edit");
        }
        else if (panelStatus.equals("D")) {
          System.out.println("Delete");
        }
        else {
          System.out.println("view");
        }
      }
    });
  }

  //Gets saved tasks and displays them on myTasks text area. Do not delete
  // will use later maybe.
  /*public void initTasks() {
    String newLn = System.getProperty("line.separator");
    for(Task currentTask : PIM.getTaskManager().getTasks()) {
      myTasks.append("Name: " + currentTask.getTaskName() + newLn );
      myTasks.append("Due Date/Time: " + currentTask.getDueDateTimeString() + newLn );
      myTasks.append("Status: " + currentTask.getTaskStatusString() + newLn );
      myTasks.append("Notes: " + currentTask.getTaskNotes() + newLn );
    }
  } */
}
