package bao.command;

import bao.BaoException;
import bao.Storage;
import bao.TaskList;
import bao.Ui;
import bao.task.Task;

import java.util.ArrayList;

/**
 * Represents a command to search for tasks within the task list.
 * This command filters tasks whose string representation contains a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the search logic.
     * Iterates through all tasks and checks if their string representation
     * contains the keyword (case-insensitive). Results are displayed via the {@link Ui}.
     *
     * @param tasks   The list of tasks to be searched.
     * @param ui      The user interface for displaying the search results.
     * @param storage The storage system.
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) throws BaoException {
        ArrayList<Task> results = new ArrayList<>();
        int size = tasks.getSize();

        // Perform a case-insensitive search through all tasks
        for (int i = 0; i < size; i++) {
            Task task = tasks.getTask(i);
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(task);
            }
        }

        ui.showMatchingTasks(results);
    }
}
