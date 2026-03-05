package bao.command;

import bao.Storage;
import bao.TaskList;
import bao.Ui;

/**
 * Represents the command to exit the application.
 * When executed, it triggers the display of the exit message and signals
 * the main program loop to stop.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit sequence.
     * Displays a goodbye message to the user via the {@link Ui}.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying the exit message.
     * @param storage The storage system.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showExitMessage();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
