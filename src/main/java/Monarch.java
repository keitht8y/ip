import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;
import java.io.FileWriter;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.toDo;

import Exceptions.MonException;

import Core.Storage;
import Core.TaskList;
import Core.Ui;
import Core.Parser;

/**
 * Represents the main Chat bot. Is started on running the file.
 */
public class Monarch {
    private static final String END_LINE = "\t____________________________________________________________";
    private static final String SAVE_PATH = "./save.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Scanner scanObj;

    /**
     * Constructor for the Monarch chatbot object.
     *
     * @param filePath The path to a text file.
     */
    public Monarch(String filePath) {
        storage = new Storage();
        storage.set(filePath);
        tasks = new TaskList();
        ui = new Ui();
        try {
            tasks.set(storage.load());
        } catch (MonException e) {
            tasks.set();
        }
        scanObj = new Scanner(System.in);
    }

    /**
     * Returns nothing.
     * Starts the main program of the Chat bot.
     */
    public void run() {
        System.out.println(END_LINE);
        ui.greeting();

        while (true) {
            String userInput = scanObj.nextLine();
            System.out.println(END_LINE);
            Parser parser = new Parser(userInput);

            if (parser.isEnd()) {
                break;
            }
        }

        scanObj.close();
        ui.end();
    }

    /**
     * Returns nothing.
     * Start the Chat bot.
     *
     * @param args Additional command line flags
     */
    public static void main(String[] args) {
        new Monarch(SAVE_PATH).run();
    }

}
