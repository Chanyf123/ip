package bao;

import bao.command.Command;

import java.io.FileNotFoundException;

public class Bao {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bao(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            ui.showFileLoadedMessage(filePath);
        } catch (FileNotFoundException e) {
            ui.showFileLoadingError(filePath);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String UserInput = ui.readUserInput();
                ui.showHorizontalLine();
                Command c = Parser.parseUserInput(UserInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BaoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showHorizontalLine();
            }
        }
    }

    public static void main(String[] args) {
        new Bao("data/bao.txt").run();
    }

}
