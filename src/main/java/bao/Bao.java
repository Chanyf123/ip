package bao;

import bao.command.Command;

import java.io.FileNotFoundException;

/**
 * Represents the main entry point for the Bao task management application.
 * It initializes the core components (UI, Storage, and TaskList) and manages the
 * main program loop.
 */
public class Bao {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes a new Bao instance with a specific data file path.
     * Attempts to load existing tasks from the specified file.
     * If the file is not found, starts with an empty task list.
     *
     * @param filePath The file path where task data is stored and loaded from.
     */
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

    /**
     * Starts the main application loop.
     * Continues to read user input, parse commands, and execute them until
     * an exit command is received.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readUserInput();
                ui.showHorizontalLine();

                // Parse user input into command object to determine command name
                Command c = Parser.parseUserInput(userInput);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BaoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showHorizontalLine();
            }
        }
    }

    /**
     * Main method to launch the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Bao("data/bao.txt").run();
    }

}
