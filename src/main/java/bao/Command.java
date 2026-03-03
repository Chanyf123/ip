package bao;

import bao.Ui;
import bao.Storage;
import bao.BaoException;
import bao.task.Task;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BaoException;

    public boolean isExit() {
        return false;
    }
}
