package Tasks;

public class Deadline extends Task {
    String end = "";

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String toString() {
        return (String.format("[%s][%s] %s (by: %s)", "D",
                super.getStatusIcon(),
                super.getDescription(),
                this.end
        ));
    }
}