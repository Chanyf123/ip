import java.util.Scanner;

public class Bao {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final int MAX_NUM_OF_TASKS = 100;

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_NUM_OF_TASKS];
        int taskCount = 0;

        showWelcomeMessage();

        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = in.nextLine();
            System.out.println(HORIZONTAL_LINE);

            if (userInput.equalsIgnoreCase("bye")) {
                showExitMessage();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                showTaskList(taskCount, tasks);
            } else if (userInput.startsWith("mark ")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                tasks[index].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" [" + tasks[index].getStatusIcon() + "] " +  tasks[index].description);
            } else if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                tasks[index].markAsNotDone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(" [" + tasks[index].getStatusIcon() + "] " +  tasks[index].description);
            } else {
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println(" added: " + userInput);
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    /**
     * Shows the task list to the user.
     * This will be in a well formatted way.
     */
    private static void showTaskList(int taskCount, Task[] tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
    }

    /**
     * Shows the exit message to the user.
     * This will be in a well formatted way.
     */
    private static void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows the welcome message to the user.
     * This will be in a well formatted way.
     */
    private static void showWelcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bao");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }
}
