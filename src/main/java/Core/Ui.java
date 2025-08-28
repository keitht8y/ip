package Core;

import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    /** Used by Parser for formatting strings too */
    public static final String END_LINE = "\t____________________________________________________________";
    private TaskList tasks = new TaskList();

    public void greeting() {
        String msg = "\tHello! I'm Monarch\n\tWhat can I do for you?";
        System.out.println(msg);
        System.out.println(END_LINE);
    }

    public void end() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(END_LINE);
    }

    public void listTasks(ArrayList<Task> taskList) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i ++) {
            if (tasks.get(i) == null) {
                break;
            }
            else {
                String msg = String.format("%d. %s", i + 1, tasks.get(i).toString());
                System.out.println("\t" + msg);
            }
        }
        System.out.println(END_LINE);
    }

    public void mark(Task task) {
        System.out.println("\tNice! I've marked this task as done:\n"
                + "\t\t" + task.toString() + "\n" + END_LINE);
    }

    public void unmark(Task task) {
        System.out.println("\tOk, I've marked this task as not done yet:\n"
                + "\t\t" + task + "\n" + END_LINE);
    }

    public void addTask(Task task) {
        System.out.println("\tGot it. I've added this task:\n"
                + "\t\t" + task + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                + "\n" + END_LINE);
    }

    public void deleteTask(Task task) {
        System.out.println("\tNoted. I've removed the task:\n"
                + "\t\t" + task + "\n"
                + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                + "\n" + END_LINE);
    }

    public void clearList() {
//        System.out.println("\tAre you sure you want to clear all tasks? [Y]");
//        Scanner scanObj = new Scanner(System.in);
//        String confirm = scanObj.nextLine();
//        if (confirm.equals("Y")) {
//            tasks.clear();
//            System.out.println("\tNoted. I've cleared all tasks");
//        } else {
//            System.out.println("\tYour tasks are unchanged.");
//        }
        tasks.clear();
        System.out.println("\tNoted. I've cleared all tasks");
        System.out.println(Ui.END_LINE);
    }
}
