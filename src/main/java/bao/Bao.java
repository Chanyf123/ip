package bao;

import bao.task.Deadline;
import bao.task.Event;
import bao.task.Task;
import bao.task.Todo;
import bao.Ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Bao {

    //private Storage storage = new Storage("data/bao.txt");
    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    public Bao(String filePath) {
        Ui ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = storage.load();
            ui.showFileLoadedMessage(filePath);
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<>();
            ui.showFileLoadingError(filePath);
        }
    }

    public static void main(String[] args) {


        showWelcomeMessage();
        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = in.nextLine();
            if (userInput.trim().isEmpty()) continue; // Skip empty inputs
            String command = userInput.split(" ")[0].toLowerCase(); //Splitting to get the command user input (e.g., "todo", "deadline") in lowercase

            try {
                System.out.println(HORIZONTAL_LINE);

                switch (command) {
                case "bye":
                    showExitMessage();
                    return; // Terminate main method and exit the program
                case "list":
                    showTaskList();
                    break;
                case "mark":
                    handleMarkStatus(userInput, true);
                    break;
                case "unmark":
                    handleMarkStatus(userInput, false);
                    break;
                case "todo":
                    addToDo(userInput);
                    break;
                case "deadline":
                    addDeadline(userInput);
                    break;
                case "event":
                    addEvent(userInput);
                    break;
                case "delete":
                    deleteTask(userInput);
                    break;
                default:
                    throw new BaoException("I'm sorry, but I don't know what '" + command + "' means :(\n" + USAGE_GUIDE);
                }
            } catch (BaoException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(" OOPS!!! " + BaoException.INVALID_NUM);
            } finally {
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }

    private static void updateStorage() {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println(" OOPS!!! Something went wrong while saving: " + e.getMessage());
        }
    }

    private static void addToDo(String input) throws BaoException {
        // Check if user just typed "todo" without description
        String description = input.length() > 4 ? input.substring(5).trim() : "";
        if (description.isEmpty()) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }

        Task newTodo = new Todo(description);
        tasks.add(newTodo);
        updateStorage();
        showTaskAddedResponse(newTodo);
    }

    private static void addDeadline(String input) throws BaoException {
        // Check if user input is missing "/by" or without any description
        if (input.trim().length() <= 8) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }
        if (!input.contains(" /by ")) {
            throw new BaoException(BaoException.MISSING_BY);
        }
        String[] parts = input.substring(9).trim().split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }

        Task newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(newDeadline);
        updateStorage();
        showTaskAddedResponse(newDeadline);
    }

    private static void addEvent(String input) throws BaoException {
        // Check if user did not include "/from" or "/to" or without any description
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new BaoException(BaoException.MISSING_EVENT_INFO);
        }
        String[] parts = input.substring(6).split(" /from | /to ", 3);
        if (parts.length < 3 || parts[0].trim().isEmpty()) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }

        Task newEvent = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        tasks.add(newEvent);
        updateStorage();
        showTaskAddedResponse(newEvent);
    }

    private static void deleteTask(String input) throws BaoException{
        // Check if user entered a valid task number or if number is out of range
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new BaoException(BaoException.INVALID_NUM);
        }

        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new BaoException(BaoException.OUT_OF_BOUNDS);
        }

        Task removedTask = tasks.remove(index);
        updateStorage();
        showTaskDeleteMessage(removedTask);
    }

    private static void handleMarkStatus(String input, boolean isDone) throws BaoException {
        // Check if user entered a valid task number or if number is out of range
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new BaoException(BaoException.INVALID_NUM);
        }

        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new BaoException(BaoException.OUT_OF_BOUNDS);
        }

        if (isDone) {
            tasks.get(index).markAsDone();
            System.out.println(MSG_MARK_DONE);
        } else {
            tasks.get(index).markAsNotDone();
            System.out.println(MSG_MARK_UNDONE);
        }
        updateStorage();
        System.out.println("   " + tasks.get(index).toString());
    }

}
