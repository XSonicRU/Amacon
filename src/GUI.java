import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

class GUI { //Class used for starting application
    GUI() throws IOException, AWTException {
        System.out.println("Are you client or server?");
        while (true) {
            System.out.println("Write either client or server");
            Scanner s = new Scanner(System.in);
            String a = s.nextLine();
            if (a.equalsIgnoreCase("client")) {
                Client c = new Client();
                c.setIPadress(""); //TODO
                break;
            } else if (a.equalsIgnoreCase("server")) {
                System.out.println("Is server local or global?");
                System.out.println("Write either local or global");
                a = s.nextLine();
                Server server = new Server();
                if (a.equalsIgnoreCase("global")) {
                    server.Start(false);
                } else if (a.equalsIgnoreCase("local")) {
                    server.Start(true);
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
