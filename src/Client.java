import java.net.Socket;
import java.util.Scanner;

class Client { //Client class
    static void Start() {
        Socket chat = new Socket();
    }

    public void setIPadress(String IPadress, String port) {
        Scanner sc = new Scanner(System.in);
        String i = sc.nextLine();
        IPadress = i;
        Scanner sr = new Scanner(System.in);
        String q = sc.nextLine();
        port = q;

    }
}
