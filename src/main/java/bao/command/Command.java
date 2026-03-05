package bao.command;

import bao.BaoException;
import bao.Storage;
import bao.TaskList;
import bao.Ui;

/**
 * Represents an executable command in the Bao application.
 * All specific command types must extend this class and implement the
 * {@link #execute(TaskList, Ui, Storage)} method to define their behavior.
 */
public abstract class Command {

    /**
     * Executes the specific command logic.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The user interface for displaying feedback.
     * @param storage The storage system for reading and writing data.
     * @throws BaoException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BaoException;

    /**
     * Indicates whether the application should terminate after this command.
     * By default, commands return <code>false</code>.
     *
     * @return <code>true</code> if the application should exit; <code>false</code> otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
