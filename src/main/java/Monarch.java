public class Monarch {
    static String END_LINE = "____________________________________________________________";

    public static void greeting() {
        String msg = "Hello! I'm Monarch\nWhat can I do for you?";
        System.out.println(msg);
    }

    public static void end() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        System.out.println(END_LINE);
        greeting();
        System.out.println(END_LINE);
        end();
        System.out.println(END_LINE);
    }
}
