package bao.command;

import bao.Storage;
import bao.TaskList;
import bao.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTaskList(tasks.getList());
    }
}
