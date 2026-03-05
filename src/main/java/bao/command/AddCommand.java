package bao.command;

import bao.Storage;
import bao.TaskList;
import bao.Ui;
import bao.task.Task;

import java.io.IOException;

/**
 * Represents the command to add a task to the task list.
 * This command handles the addition of Todo, Deadline, and Event tasks,
 * and ensures the updated list is saved to the local data file.
 */
public class AddCommand extends Command {
    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the addition of the task to the task list to
     * the {@link TaskList}, saves the updated list via {@link Storage},
     * and displays a confirmation message through the {@link Ui}.
     *
     * @param tasks   The list where the task will be added.
     * @param ui      The user interface for displaying confirmation.
     * @param storage The storage system for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(taskToAdd);

        try {
            storage.save(tasks.getList());
        } catch (IOException e) {
            System.out.println(" OOPS!!! Something went wrong while saving: " + e.getMessage());
        }

        ui.showTaskAddedResponse(taskToAdd, tasks.getSize());
    }
}
