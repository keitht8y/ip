package Tasks;

/**
 * Represents an Event task, consisting of a description, and a pair of start & end
 * dates.
 */
public class Event extends Task {
    private String start = "";
    private String end = "";

    /**
     * Constructor for an Event task.
     *
     * @param description A description for the task.
     * @param start A start date for this task.
     * @param end An end date for this task.
     */
    public Event(String description, String start, String end) {
        super(description, "E");
        this.start = start;
        this.end = end;
    }

    @Override
    public String[] getInfo() {
        return new String[] {this.start, this.end};
    }

    @Override
    public String toString() {
        return (String.format("[%s][%s] %s (from: %s to: %s)",
                "E",
                super.getStatusIcon(),
                super.getDescription(),
                this.start,
                this.end
        ));
    }
}