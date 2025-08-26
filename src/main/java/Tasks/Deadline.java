package Tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, consisting of a description and an end date.
 */
public class Deadline extends Task {
    private final LocalDateTime end;

    /**
     * Constructor for a Deadline Task.
     *
     * @param description A description for the task.
     * @param end The end date for this task.
     */
    public Deadline(String description, String end) {
        super(description, "D");
        this.end = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public String[] getInfo() {
        return new String[] {
                end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))};
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