package Tasks;

public class Event extends Task {
    String start = "";
    String end = "";

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return (String.format("[%s][%s] %s (from: %s to: %s)", "E",
                super.getStatusIcon(),
                super.getDescription(),
                this.start,
                this.end
        ));
    }
}