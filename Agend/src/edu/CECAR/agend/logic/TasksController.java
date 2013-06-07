
package edu.CECAR.agend.logic;

import java.util.Vector;

/**
 *
 * @author Yesid Lazaro Mayoriano
 */
public class TasksController {

    private Vector tasks;

    public TasksController() {
        tasks= new Vector(); 
    }
    
    public void addTask(Task task) {
        tasks.addElement(task);
    }

    public void removeTask(Task task) {
        tasks.removeElement(task);
    }

    public Task getTask(String input) {
        Task task = null;
        for (int i = 0; i < tasks.size(); i++) {

            if (((Task) tasks.elementAt(i)).getName().equals(input)) {
                task = ((Task) tasks.elementAt(i));
            }
        }
        return task;
    }

    public Vector getTasks() {
        return tasks;
    }
    
}
