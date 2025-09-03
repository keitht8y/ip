package monarch;

import java.util.Scanner;

import monarch.core.Parser;
import monarch.core.Storage;
import monarch.core.TaskList;
import monarch.core.Ui;
import monarch.exceptions.MonException;

/**
 * Represents the main Chat bot. Is started on running the file.
 */
public class Monarch {
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
     * Starts the main program of the Chat bot.
     */
    public void run() {
        System.out.println(Ui.END_LINE);
        ui.greeting();

        while (true) {
            String userInput = scanObj.nextLine();
            System.out.println(Ui.END_LINE);

            try {
                Parser parser = new Parser(userInput);
                if (parser.isEnd()) {
                    break;
                }
            } catch (MonException e) {
                System.out.println("\t" + e.getMessage() + "\n" + Ui.END_LINE);
            }
        }

        scanObj.close();
        ui.end();
    }

    /**
     * Starts the Chat bot.
     *
     * @param args Additional command line flags
     */
    public static void main(String[] args) {
        new Monarch(SAVE_PATH).run();
    }

}
