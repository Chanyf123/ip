package bao;

import bao.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BaoException {
        Task removedTask = tasks.deleteTask(taskId);

        try {
            storage.save(tasks.getList());
        } catch (IOException e) {
            System.out.println(" OOPS!!! Something went wrong while saving: " + e.getMessage());
        }

        ui.showTaskDeleteMessage(removedTask, tasks.getSize());
    }
}
