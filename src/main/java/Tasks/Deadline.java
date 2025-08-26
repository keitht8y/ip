package Tasks;

/**
 * Represents a Deadline task, consisting of a description and an end date.
 */
public class Deadline extends Task {
    private String end = "";

    /**
     * Constructor for a Deadline Task.
     *
     * @param description A description for the task.
     * @param end The end date for this task.
     */
    public Deadline(String description, String end) {
        super(description, "D");
        this.end = end;
    }

    @Override
    public String[] getInfo() {
        return new String[] {end};
    }

    @Override
    public String toString() {
        return (String.format("[%s][%s] %s (by: %s)",
                "D",
                super.getStatusIcon(),
                super.getDescription(),
                this.end
        ));
    }
}