package monarch.tasks;

/**
 * Represents a toDo task, consisting of only a description.
 */
public class ToDo extends Task {
    /**
     * Constructor for a toDo task
     *
     * @param description A description for the task.
     */
    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String[] getInfo() {
        return new String[] {};
    }

    @Override
    public String toString() {
        return (String.format("[%s][%s] %s", "T",
                super.getStatusIcon(),
                super.getDescription()
        ));
    }
}
