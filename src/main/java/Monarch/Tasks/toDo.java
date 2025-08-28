package Monarch.Tasks;

/**
 * Represents a toDo task, consisting of only a description.
 */
public class toDo extends Task {
    /**
     * Constructor for a toDo task
     *
     * @param description A description for the task.
     */
    public toDo(String description) {
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
