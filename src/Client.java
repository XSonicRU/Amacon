import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class Client { //Client class
    private String IPadress;

    private void Start() throws IOException {
        Socket chat = new Socket(IPadress, 5000);
        if (chat.isBound()) {
            System.out.println("Connected");
        } else {
            System.out.println("Failed");
            System.exit(0);
        }
        PrintWriter writer = new PrintWriter(chat.getOutputStream());
        while (true) {
            writer.println("Hello world");
        }
    }

    void setIPadress(String addr) throws IOException {
        IPadress = addr;
        Start();
    }
}
