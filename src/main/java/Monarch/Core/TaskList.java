package Monarch.Core;

import Monarch.Tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the way Monarch handles the Task list.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * Receives an ArrayList of Tasks to set as its own list.
     *
     * @param taskList An array list of Tasks.
     */
    public void set(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Set an empty task list.
     */
    public void set() {
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
    public void remove(Task task) {
        taskList.remove(task);
    }

    /**
     * Returns the size of the current task list.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns a task from the list given its index.
     *
     * @param i The index of the task.
     * @return A task object.
     */
    public Task get(int i) {
        return this.taskList.get(i);
    }

    /**
     * Returns an Array List object with all tasks.
     *
     * @return ArrayList of Task
     */
    public ArrayList<Task> getAll() {
        return this.taskList;
    }

    /**
     * Removes all tasks from the list.
     */
    public void clear() {
        this.taskList = new ArrayList<>();
        try {
            FileWriter fw = new FileWriter(new Storage().getFilePath());
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("\tUH-OH");
        }
    }
}
