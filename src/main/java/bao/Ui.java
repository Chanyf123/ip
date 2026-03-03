package bao;

import bao.task.Task;

import java.util.ArrayList;
import java.util.Scanner;


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
              - bye : exit the program\
            """;
    public static final String MSG_ADD_TASK = " Got it. I've added this task:";
    public static final String MSG_TASK_COUNT_PRE = " Now you have ";
    public static final String MSG_TASK_COUNT_POST = " tasks in the list.";
    public static final String MSG_MARK_DONE = " Nice! I've marked this task as done:";
    public static final String MSG_MARK_UNDONE = " OK, I've marked this task as not done yet:";
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


    private Scanner in;

    public Ui(){
        this.in = new Scanner(System.in);
    }

    public String readUserInput(){
        return in.nextLine();
    }

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

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println(MSG_TASK_LIST);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void showTaskAddedResponse(Task addedtask, int currentListSize) {
        System.out.println(MSG_ADD_TASK);
        System.out.println("   " + addedtask.toString());
        System.out.println(MSG_TASK_COUNT_PRE + currentListSize + MSG_TASK_COUNT_POST);
    }

    public void showTaskDeleteMessage(Task removedTask, int currentListSize) {
        System.out.println(MSG_TASK_REMOVE);
        System.out.println("   " + removedTask.toString());
        System.out.println(MSG_TASK_COUNT_PRE + currentListSize + MSG_TASK_COUNT_POST);
    }

    public void showTaskMarkDoneMessage(Task removedTask) {
        System.out.println(MSG_MARK_DONE);
    }

    public void showTaskMarkUndoneMessage(Task removedTask) {
        System.out.println(MSG_MARK_UNDONE);
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

}
