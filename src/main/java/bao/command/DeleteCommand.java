package bao.command;

import bao.BaoException;
import bao.Storage;
import bao.TaskList;
import bao.Ui;
import bao.task.Task;

import java.io.IOException;

/**
 * Represents the command to remove a task from the task list.
 * This command deletes a task based on its taskID and updates the storage file.
 */
public class DeleteCommand extends Command {
    private final int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the deletion of a task from the list,
     * saves the change to storage, and
     * notifies the user of the successful removal.
     *
     * @param tasks   The list from which the task will be removed.
     * @param ui      The user interface for displaying feedback.
     * @param storage The storage system for updating the data file.
     * @throws BaoException If the taskId is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BaoException {
        Task removedtask = tasks.deleteTask(taskId);

        try {
            storage.save(tasks.getList());
        } catch (IOException e) {
            System.out.println(" OOPS!!! Something went wrong while saving: " + e.getMessage());
        }

        ui.showTaskDeleteMessage(removedtask, tasks.getSize());
    }
}
