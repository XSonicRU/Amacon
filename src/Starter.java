import java.io.IOException;
import java.util.Scanner;

public class Starter { //Class used for starting application
    public static void main(String[] args) throws IOException {
        System.out.println("Are you client or server?");
        while (true) {
            System.out.println("Write either client or server");
            Scanner s = new Scanner(System.in);
            String a = s.nextLine();
            if (a.equalsIgnoreCase("client")) {
                Client.setIPadress();

            } else if (a.equalsIgnoreCase("server")) {
                System.out.println("Is server local or global?");
                System.out.println("Write either local or global");
                a = s.nextLine();
                if (a.equalsIgnoreCase("global")) {
                    Server.Start(false);
                } else if (a.equalsIgnoreCase("local")) {
                    Server.Start(true);
                } else {
                    System.out.println("Enter a correct word");
                    continue;
                }
                break;
            } else {
                System.out.println("Enter a correct word");
            }
        }
    }
}
