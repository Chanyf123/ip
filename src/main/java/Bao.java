import java.util.Scanner;

public class Bao {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Bao");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String user_input;
        Scanner in = new Scanner(System.in);

        while (true){
            user_input = in.nextLine();
            System.out.println(horizontalLine);

            if (user_input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else{
                    System.out.println(" " + user_input);
                    System.out.println(horizontalLine);
            }
        }
    }
}
