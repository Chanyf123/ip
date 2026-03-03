package bao;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BaoException;

    public boolean isExit() {
        return false;
    }
}
