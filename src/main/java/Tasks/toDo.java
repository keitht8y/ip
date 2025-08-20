package Tasks;

public class toDo extends Task {
    public toDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return (String.format("[%s][%s] %s", "T",
                super.getStatusIcon(),
                super.getDescription()
        ));
    }
}
