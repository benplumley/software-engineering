import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
public class TaskPanel extends JPanel {
  private static List<Task> taskList;
  private static JTextArea myTasks;
  //Creates a TaskPanel object
  public TaskPanel(){
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    initComponents();
  }

  public void initComponents(){
    myTasks = new JTextArea();
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
    initTasks();
    add(myTasksScroll);
    add(homeButton);
    homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
  }

  //Gets saved tasks and displays them on myTasks text area
  public void initTasks() {
    taskList = PIM.getTaskManager().getTaskList();
    String newLn = System.getProperty("line.separator");
    for(Task currentTask : taskList) {
      myTasks.append("Name: " + currentTask.getTaskName() + newLn );
      myTasks.append("Due Date/Time: " + currentTask.getDueDateTimeString() + newLn );
      myTasks.append("Status: " + currentTask.getTaskStatusString() + newLn );
      myTasks.append("Notes: " + currentTask.getTaskNotes() + newLn );
    }
  }
}
