package bao.task;

/**
 * Represents a "Deadline" type task.
 * A <code>Deadline</code> is a task that needs to be done by a specific date/time.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string representation of the Deadline task formatted for file storage.
     * The format used is: <code>D | status | description | by</code>.
     *
     * @return A formatted string suitable for saving to a text file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
