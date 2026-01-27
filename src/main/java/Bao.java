import java.util.Scanner;

public class Bao {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";
        String[] list = new String[100];
        int itemCount = 0;


        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Bao");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String userInput;
        Scanner in = new Scanner(System.in);

        while (true){
            userInput = in.nextLine();
            System.out.println(horizontalLine);

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + list[i]);
                }
            } else{
                list[itemCount] = userInput;
                itemCount++;
                System.out.println(" added: " + userInput);
            }
            System.out.println(horizontalLine);
        }
    }
}
