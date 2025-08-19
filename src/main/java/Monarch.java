import java.util.Scanner;

public class Monarch {
    static String END_LINE = "\t____________________________________________________________";
    static String[] strArr = new String[100];
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

            // Interpret user input
            switch (userInput) {
                case "bye":
                    // Set exit flag
                    break;

                case "list":
                    // Return all inputs
                    for (int i = 0; i < strArr.length; i ++) {
                        if (strArr[i] == null) {
                            break;
                        }
                        else {
                            String msg = String.format("%d. %s", i + 1, strArr[i]);
                            System.out.println("\t" + msg);
                        }
                    }
                    System.out.println(END_LINE);
                    break;

                default:
                    // Default case
                    strArr[index] = userInput;
                    index += 1;
                    System.out.println("\tadded: " + userInput + "\n" + END_LINE);
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
