package monarch.core;

import java.util.ArrayList;

import monarch.tasks.Task;

/**
 * Represents the Ui messages of the chatbot.
 */
public class Ui {
    /** Used by Parser for formatting strings too, made public for other classes' access */
    // public static final String END_LINE = "\t____________________________________________________________";
    private TaskList tasks = new TaskList();

    /**
     * Returns a greeting message.
     */
    public String greeting() {
        return "Hello! I'm Monarch\nWhat can I do for you?";
    }

    /**
     * Returns the end message.
     */
    public String end() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns the list of tasks.
     *
     * @param taskList A list of task objects.
     */
    public String listTasks(ArrayList<Task> taskList) {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            } else {
                String entry = String.format("%d. %s", i + 1, tasks.get(i).toString());
                message.append("\n").append(entry);
            }
        }
        return message.toString();
    }

    /**
     * Returns a mark message.
     *
     * @param task Task object.
     */
    public String mark(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "\t" + task.toString();
    }

    /**
     * Returns an unmark message.
     *
     * @param task Task object.
     */
    public String unmark(Task task) {
        return "Ok, I've marked this task as not done yet:\n"
                + "\t" + task.toString();
    }

    /**
     * Returns a task adding message.
     *
     * @param task Task object.
     */
    public String addTask(Task task) {
        return "Got it. I've added this task:\n"
                + "\t" + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a task deletion message.
     *
     * @param task Task object.
     */
    public String deleteTask(Task task) {
        return "Noted. I've removed the task:\n"
                + "\t" + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns a finding task message.
     *
     * @param taskList A list of task objects.
     */
    public String findTask(ArrayList<Task> taskList) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            message.append(String.format("\n\t%d. %s", i + 1, task.toString()));
        }
        return message.toString();
    }

    /**
     * Returns a clear task list message.
     */
    public String clearList() {
        tasks.clear();
        return "Noted. I've cleared all tasks";
    }
}
