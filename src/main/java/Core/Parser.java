//package Core;
//
//import Exceptions.MonException;
//import Tasks.Deadline;
//import Tasks.Event;
//import Tasks.Task;
//import Tasks.toDo;
//
//import java.io.IOException;
//import java.util.Scanner;
//
///**
// * Represents how commands are interpreted & executed by Monarch.
// */
//public class Parser {
//    private static final String END_LINE = "\t____________________________________________________________";
//
//    public static void start() {
//        Scanner scanObj = new Scanner(System.in);
//
//        while (true) {
//            String userInput = scanObj.nextLine();
//            System.out.println(END_LINE);
//
//            String[] sliced = userInput.split(" ");
//
//            // Interpret user input
//            switch (sliced[0]) {
//            case "bye":
//                // Set exit flag
//                break;
//
//            case "list":
//                // Return all inputs
//                System.out.println("\tHere are the tasks in your list:");
//                for (int i = 0; i < taskArr.size(); i ++) {
//                    if (taskArr.get(i) == null) {
//                        break;
//                    }
//                    else {
//                        String msg = String.format("%d. %s", i + 1, taskArr.get(i).toString());
//                        System.out.println("\t" + msg);
//                    }
//                }
//                System.out.println(END_LINE);
//                break;
//
//            case "mark":
//                // Mark task as done
//                taskArr.get(Integer.parseInt(sliced[1]) - 1).markAsDone();
//                try {
//                    Storage.save(SAVE_PATH, taskArr);
//                } catch (IOException e) {
//                    System.out.println("\tUH-OH: Your task change didn't get saved!"
//                            + "\n" + END_LINE);
//                }
//                System.out.println("\tNice! I've marked this task as done:\n"
//                        + "\t\t" + taskArr.get(Integer.parseInt(sliced[1]) - 1).toString()
//                        + "\n" + END_LINE);
//                break;
//
//            case "unmark":
//                // Mark task as undone
//                taskArr.get(Integer.parseInt(sliced[1]) - 1).unmark();
//                try {
//                    Storage.save(SAVE_PATH, taskArr);
//                } catch (IOException e) {
//                    System.out.println("\tUH-OH: Your task change didn't get saved!"
//                            + "\n" + END_LINE);
//                }
//                System.out.println("\tOk, I've marked this task as not done yet:\n"
//                        + "\t\t" + taskArr.get(Integer.parseInt(sliced[1]) - 1)
//                        + "\n" + END_LINE);
//                break;
//
//            case "todo":
//                // Create a ToDo task
//                try {
//                    if (sliced.length == 1) {
//                        throw new MonException("ToDo missing description");
//                    }
//                    toDo toDoTask = new toDo(userInput.substring(4 + 1));
//                    taskArr.add(toDoTask);
//                    Storage.save(SAVE_PATH, taskArr);
//                    System.out.println("\tGot it. I've added this task:\n"
//                            + "\t\t" + toDoTask + "\n"
//                            + "\tNow you have " + taskArr.size() + " tasks in the list.\n"
//                            + "\n" + END_LINE);
//                } catch (MonException e) {
//                    System.out.println("\tUH-OH: You need a description for a todo."
//                            + "\n" + END_LINE);
//                } catch (IOException e) {
//                    System.out.println("\tUH-OH: Your task didn't get saved!"
//                            + "\n" + END_LINE);
//                }
//                break;
//
//            case "deadline":
//                // Create a deadline task
//                try {
//                    String[] args = userInput.substring(8 + 1).split(" /by ");
//                    if (args.length == 1) {
//                        throw new MonException("Deadline missing end");
//                    }
//                    Deadline deadlineTask = new Deadline(args[0], args[1]);
//                    taskArr.add(deadlineTask);
//                    Storage.save(SAVE_PATH, taskArr);
//                    System.out.println("\tGot it. I've added this task:\n"
//                            + "\t\t" + deadlineTask + "\n"
//                            + "\tNow you have " + taskArr.size() + " tasks in the list.\n"
//                            + "\n" + END_LINE);
//                } catch (MonException error) {
//                    System.out.println("\tUH-OH: You need a end time for a deadline."
//                            + "\n" + END_LINE);
//                } catch (IOException e) {
//                    System.out.println("\tUH-OH: Your task didn't get saved!"
//                            + "\n" + END_LINE);
//                }
//                break;
//
//            case "event":
//                // Create an event task
//                try {
//                    String[] split = userInput.substring(5 + 1).split(" /from ");
//                    String[] temp = split[1].split(" /to ");
//                    String[] eventArgs = {split[0], temp[0], temp[1]};
//                    Event eventTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
//                    taskArr.add(eventTask);
//                    Storage.save(SAVE_PATH, taskArr);
//                    System.out.println("\tGot it. I've added this task:\n"
//                            + "\t\t" + eventTask + "\n"
//                            + "\tNow you have " + taskArr.size() + " tasks in the list.\n"
//                            + "\n" + END_LINE);
//                } catch (RuntimeException error) {
//                    System.out.println("\tUH-OH: You need a start time & end time for an event."
//                            + "\n" + END_LINE);
//                } catch (IOException e) {
//                    System.out.println("\tUH-OH: Your task didn't get saved!"
//                            + "\n" + END_LINE);
//                }
//                break;
//
//            case "delete":
//                // Delete a task
//                Task temp = taskArr.get(Integer.valueOf(sliced[1]) - 1);
//                taskArr.remove(Integer.valueOf(sliced[1]) - 1);
//                try {
//                    Storage.save(SAVE_PATH, taskArr);
//                } catch (IOException e) {
//                    System.out.println("\tUH-OH: Your task change didn't get saved!"
//                            + "\n" + END_LINE);
//                }
//                System.out.println("\tNoted. I've removed the task:\n"
//                        + "\t\t" + temp + "\n"
//                        + "\tNow you have " + taskArr.size() + " tasks in the list.\n"
//                        + "\n" + END_LINE);
//                break;
//
//            case "clear":
//                // Clear all tasks
//                System.out.println("\tAre you sure you want to clear all tasks? [Y]");
//                String confirm = scanObj.nextLine();
//                if (confirm.equals("Y")) {
//                    clear();
//                    System.out.println("\tNoted. I've cleared all tasks");
//                }
//                System.out.println(END_LINE);
//                break;
//
//            default:
//                // Unknown case
//                try {
//                    throw new MonException("Unknown");
//                } catch (MonException error) {
//                    System.out.println("\tThat's not something I can do unfortunately ¯\\_(ツ)_/¯"
//                            + "\n" + END_LINE);
//                }
//
//            }
//
//            // Exit program
//            if (userInput.equals("bye")) {
//                break;
//            }
//        }
//    }
//}
