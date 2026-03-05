package bao.task;

/**
 * Represents an Event type task.
 * An <code>Event</code> is a task that occurs within a specific time frame,
 * starting at a certain date/time and ending at another.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String start, String end) {
        super(description);
        this.from = start;
        this.to = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the Event task formatted for file storage.
     * The format used is: <code>E | status | description | from | to</code>.
     *
     * @return A formatted string suitable for saving to a text file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
