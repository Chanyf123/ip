package bao;

import bao.task.Task;
import bao.TaskList;

import java.io.IOException;

public class AddCommand extends Command {
    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

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
