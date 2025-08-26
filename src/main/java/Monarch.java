import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.toDo;

import Exceptions.MonException;


/**
 * Represents the main Chat bot. Is started on running the file.
 */
public class Monarch {
    private static final String END_LINE = "\t____________________________________________________________";
    private static ArrayList<Task> taskArr = null;
    private static final String SAVE_PATH = "./save.txt";

    private static void init() {
        File f = new File(SAVE_PATH);
        try {
            // Check if save file exists, create if required
            f.createNewFile();

            // Retrieve tasks from save file
            ArrayList<Task> save = new ArrayList<>();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] i = s.nextLine().split(",,,", 3);
                String type = i[0];
                String status = i[1];
                String info = i[2];
                Task task = null;

                switch (type) {
                case "T":
                    // Sample structure: <type>,,,<status>,,,<info>
                    task = new toDo(info);
                    break;

                case "D":
                    // Sample structure: <type>,,,<status>,,,<desc>,,,<end>
                    String[] deadlineArgs = info.split(",,,", 2);
                    task = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                    break;

                case "E":
                    // Sample structure: <type>,,,<status>,,,<desc>,,,<start>,,,<end>
                    String[] eventArgs = info.split(",,,", 3);
                    task = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                    break;

                default:
                    System.out.println("Unknown task type: " + type);
                }


                if (task != null) {
                    // Update task status
                    if (status.equals("X")) {
                        task.markAsDone();
                    }

                    // Append task to task list
                    save.add(task);
                }
            }
            // Return compiled task list
            taskArr = save;

        } catch (IOException e) {
            // Return an empty ArrayList due to error
            System.out.println(e.getMessage());
            taskArr = new ArrayList<>();
        }
    }

    private static void program() {
        Scanner scanObj = new Scanner(System.in);

        while (true) {
            String userInput = scanObj.nextLine();
            System.out.println(END_LINE);

            String[] sliced = userInput.split(" ");

            // Interpret user input
            switch (sliced[0]) {
            case "bye":
                // Set exit flag
                break;

            case "list":
                // Return all inputs
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < taskArr.size(); i ++) {
                    if (taskArr.get(i) == null) {
                        break;
                    }
                    else {
                        String msg = String.format("%d. %s", i + 1, taskArr.get(i).toString());
                        System.out.println("\t" + msg);
                    }
                }
                System.out.println(END_LINE);
                break;

            case "mark":
                // Mark task as done
                taskArr.get(Integer.parseInt(sliced[1]) - 1).markAsDone();
                try {
                    save();
                } catch (IOException e) {
                    System.out.println("\tUH-OH: Your task change didn't get saved!"
                            + "\n" + END_LINE);
                }
                System.out.println("\tNice! I've marked this task as done:\n"
                        + "\t\t" + taskArr.get(Integer.parseInt(sliced[1]) - 1).toString()
                        + "\n" + END_LINE);
                break;

            case "unmark":
                // Mark task as undone
                taskArr.get(Integer.parseInt(sliced[1]) - 1).unmark();
                try {
                    save();
                } catch (IOException e) {
                    System.out.println("\tUH-OH: Your task change didn't get saved!"
                            + "\n" + END_LINE);
                }
                System.out.println("\tOk, I've marked this task as not done yet:\n"
                        + "\t\t" + taskArr.get(Integer.parseInt(sliced[1]) - 1)
                        + "\n" + END_LINE);
                break;

            case "todo":
                // Create a ToDo task
                try {
                    if (sliced.length == 1) {
                        throw new MonException("ToDo missing description");
                    }
                    toDo toDoTask = new toDo(userInput.substring(4 + 1));
                    taskArr.add(toDoTask);
                    save();
                    System.out.println("\tGot it. I've added this task:\n"
                            + "\t\t" + toDoTask + "\n"
                            + "\tNow you have " + taskArr.size() + " tasks in the list.\n"
                            + "\n" + END_LINE);
                } catch (MonException e) {
                    System.out.println("\tUH-OH: You need a description for a todo."
                            + "\n" + END_LINE);
                } catch (IOException e) {
                    System.out.println("\tUH-OH: Your task didn't get saved!"
                            + "\n" + END_LINE);
                }
                break;

            case "deadline":
                // Create a deadline task
                try {
                    String[] args = userInput.substring(8 + 1).split(" /by ");
                    if (args.length == 1) {
                        throw new MonException("Deadline missing end");
                    }
                    Deadline deadlineTask = new Deadline(args[0], args[1]);
                    taskArr.add(deadlineTask);
                    save();
                    System.out.println("\tGot it. I've added this task:\n"
                            + "\t\t" + deadlineTask + "\n"
                            + "\tNow you have " + taskArr.size() + " tasks in the list.\n"
                            + "\n" + END_LINE);
                } catch (MonException error) {
                    System.out.println("\tUH-OH: You need a end time for a deadline."
                            + "\n" + END_LINE);
                } catch (IOException e) {
                    System.out.println("\tUH-OH: Your task didn't get saved!"
                            + "\n" + END_LINE);
                }
                break;

            case "event":
                // Create an event task
                try {
                    String[] split = userInput.substring(5 + 1).split(" /from ");
                    String[] temp = split[1].split(" /to ");
                    String[] eventArgs = {split[0], temp[0], temp[1]};
                    Event eventTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                    taskArr.add(eventTask);
                    save();
                    System.out.println("\tGot it. I've added this task:\n"
                            + "\t\t" + eventTask + "\n"
                            + "\tNow you have " + taskArr.size() + " tasks in the list.\n"
                            + "\n" + END_LINE);
                } catch (RuntimeException error) {
                    System.out.println("\tUH-OH: You need a start time & end time for an event."
                            + "\n" + END_LINE);
                } catch (IOException e) {
                    System.out.println("\tUH-OH: Your task didn't get saved!"
                            + "\n" + END_LINE);
                }
                break;

            case "delete":
                // Delete a task
                Task temp = taskArr.get(Integer.valueOf(sliced[1]) - 1);
                taskArr.remove(Integer.valueOf(sliced[1]) - 1);
                try {
                    save();
                } catch (IOException e) {
                    System.out.println("\tUH-OH: Your task change didn't get saved!"
                            + "\n" + END_LINE);
                }
                System.out.println("\tNoted. I've removed the task:\n"
                        + "\t\t" + temp + "\n"
                        + "\tNow you have " + taskArr.size() + " tasks in the list.\n"
                        + "\n" + END_LINE);
                break;

            case "clear":
                // Clear all tasks
                System.out.println("\tAre you sure you want to clear all tasks? [Y]");
                String confirm = scanObj.nextLine();
                if (confirm.equals("Y")) {
                    clear();
                    System.out.println("\tNoted. I've cleared all tasks");
                }
                System.out.println(END_LINE);
                break;

            default:
                // Unknown case
                try {
                    throw new MonException("Unknown");
                } catch (MonException error) {
                    System.out.println("\tThat's not something I can do unfortunately ¯\\_(ツ)_/¯"
                            + "\n" + END_LINE);
                }

            }

            // Exit program
            if (userInput.equals("bye")) {
                break;
            }
        }
    }

    private static void greeting() {
        String msg = "\tHello! I'm Monarch\n\tWhat can I do for you?";
        System.out.println(msg);
        System.out.println(END_LINE);
    }

    private static void end() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(END_LINE);
    }

    private static void save() throws IOException {
        FileWriter fw = new FileWriter(SAVE_PATH);
        String tasksList = "";
        for (int i = 0; i < taskArr.size(); i ++) {
            Task task = taskArr.get(i);
            String info = "";

            switch (task.getType()) {
            case "T":
                info = task.getDescription();
                break;

            case "D":
                info = String.format("%s,,,%s", task.getDescription(), task.getInfo()[0]);
                break;

            case "E":
                info = String.format("%s,,,%s,,,%s", task.getDescription(),
                        task.getInfo()[0],
                        task.getInfo()[1]);
                break;

            default:
                // Should never reach
                break;
            }

            String taskString = String.format("%s,,,%s,,,",
                    taskArr.get(i).getType(),
                    taskArr.get(i).getStatusIcon());
            //System.out.println(taskString + info);
            tasksList += (taskString + info + System.lineSeparator());
        }
        fw.write(tasksList);
        fw.close();
    }

    private static void clear() {
        taskArr = new ArrayList<>();
        try {
            FileWriter fw = new FileWriter(SAVE_PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("\tUH-OH");
        }
    }

    /**
     * Returns nothing.
     * Starts the main program of the Chat bot.
     *
     * @param args Additional command line flags
     */
    public static void main(String[] args) {
        init();
        System.out.println(END_LINE);
        greeting();
        program();
        end();
    }


}
