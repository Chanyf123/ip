package bao;

import bao.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interface interactions for the Bao application.
 * This class is responsible for displaying messages,
 * errors, and task information to the console.
 */
public class Ui {

    //UI Strings
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String USAGE_GUIDE = """
             Here is a quick guide on how to use Bao:
              - todo <task description>
              - deadline <task description> /by <date/time>
              - event <event description> /from <start> /to <end>
              - list : view all added tasks
              - mark/unmark <index> : change task status
              - delete <index> : remove a task from the list
              - find <keyword> : finds a task with the given keyword from the list
              - bye : exit the program\
            """;
    public static final String MSG_ADD_TASK = " Got it. I've added this task:";
    public static final String MSG_TASK_COUNT_PRE = " Now you have ";
    public static final String MSG_TASK_COUNT_POST = " tasks in the list.";
    public static final String MSG_MARK_DONE = " Nice! I've marked this task as done: ";
    public static final String MSG_MARK_UNDONE = " OK, I've marked this task as not done yet: ";
    public static final String MSG_TASK_LIST = " Here are the tasks in your list:";
    public static final String MSG_BYE = "Bye. Hope to see you again soon!";
    public static final String MSG_TASK_REMOVE = " Noted. I've removed this task:";
    public static final String MSG_HELLO = "Hello! I'm Bao";
    public static final String MSG_ASSISTANCE = "What can I do for you?";
    public static final String BAO_LOGO = """
                  (  (  ( \s
                   )  )  )
                  _________
                 /   \\|/   \\
                |  o     o  |
                 \\____V____/\
            """;
    public static final String MSG_NO_MATCHING_TASKS = "No matching tasks found. Did you really add that task @o@";
    public static final String MSG_MATCHING_TASKS_FOUND = "Hopefully I have found what you are looking for:";


    private Scanner in;

    /**
     * Initialises a new Ui instance with a <code>Scanner</code> for reading system input.
     */
    public Ui(){
        this.in = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The raw string entered by the user.
     */
    public String readUserInput(){
        return in.nextLine();
    }

    /**
     * Displays a horizontal divider line to the console.
     */
    public void showHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }


    public void showWelcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(MSG_HELLO);
        System.out.println(BAO_LOGO);
        System.out.println(MSG_ASSISTANCE);
        System.out.println(USAGE_GUIDE);
        System.out.println(HORIZONTAL_LINE);
    }

    public void showExitMessage() {
        System.out.println(MSG_BYE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the full list of tasks to the user.
     *
     * @param tasks The {@link ArrayList} of tasks to be printed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println(MSG_TASK_LIST);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Confirms to the user that a task has been successfully added.
     *
     * @param addedTask The task that was just created.
     * @param currentListSize The total number of tasks after the addition.
     */
    public void showTaskAddedResponse(Task addedTask, int currentListSize) {
        System.out.println(MSG_ADD_TASK);
        System.out.println("   " + addedTask.toString());
        System.out.println(MSG_TASK_COUNT_PRE + currentListSize + MSG_TASK_COUNT_POST);
    }

    /**
     * Confirms to the user that a task has been successfully removed.
     *
     * @param removedTask The task that was just deleted.
     * @param currentListSize The total number of tasks remaining.
     */
    public void showTaskDeleteMessage(Task removedTask, int currentListSize) {
        System.out.println(MSG_TASK_REMOVE);
        System.out.println("   " + removedTask.toString());
        System.out.println(MSG_TASK_COUNT_PRE + currentListSize + MSG_TASK_COUNT_POST);
    }

    public void showTaskMarkDoneMessage(Task task) {
        System.out.println(MSG_MARK_DONE +  task.toString());
    }

    public void showTaskMarkUndoneMessage(Task task) {
        System.out.println(MSG_MARK_UNDONE +  task.toString());
    }

    public void showFileLoadedMessage(String filePath) {
        System.out.println("file with path: " + filePath + " loaded successfully :)");
    }

    public void showFileLoadingError(String filePath) {
        System.out.println("file with path: " + filePath + " loaded unsuccessfully :(");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a list of tasks that match the search keyword.
     * If no tasks are found, a "no match" message is shown.
     *
     * @param results The list of tasks containing the keyword.
     */
    public void showMatchingTasks(ArrayList<Task> results) {
        if (results.isEmpty()) {
            System.out.println(MSG_NO_MATCHING_TASKS);
        }
        else {
            System.out.println(MSG_MATCHING_TASKS_FOUND);
            for (int i = 0; i < results.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + results.get(i).toString());
            }
        }
    }

}
