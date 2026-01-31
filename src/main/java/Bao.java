import java.util.Scanner;

public class Bao {
    public static void main(String[] args) {
        String HORIZONTAL_LINE = "____________________________________________________________";
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bao");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = in.nextLine();
            System.out.println(HORIZONTAL_LINE);

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". [" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
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
}
