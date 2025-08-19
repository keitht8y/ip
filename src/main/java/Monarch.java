import java.util.Scanner;

public class Monarch {
    static String END_LINE = "\t____________________________________________________________";

    /**
     * The core program
     * @since Level-1
     */
    public static void program() {
        while (true) {
            Scanner scanObj = new Scanner(System.in);
            String userInput = scanObj.nextLine();
            System.out.println(END_LINE);

            // Interpret user input
            switch (userInput) {
                case "bye":
                    // Set exit flag
                    break;

                default:
                    // Default case
                    System.out.println(userInput + "\n" + END_LINE);
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
