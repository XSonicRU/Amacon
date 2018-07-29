import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class Client { //Client class
    private static String IPadress;
    final private static int port = 5000;

    static void Start() throws IOException {
        if (InetAddress.getByName(IPadress + ':' + port).isReachable(200)) {
            System.out.println("Found IP!");
        } else {
            System.out.println("No such IP!");
            Starter.main(new String[0]);
        }

        Socket chat = new Socket(IPadress, port);
        if (chat.isBound()) {
            System.out.println("Connected");
        } else {
            System.out.println("Failed");
            System.exit(0);
        }
        PrintWriter writer = new PrintWriter(chat.getOutputStream());
    }

    public static void setIPadress() {
        System.out.println("Enter server IP adress:");
        Scanner sc = new Scanner(System.in);
        IPadress = sc.nextLine();
    }
}
