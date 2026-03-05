package bao.command;

import bao.Storage;
import bao.TaskList;
import bao.Ui;

/**
 * Represents a command to display all tasks currently in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list display logic.
     * Retrieves the current list from {@link TaskList} and passes it to
     * the {@link Ui} for formatting and display.
     *
     * @param tasks   The list containing the tasks to be displayed.
     * @param ui      The user interface used to show the task list.
     * @param storage The storage system.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTaskList(tasks.getList());
    }
}
