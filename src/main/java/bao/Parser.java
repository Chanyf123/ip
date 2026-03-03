package bao;

import bao.command.Command;
import bao.command.AddCommand;
import bao.command.DeleteCommand;
import bao.command.ExitCommand;
import bao.command.ListCommand;
import bao.command.MarkCommand;
import bao.command.UnmarkCommand;
import bao.task.Todo;
import bao.task.Deadline;
import bao.task.Event;


public class Parser {
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
        case "bye":
            return new ExitCommand();
        default:
            throw new BaoException("I'm sorry, but I don't know what '" + userInput + "' means :(\n");
        }
    }

    private static AddCommand handleToDo(String taskName) throws BaoException {
        if (taskName.isEmpty()) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }
        return new AddCommand(new Todo(taskName));
    }

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
}
