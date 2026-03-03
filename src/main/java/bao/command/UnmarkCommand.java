package bao.command;

import bao.BaoException;
import bao.Storage;
import bao.TaskList;
import bao.Ui;
import bao.task.Task;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

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
