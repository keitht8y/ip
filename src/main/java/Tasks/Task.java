package Tasks;

/**
 * Represents a task in our chatbot.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private final String type;

    /**
     * Constructor for a Task object.
     * Used only in its subclasses.
     *
     * @param description A description for the task.
     * @param type The type of task, set by subclasses.
     */
    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getType() {
        return this.type;
    }

    abstract public String[] getInfo();

    @Override
    public String toString() {
        return (String.format("[%s] %s", getStatusIcon(), description));
    }
}





