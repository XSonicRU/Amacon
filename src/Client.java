import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Client { //Client class
    private static String IPadress;
    final private static int port = 5000;

    static void Start() throws IOException {
        Socket chat = new Socket(IPadress, port);
        PrintWriter writer = new PrintWriter(chat.getOutputStream());
    }

    public static void setIPadress() {
        Scanner sc = new Scanner(System.in);
        String i = sc.nextLine();
        IPadress = i;


    }
}
