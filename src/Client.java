import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Client { //Client class
    private static String IPadress;
    final private static int port = 5000;

    static void Start() throws IOException {
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
        Scanner sc = new Scanner(System.in);
        IPadress = sc.nextLine();
    }
}
