import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

class GUI extends JFrame { //GUI class for starting application
    private JButton sbut = new JButton();
    private JButton cbut = new JButton();
    private Container container = getContentPane();
    private JLabel jl = new JLabel();
    private JPanel jp = new JPanel();

    private void serveraction(boolean isLocal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("я тут");
                cbut.setText("Exit");
                System.out.println("я тут2");
                try {
                    ServerSocket ss = new ServerSocket(5000);
                    String a;
                    Robot r = new Robot();
                    int state;
                    int code;
                    jl.setText("This is a server IP: " + getIP(isLocal) + "Waiting for client to connect...");
                    Socket s = ss.accept();
                    jl.setText("Connected! Waiting for input...");
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    while (true) {
                        a = br.readLine();
                        jl.setText("Got input! Here is the code: " + a);
                        code = Integer.parseInt(a.substring(0, a.indexOf(' ')));
                        state = Integer.parseInt(a.substring(a.indexOf(' ')));
                        if (state == 0) {
                            r.keyPress(code);
                        } else {
                            r.keyRelease(code);
                        }
                    }
                } catch (Exception ignored) {
                }
            }
        }).start();
    }

    private void clientaction() {

    }

    GUI() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 400));
        setMaximumSize(new Dimension(600, 400));
        sbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Flags.serverstage == 0) {
                    Flags.serverstage++;
                    sbut.setText("Local");
                    cbut.setText("Global");
                    jl.setText("Set type of your server");
                } else {
                    serveraction(true);
                }
            }
        });
        cbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Flags.serverstage == 1) {
                    serveraction(false);
                }
                clientaction();
            }
        });
        jp.add(jl, BorderLayout.CENTER);
        jl.setText("Choose your side");
        sbut.setText("Server");
        cbut.setText("Client");
        container.setLayout(new BorderLayout());
        container.add(sbut, BorderLayout.LINE_START);
        container.add(cbut, BorderLayout.LINE_END);
        container.add(jp, BorderLayout.CENTER);
    }

    private String getIP(boolean isLocal) {
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
