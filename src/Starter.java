import java.util.Scanner;

public class Starter { //Class used for starting application
    public static void main(String[] args) {
        System.out.println("Are you client or server?");
        while (true) {
            System.out.println("Write either client or server");
            Scanner s = new Scanner(System.in);
            String a = s.nextLine();
            if (a.equalsIgnoreCase("client")) {
                Client.Start();
            } else if (a.equalsIgnoreCase("server")) {

            } else {
                System.out.println("Enter a correct word");
            }
        }
    }
}
