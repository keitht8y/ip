package Monarch.Core;

import Monarch.Exceptions.MonException;
import Monarch.Tasks.Task;
import Monarch.Tasks.toDo;
import Monarch.Tasks.Deadline;
import Monarch.Tasks.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents how commands are interpreted & executed by Monarch.
 */
public class  Parser {
    private boolean isEnd = false;

    public Parser(String userInput) throws MonException {
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
                    throw new MonException("UH-OH: Tasks didn't get saved properly.");
                }
                break;

            case "list":
                // Return all inputs
                ui.listTasks(tasks.getAll());
                break;

            case "mark":
                // Mark task as done
                try {
                    if (sliced.length < 2) {
                        throw new MonException("UH-OH: You forgot to indicate a task to mark (e.g. 1).");
                    } else if (tasks.size() < Integer.parseInt(sliced[1]) - 1 || Integer.valueOf(sliced[1]) - 1 <= 0) {
                        throw new MonException("UH-OH: The task isn't in the range of the task list.");
                    }
                } catch (NumberFormatException e) {
                    throw new MonException("UH-OH: You need to give a number for the task to delete.");
                }
                tasks.get(Integer.parseInt(sliced[1]) - 1).markAsDone();
                ui.mark(tasks.get(Integer.parseInt(sliced[1]) - 1));
                break;

            case "unmark":
                // Mark task as undone
                try {
                    if (sliced.length < 2) {
                        throw new MonException("UH-OH: You forgot to indicate a task to unmark (e.g. 1).");
                    } else if (tasks.size() < Integer.parseInt(sliced[1]) - 1 || Integer.valueOf(sliced[1]) - 1 <= 0) {
                        throw new MonException("UH-OH: The task isn't in the range of the task list.");
                    }
                } catch (NumberFormatException e) {
                    throw new MonException("UH-OH: You need to give a number for the task to delete.");
                }
                tasks.get(Integer.parseInt(sliced[1]) - 1).unmark();
                ui.unmark(tasks.get(Integer.parseInt(sliced[1]) - 1));
                break;

            case "todo":
                // Create a ToDo task
                if (sliced.length == 1) {
                    throw new MonException("UH-OH: You need a description for a todo.");
                }
                toDo toDoTask = new toDo(userInput.substring(4 + 1));
                tasks.add(toDoTask);
                ui.addTask(toDoTask);
                break;

            case "deadline":
                // Create a deadline task
                try {
                    String[] args = userInput.substring(8 + 1).split(" /by ", 2);
                    if (args.length == 1) {
                        throw new MonException("UH-OH: You need a end time for a deadline.");
                    }
                    Deadline deadlineTask = new Deadline(args[0], args[1]);
                    tasks.add(deadlineTask);
                    ui.addTask(deadlineTask);
                } catch (RuntimeException error) {
                    throw new MonException("UH-OH: You need a start time & end time for an event.");
                }
                break;

            case "event":
                // Create an event task
                try {
                    String[] split = userInput.substring(5 + 1).split(" /from ", 2);
                    String[] temp = split[1].split(" /to ", 2);
                    String[] eventArgs = {split[0], temp[0], temp[1]};
                    Event eventTask = new Event(eventArgs[0], eventArgs[1], eventArgs[2]);
                    tasks.add(eventTask);
                    ui.addTask(eventTask);
                } catch (RuntimeException error) {
                    throw new MonException("UH-OH: You need a start time & end time for an event.");
                }
                break;

            case "delete":
                // Delete a task
                try {
                    if (sliced.length < 2) {
                        throw new MonException("UH-OH: You forgot to indicate a task to unmark (e.g. 1).");
                    } else if (tasks.size() < Integer.valueOf(sliced[1]) - 1 || Integer.valueOf(sliced[1]) - 1 <= 0) {
                        throw new MonException("UH-OH: The task isn't in the range of the task list.");
                    }
                } catch (NumberFormatException e) {
                    throw new MonException("UH-OH: You need to give a number for the task to delete.");
                }
                Task temp = tasks.get(Integer.valueOf(sliced[1]) - 1);
                tasks.remove(temp);
                ui.deleteTask(temp);
                break;

            case "clear":
                // Clear all tasks
                ui.clearList();
                break;

            case "find":
                // Find a task by keywords
                try {
                    String keyphrase = userInput.split(" ", 2)[1];
                    ArrayList<Task> taskList = new ArrayList<>();
                    Pattern pattern = Pattern.compile(keyphrase, Pattern.CASE_INSENSITIVE);
                    for (int i = 0; i < tasks.size(); i ++) {
                        Task task = tasks.get(i);
                        Matcher matcher = pattern.matcher(task.getDescription());
                        if (matcher.find()) {
                            taskList.add(task);
                        }
                    }

                    ui.findTask(taskList);
                } catch (RuntimeException error) {
                    throw new MonException("UH-OH: That's not a valid keyword for finding tasks.");
                }
                break;

            default:
                // Unknown case
                throw new MonException("That's not something I can do unfortunately ¯\\_(ツ)_/¯");
        }
    }

    public boolean isEnd() {
        return this.isEnd;
    }
}
