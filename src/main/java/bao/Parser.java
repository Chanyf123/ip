package bao;

import bao.command.Command;
import bao.command.AddCommand;
import bao.command.DeleteCommand;
import bao.command.ExitCommand;
import bao.command.ListCommand;
import bao.command.MarkCommand;
import bao.command.UnmarkCommand;
import bao.command.FindCommand;
import bao.task.Todo;
import bao.task.Deadline;
import bao.task.Event;

/**
 * Parses user input into executable commands for the Bao application.
 * This class contains the logic to interpret strings and return the
 * appropriate {@link Command} objects.
 */
public class Parser {

    /**
     * Parses the full user input string and returns the corresponding command.
     *
     * @param userInput The raw input string from the user.
     * @return A {@link Command} object ready for execution.
     * @throws BaoException If the command is unknown or the arguments are invalid.
     */
    public static Command parseUserInput(String userInput) throws BaoException {
        String[] parts = userInput.trim().split(" ", 2);
        String commandName = parts[0].toLowerCase();
        String taskName;
        if (parts.length > 1) {
            taskName = parts[1].trim();
        } else {
            taskName = "";
        }

        switch (commandName) {
        case "list":
            return new ListCommand();
        case "todo":
            return handleToDo(taskName);
        case "deadline":
            return handleDeadline(taskName);
        case "event":
            return handleEvent(taskName);
        case "mark":
            return new MarkCommand(parseTaskID(taskName));
        case "unmark":
            return new UnmarkCommand(parseTaskID(taskName));
        case "delete":
            return new DeleteCommand(parseTaskID(taskName));
        case "find":
            return handleFind(taskName);
        case "bye":
            return new ExitCommand();
        default:
            throw new BaoException("I'm sorry, but I don't know what '" + userInput + "' means :(\n");
        }
    }

    /**
     * Validates and creates a {@link Todo} task.
     * @param taskName The description of the todo.
     * @return An {@link AddCommand} containing the new Todo.
     * @throws BaoException If the description is empty.
     */
    private static AddCommand handleToDo(String taskName) throws BaoException {
        if (taskName.isEmpty()) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }
        return new AddCommand(new Todo(taskName));
    }

    /**
     * Converts a string task index into an integer ID.
     * @param taskName The string containing the task number (e.g., "1").
     * @return The 0-indexed integer representation of the task ID.
     * @throws BaoException If the input is empty or not a valid number.
     */
    private static int parseTaskID(String taskName) throws BaoException {
        try {
            if (taskName.isEmpty()) {
                throw new BaoException(BaoException.INVALID_NUM);
            }
            return Integer.parseInt(taskName) - 1;
        } catch (NumberFormatException e) {
            throw new BaoException(BaoException.INVALID_NUM_FORMAT);
        }
    }

    /**
     * Parses deadline task name as input and separates the description from the 'by' date.
     * @param taskName The raw arguments for the deadline command.
     * @return An {@link AddCommand} containing the new Deadline.
     * @throws BaoException If the format is missing '/by' or parts are empty.
     */
    private static Command handleDeadline(String taskName) throws BaoException {
        if (!taskName.contains(" /by ")) {
            throw new BaoException(BaoException.MISSING_BY);
        }
        String[] parts = taskName.split(" /by ", 2);
        if (parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }
        return new AddCommand(new Deadline(parts[0].trim(), parts[1].trim()));
    }

    /**
     * Parses event input into description, start time, and end time.
     * @param taskName The raw arguments for the event command.
     * @return An {@link AddCommand} containing the new Event.
     * @throws BaoException If the format is missing '/from' or '/to'.
     */
    private static Command handleEvent(String taskName) throws BaoException {
        if (!taskName.contains(" /from ") || !taskName.contains(" /to ")) {
            throw new BaoException(BaoException.MISSING_EVENT_INFO);
        }
        String[] parts = taskName.split(" /from | /to ", 3);
        if (parts.length < 3 || parts[0].trim().isEmpty()) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }
        return new AddCommand(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
    }

    /**
     * Validates the search keyword for a find command.
     * @param taskName The keyword to search for.
     * @return A {@link FindCommand} with the search keyword.
     * @throws BaoException If the keyword is empty.
     */
    private static Command handleFind(String taskName) throws BaoException {
        if (taskName.isEmpty()) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }
        return new FindCommand(taskName);
    }
}
