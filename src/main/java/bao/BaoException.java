package bao;

public class BaoException extends Exception {
    public static final String DESC_EMPTY = "The task description cannot be empty! Please add a task name.";
    public static final String OUT_OF_BOUNDS = "That task index is out of range.";
    public static final String INVALID_NUM = "Please provide a valid task number.";
    public static final String MISSING_BY = "Deadlines must be in this format '<task> /by <date/time>'";
    public static final String MISSING_EVENT_INFO = "Events must include ' /from ' and ' /to ' with times.";

    public BaoException(String message) {
        super(message);
    }
}
