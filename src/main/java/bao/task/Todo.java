package bao.task;

/**
 * Represents a To-do type task.
 * A <code>Todo</code> is a basic task that only contains a description
 * without any date or time constraints.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the To-do task formatted for file storage.
     * The format used is: <code>T | status | description</code>.
     *
     * @return A formatted string suitable for saving to a text file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
