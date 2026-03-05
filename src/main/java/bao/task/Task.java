package bao.task;

/**
 * Represents a generic task in the Bao application.
 * This is an abstract class that provides the foundation for specific task types.
 * It tracks the task description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Returns a string representation of the task formatted for file storage.
     * Subclasses must implement this to ensure tasks can be saved and reloaded correctly.
     *
     * @return A formatted string suitable for saving to a text file.
     */
    public abstract String toFileFormat();

    /**
     * Initializes a new Task with the given description.
     * By default, a new task is not completed.
     *
     * @param description The name or details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a visual icon representing the task's completion status.
     * "X" indicates done, while a space " " indicates not done.
     *
     * @return A status icon string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon
     * and description, intended for display to the user.
     *
     * @return A user-friendly string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
