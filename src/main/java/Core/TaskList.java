package Core;

import Exceptions.MonException;
import Tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the way Monarch handles the Task list.
 */
public class TaskList {
    public ArrayList<Task> taskList;

    /**
     * Constructor for initializing a TaskList object from a ArrayList.
     *
     * @param taskList An initial array list of Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructor for initializing an empty TaskList object
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns nothing.
     * Receives a Task object to add to the task list.
     *
     * @param task The Task object to add.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns nothing.
     * Receives a Task object to remove from the Task List.
     *
     * @param task The task object to remove.
     */
    public void delete(Task task) {
        taskList.remove(task);
    }

    /**
     * Returns this Task List.
     * Temporary debugging solution.
     *
     * @return An ArrayList of Task.
     */
    public ArrayList<Task> temp() {
        return this.taskList;
    }
}
