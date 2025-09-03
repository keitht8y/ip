package monarch.core;

import java.util.ArrayList;

import monarch.tasks.Task;

/**
 * Represents the Ui messages of the chatbot.
 */
public class Ui {
    /** Used by Parser for formatting strings too, made public for other classes' access */
    public static final String END_LINE = "\t____________________________________________________________";
    private TaskList tasks = new TaskList();

    /**
     * Returns a greeting message.
     */
    public void greeting() {
        String msg = "\tHello! I'm Monarch\n\tWhat can I do for you?";
        System.out.println(msg);
        System.out.println(END_LINE);
    }

    /**
     * Returns the end message.
     */
    public void end() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(END_LINE);
    }

    /**
     * Returns the list of tasks.
     *
     * @param taskList A list of task objects.
     */
    public void listTasks(ArrayList<Task> taskList) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            } else {
                String msg = String.format("%d. %s", i + 1, tasks.get(i).toString());
                System.out.println("\t" + msg);
            }
        }
        System.out.println(END_LINE);
    }

    /**
     * Returns a mark message.
     *
     * @param task Task object.
     */
    public void mark(Task task) {
        System.out.println("\tNice! I've marked this task as done:\n"
                + "\t\t" + task.toString() + "\n" + END_LINE);
    }

    /**
     * Returns an unmark message.
     *
     * @param task Task object.
     */
    public void unmark(Task task) {
        System.out.println("\tOk, I've marked this task as not done yet:\n"
                + "\t\t" + task + "\n" + END_LINE);
    }

    /**
     * Returns a task adding message.
     *
     * @param task Task object.
     */
    public void addTask(Task task) {
        System.out.println("\tGot it. I've added this task:\n"
                + "\t\t" + task + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                + "\n" + END_LINE);
    }

    /**
     * Returns a task deletion message.
     *
     * @param task Task object.
     */
    public void deleteTask(Task task) {
        System.out.println("\tNoted. I've removed the task:\n"
                + "\t\t" + task + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                + "\n" + END_LINE);
    }

    /**
     * Returns a finding task message.
     *
     * @param taskList A list of task objects.
     */
    public void findTask(ArrayList<Task> taskList) {
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(String.format("\t%d. %s", i + 1, task.toString()));
        }
        System.out.println(END_LINE);
    }

    /**
     * Returns a clear task list message.
     */
    public void clearList() {
        tasks.clear();
        System.out.println("\tNoted. I've cleared all tasks");
        System.out.println(Ui.END_LINE);
    }
}
