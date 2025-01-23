import java.util.Scanner;

public class Squid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "____________________________________________________________\n";
        String greeting = " Hello! I'm Squid\n"
                        + " What can I do for you?\n";
        String goodbye = " Bye. Hope to see you again soon!\n";
        System.out.println(line + greeting + line);
        while (true) {
            if (!sc.hasNextLine()) break;
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(line + goodbye + line);
                break;
            } else {
                System.out.println(line + input + "\n" + line);
            }
        }
        sc.close();
    }
}
