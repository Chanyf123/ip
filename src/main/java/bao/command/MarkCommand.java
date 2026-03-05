package bao.command;

import bao.BaoException;
import bao.Storage;
import bao.TaskList;
import bao.Ui;
import bao.task.Task;

import java.io.IOException;

/**
 * Represents the command to mark a specific task as completed.
 * This command updates the task's status in memory and ensures the change
 * is reflected in the storage file.
 */
public class MarkCommand extends Command {
    private final int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the mark-as-done logic.
     * Retrieves the task from the list, sets its status to completed,
     * saves the updated list to storage, and displays a success message.
     *
     * @param tasks   The list containing the task to be marked.
     * @param ui      The user interface for displaying feedback.
     * @param storage The storage system for updating the data file.
     * @throws BaoException If the taskId is invalid.
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws BaoException {
        Task task = tasks.getTask(taskId);
        task.markAsDone();

        try {
            storage.save(tasks.getList());
        } catch (IOException e) {
            System.out.println(" OOPS!!! Something went wrong while saving: " + e.getMessage());
        }

        ui.showTaskMarkDoneMessage(task);
    }

}
