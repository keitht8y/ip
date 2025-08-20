import java.util.Scanner;
import Tasks.*;
import Exceptions.*;

public class Monarch {
    static String END_LINE = "\t____________________________________________________________";
    static Task[] taskArr = new Task[100];
    static int index = 0;

    /**
     * The core program
     * @since Level-1
     */
    public static void program() {
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
                    for (int i = 0; i < taskArr.length; i ++) {
                        if (taskArr[i] == null) {
                            break;
                        }
                        else {
                            String msg = String.format("%d. %s", i + 1, taskArr[i].toString());
                            System.out.println("\t" + msg);
                        }
                    }
                    System.out.println(END_LINE);
                    break;

                case "mark":
                    // Mark task as done
                    taskArr[Integer.parseInt(sliced[1]) - 1].markAsDone();
                    System.out.println("\tNice! I've marked this task as done:\n"
                            + "\t\t" + taskArr[Integer.parseInt(sliced[1]) - 1].toString()
                            + "\n" + END_LINE);
                    break;

                case "unmark":
                    // Mark task as undone
                    taskArr[Integer.parseInt(sliced[1]) - 1].unmark();
                    System.out.println("\tOk, I've marked this task as not done yet:\n"
                            + "\t\t" + taskArr[Integer.parseInt(sliced[1]) - 1]
                            + "\n" + END_LINE);
                    break;

                case "todo":
                    // Create a ToDo task
                    try {
                        if (sliced.length == 1) {
                            throw new MonException("ToDo missing description");
                        }
                        toDo toDoTask = new toDo(userInput.substring(4 + 1));
                        taskArr[index] = toDoTask;
                        index += 1;
                        System.out.println("\tGot it. I've added this task:\n"
                                + "\t\t" + toDoTask + "\n"
                                + "\tNow you have " + index + " tasks in the list.\n"
                                + "\n" + END_LINE);
                    } catch (MonException e) {
                        System.out.println("\tUH-OH: You need a description for a todo."
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
                        taskArr[index] = deadlineTask;
                        index += 1;
                        System.out.println("\tGot it. I've added this task:\n"
                                + "\t\t" + deadlineTask + "\n"
                                + "\tNow you have " + index + " tasks in the list.\n"
                                + "\n" + END_LINE);
                    } catch (MonException error) {
                        System.out.println("\tUH-OH: You need a end time for a deadline."
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
                        taskArr[index] = eventTask;
                        index += 1;
                        System.out.println("\tGot it. I've added this task:\n"
                                + "\t\t" + eventTask + "\n"
                                + "\tNow you have " + index + " tasks in the list.\n"
                                + "\n" + END_LINE);
                    } catch (RuntimeException error) {
                        System.out.println("\tUH-OH: You need a start time & end time for an event."
                                + "\n" + END_LINE);
                    }
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

    /**
     * The greeting message
     * @since Level-0
     */
    public static void greeting() {
        String msg = "\tHello! I'm Monarch\n\tWhat can I do for you?";
        System.out.println(msg);
        System.out.println(END_LINE);
    }

    /**
     * The exit message
     * @since Level-0
     */
    public static void end() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(END_LINE);
    }

    public static void main(String[] args) {
        System.out.println(END_LINE);
        greeting();
        program();
        end();
    }


}
