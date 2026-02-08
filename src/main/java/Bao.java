import java.util.Scanner;

public class Bao {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final int MAX_NUM_OF_TASKS = 100;
    private static Task[] tasks = new Task[MAX_NUM_OF_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        showWelcomeMessage();
        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = in.nextLine();
            String command = userInput.split(" ")[0].toLowerCase(); //Splitting to get the command user input (e.g., "todo", "deadline") in lowercase
            System.out.println(HORIZONTAL_LINE);

            switch (command) {
            case "bye":
                showExitMessage();
                return;
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
                addTask(userInput);
                break;
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    private static void addToDo(String input) {
        tasks[taskCount] = new Todo(input.substring(5));
        taskCount++;
        showTaskAddedResponse();
    }

    private static void addDeadline(String input) {
        String[] parts = input.substring(9).split(" /by ");
        tasks[taskCount] = new Deadline(parts[0], parts[1]);
        taskCount++;
        showTaskAddedResponse();
    }

    private static void addEvent(String input) {
        String[] parts = input.substring(6).split(" /from | /to ");
        tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
        taskCount++;
        showTaskAddedResponse();
    }

    private static void addTask(String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println(" added: " + input);
    }

    private static void handleMarkStatus(String input, boolean isDone) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;

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
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bao");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }
}
