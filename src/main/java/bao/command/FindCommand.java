package bao.command;

import bao.Storage;
import bao.TaskList;
import bao.Ui;
import bao.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> results = new ArrayList<>();
        int size = tasks.getSize();

        for (int i = 0; i < size; i++) {
            Task task = tasks.getTask(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }

        ui.showMatchingTasks(results);
    }
}
