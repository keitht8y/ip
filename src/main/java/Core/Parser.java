package Core;

import Exceptions.MonException;
import Tasks.Task;
import Tasks.toDo;
import Tasks.Deadline;
import Tasks.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents how commands are interpreted & executed by Monarch.
 */
public class Parser {
    private boolean isEnd = false;

    public Parser(String userInput) {
        String[] sliced = userInput.split(" ");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        // Interpret user input
        switch (sliced[0]) {
            case "bye":
                this.isEnd = true;
                Storage storage = new Storage();
                try {
                    storage.save(tasks.getAll());
                } catch (IOException e) {
                    System.out.println("UH-OH");
                }
                break;

            case "list":
                // Return all inputs
                ui.listTasks(tasks.getAll());
                break;

            case "mark":
                // Mark task as done
                if (sliced.length < 2) {
                    System.out.println("\tUH-OH: You forgot to indicate a task to mark (e.g. 1)."
                            + "\n" + Ui.END_LINE);
                    break;
                }
                tasks.get(Integer.parseInt(sliced[1]) - 1).markAsDone();
                ui.mark(tasks.get(Integer.parseInt(sliced[1]) - 1));
                break;

            case "unmark":
                // Mark task as undone
                if (sliced.length < 2) {
                    System.out.println("\tUH-OH: You forgot to indicate a task to unmark (e.g. 1)."
                            + "\n" + Ui.END_LINE);
                    break;
                }
                tasks.get(Integer.parseInt(sliced[1]) - 1).unmark();
                ui.unmark(tasks.get(Integer.parseInt(sliced[1]) - 1));
                break;

            case "todo":
                // Create a ToDo task
                try {
                    if (sliced.length == 1) {
                        throw new MonException("ToDo missing description");
                    }
                    toDo toDoTask = new toDo(userInput.substring(4 + 1));
                    tasks.add(toDoTask);
                    ui.addTask(toDoTask);
                } catch (MonException e) {
                    System.out.println("\tUH-OH: You need a description for a todo."
                            + "\n" + Ui.END_LINE);
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
                    tasks.add(deadlineTask);
                    ui.addTask(deadlineTask);
                } catch (MonException error) {
                    System.out.println("\tUH-OH: You need a end time for a deadline."
                            + "\n" + Ui.END_LINE);
                }
                break;

            case "event":
                // Create an event task
                try {
                    String[] split = userInput.substring(5 + 1).split(" /from ");
                    String[] temp = split[1].split(" /to ");
                    String[] eventArgs = {split[0], temp[0], temp[1]};
                    Event eventTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                    tasks.add(eventTask);
                    ui.addTask(eventTask);
                } catch (RuntimeException error) {
                    System.out.println("\tUH-OH: You need a start time & end time for an event."
                            + "\n" + Ui.END_LINE);
                }
                break;

            case "delete":
                // Delete a task
                Task temp = tasks.get(Integer.valueOf(sliced[1]) - 1);
                tasks.remove(temp);
                ui.deleteTask(temp);
                break;

            case "clear":
                // Clear all tasks
                ui.clearList();
                break;

            default:
                // Unknown case
                try {
                    throw new MonException("Unknown");
                } catch (MonException error) {
                    System.out.println("\tThat's not something I can do unfortunately ¯\\_(ツ)_/¯"
                            + "\n" + Ui.END_LINE);
                }
        }
    }

    public boolean isEnd() {
        return this.isEnd;
    }
}
