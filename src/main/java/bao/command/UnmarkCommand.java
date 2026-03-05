package bao.command;

import bao.BaoException;
import bao.Storage;
import bao.TaskList;
import bao.Ui;
import bao.task.Task;

import java.io.IOException;

/**
 * Represents the command to mark a specific task as not completed.
 * This command reverts a task's status to incomplete and updates the storage
 * file to reflect this change.
 */
public class UnmarkCommand extends Command {
    private final int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the unmark logic.
     * Retrieves the task, sets its status to incomplete using {@link Task#markAsNotDone()},
     * saves the updated list to storage, and displays feedback to the user.
     *
     * @param tasks   The list containing the task to be updated.
     * @param ui      The user interface for displaying feedback.
     * @param storage The storage system for updating the data file.
     * @throws BaoException If the taskID is invalid or the task list access fails.
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws BaoException {
        Task task = tasks.getTask(taskId);
        task.markAsNotDone();

        try {
            storage.save(tasks.getList());
        } catch (IOException e) {
            System.out.println(" OOPS!!! Something went wrong while saving: " + e.getMessage());
        }

        ui.showTaskMarkUndoneMessage(task);
    }

}
