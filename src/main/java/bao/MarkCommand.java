package bao;

import bao.task.Task;

import java.io.IOException;

public abstract class MarkCommand extends Command {
    private final int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

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
