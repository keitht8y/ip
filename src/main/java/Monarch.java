import java.util.Scanner;

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

            // Interpret user input
            switch (userInput) {
                case "bye":
                    // Set exit flag
                    break;

                case "list":
                    // Return all inputs
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

                default:
                    // Check for 'mark'
                    String[] temp = userInput.split(" ");
                    if (temp.length > 1) {
                        if (temp[0].equals("mark")) {
                            taskArr[Integer.parseInt(temp[1]) - 1].markAsDone();
                            System.out.println("\tNice! I've marked this task as done:\n"
                                    + "\t\t" + taskArr[Integer.parseInt(temp[1]) - 1].toString()
                                    + "\n" + END_LINE);
                            break;
                        } else if (temp[0].equals("unmark")) {
                            // Check for 'unmark'
                            taskArr[Integer.parseInt(temp[1]) - 1].unmark();
                            System.out.println("\tOk, I've marked this task as not done yet:\n"
                                    + "\t\t" + taskArr[Integer.parseInt(temp[1]) - 1]
                                    + "\n" + END_LINE);
                            break;
                        }
                    }

                    // Default case
                    taskArr[index] = new Task(userInput);
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

    public static class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public String getDescription() {
            return (description);
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return (String.format("[%s] %s", getStatusIcon(), description));
        }
    }
}
