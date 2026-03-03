package bao.command;

import bao.Storage;
import bao.TaskList;
import bao.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showExitMessage();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
