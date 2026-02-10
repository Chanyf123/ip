public class BaoException extends Exception {
    public static final String DESC_EMPTY = "The description cannot be empty!";
    public static final String OUT_OF_BOUNDS = "That task index is out of range.";
    public static final String INVALID_NUM = "Please provide a valid task number.";
    public static final String MISSING_BY = "Deadlines must include ' /by ' followed by a time.";
    public static final String MISSING_EVENT_INFO = "Events must include ' /from ' and ' /to ' with times.";

    public BaoException(String message) {
        super(message);
    }
}
