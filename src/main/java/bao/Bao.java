package bao;

import bao.task.Deadline;
import bao.task.Event;
import bao.task.Task;
import bao.task.Todo;
import java.util.Scanner;

public class Bao {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final int MAX_NUM_OF_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_NUM_OF_TASKS];
    private static int taskCount = 0;

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
                default:
                    throw new BaoException("I'm sorry, but I don't know what '" + command + "' means :(\n" +
                            " Here is a quick guide on how to use Bao:\n" +
                            "  - todo <task description>\n" +
                            "  - deadline <task description> /by <date/time>\n" +
                            "  - event <event description> /from <start> /to <end>\n" +
                            "  - list : view all added tasks\n" +
                            "  - mark/unmark <index> : change task status\n" +
                            "  - bye : exit the program");
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

    private static void addToDo(String input) throws BaoException {
        // Check if user just typed "todo" without description
        String description = input.length() > 4 ? input.substring(5).trim() : "";
        if (description.isEmpty()) {
            throw new BaoException(BaoException.DESC_EMPTY);
        }

        tasks[taskCount] = new Todo(description);
        taskCount++;
        showTaskAddedResponse();
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

        tasks[taskCount] = new Deadline(parts[0].trim(), parts[1].trim());
        taskCount++;
        showTaskAddedResponse();
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

        tasks[taskCount] = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        taskCount++;
        showTaskAddedResponse();
    }

    private static void handleMarkStatus(String input, boolean isDone) throws BaoException {
        // Check if user entered a valid task number or if number is out of range
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new BaoException(BaoException.INVALID_NUM);
        }

        int index = Integer.parseInt(parts[1]) - 1;
        if (index < 0 || index >= taskCount) {
            throw new BaoException(BaoException.OUT_OF_BOUNDS);
        }

        if (isDone) {
            tasks[index].markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            tasks[index].markAsNotDone();
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + tasks[index].toString());
    }

    private static void showTaskAddedResponse() {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks[taskCount - 1].toString());
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    private static void showTaskList() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i].toString());
        }
    }

    private static void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void showWelcomeMessage() {
        String logo = """
                      (  (  ( \s
                       )  )  )
                      _________
                     /   \\|/   \\
                    |  o     o  |
                     \\____V____/\
                """;
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bao");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }
}
