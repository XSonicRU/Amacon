import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Vector;

class GUI extends JFrame { //GUI class for starting application
    private JButton sbut = new JButton();
    private JButton cbut = new JButton();
    private Container container = getContentPane();
    private JLabel jl = new JLabel();
    private JPanel jp = new JPanel();
    private JTextField jtf = new JTextField();
    private JFrame frame = this;

    private void serveraction(boolean isLocal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                cbut.setText("Exit");
                remove(sbut);
                try {
                    ServerSocket ss = new ServerSocket(5000);
                    String a;
                    StringBuilder sb = new StringBuilder();
                    char[] input;
                    Robot r = new Robot();
                    int code;
                    jl.setText("This is a server IP: " + getIP(isLocal) + ". Now waiting for client to connect...");
                    Socket s = ss.accept();
                    jl.setText("Connected! Waiting for input...");
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    while (true) {
                        a = br.readLine();
                        if (s.isBound()) {
                            jl.setText("Connected and working!");
                        } else {
                            jl.setText("Client disconnected");
                        }
                        input = a.toCharArray();
                        for (char c : input) {
                            if (Character.isDigit(c)) {
                                sb.append(c);
                            } else {
                                if (c == ' ') {
                                    r.keyPress(Integer.parseInt(sb.toString()));
                                    JOptionPane.showMessageDialog(frame, sb.toString() + "pressed!");
                                    sb.delete(0, sb.length());
                                }
                            }
                        }
                    }
                } catch (Exception ignored) {
                    System.out.println("Error");
                }
            }
        }).start();
    }

    private void clientaction() {
        jtf.setVisible(true);
        cbut.setVisible(false);
        sbut.setVisible(false);
        jl.setText("Input your friend's IP and press Enter");
    }

    GUI() {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150, 500, 300);
        sbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Flags.serverstage == 0) {
                    Flags.serverstage++;
                    sbut.setText("Local");
                    cbut.setText("Global");
                    jl.setText("Set type of your server");
                } else {
                    Flags.serverstage++;
                    serveraction(true);
                }
            }
        });
        cbut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Flags.serverstage == 1) {
                    Flags.serverstage++;
                    serveraction(false);
                } else if (Flags.serverstage == 2) {
                    System.exit(0);
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
        jtf.setVisible(false);
        container.add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Flags.used) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("FILLED! CONN TO " + jtf.getText());
                            Socket chat = null;
                            StringBuilder sb = new StringBuilder();
                            Vector<Integer> buf;
                            try {
                                chat = new Socket(jtf.getText(), 5000);
                            } catch (IOException e) {
                                System.exit(0);
                            }
                            if (chat.isBound()) {
                                jl.setText("Connected");
                            } else {
                                System.out.println("Failed");
                                System.exit(0);
                            }
                            PrintWriter writer = null;
                            try {
                                writer = new PrintWriter(chat.getOutputStream());
                            } catch (IOException err) {
                                System.exit(0);
                            }
                            while (true) {
                                buf = Flags.getbuttons();
                                for (Integer i : buf) {
                                    sb.append(i);
                                    sb.append(" ");
                                }
                                writer.println(sb.toString());
                                sb.delete(0, sb.length());
                            }
                        }
                    }).start();
                    Flags.used = true;
                }
            }
        });
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            JOptionPane.showMessageDialog(frame, "There is a problem registering your key presses");
            System.exit(0);
        }
        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

            }

            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                Flags.keyaction(true, nativeKeyEvent.getKeyCode());
            }

            @Override
            public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
                Flags.keyaction(false, nativeKeyEvent.getKeyCode());
            }
        });
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