import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

class Server { //Server class
    static void Start(boolean isLocal) throws IOException, AWTException {
        ServerSocket ss = new ServerSocket(5000);
        String a;
        Robot r = new Robot();
        int state;
        int code;
        System.out.println("This is a server IP: " + getIP(isLocal));
        System.out.println("Just send it to client!");
        System.out.println("Waiting for client to connect...");
        Socket s = ss.accept();
        System.out.println("Connected! Waiting for input...");
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        while (true) {
            a = br.readLine();
            System.out.println(a);
            code = Integer.parseInt(a.substring(0, a.indexOf(' ')));
            state = Integer.parseInt(a.substring(a.indexOf(' ')));
            if (state == 0) {
                r.keyPress(code);
            } else {
                r.keyRelease(code);
            }
        }
    }

    private static String getIP(boolean isLocal) {
        String ip = "";
        if (isLocal) {
            try (final DatagramSocket socket = new DatagramSocket()) {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                ip = socket.getLocalAddress().getHostAddress();
            } catch (Exception e) {
                System.out.println("Error!");
                System.exit(0);
            }
        } else {
            try {
                URL myip = new URL("http://checkip.amazonaws.com");
                BufferedReader in = new BufferedReader(new InputStreamReader(myip.openStream()));
                ip = in.readLine();
            } catch (Exception ignored) {
            }
        }
        return ip;
    }
}
