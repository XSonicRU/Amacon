import java.util.Vector;

class Flags { //Settings and tech stuff
    static int serverstage = 0;
    static int clientstage = 0;
    static boolean used = false;
    private static int keycode = -50;
    private static Vector<Integer> curpress = new Vector<>();

    static synchronized int getKeycode() {
        if (keycode != 50) {
            int buf = keycode;
            keycode = -50;
            return buf;
        } else {
            return -50;
        }
    }

    static synchronized Vector getbuttons() {
        return curpress;
    }

    static synchronized void keyaction(boolean isPressed, int keycode) {
        if (isPressed) {
            if (curpress.indexOf(keycode) == -1) {
                curpress.add(keycode);
            }
        } else {
            curpress.remove(keycode);
        }
    }

    static synchronized void setKeycode(int key) {
        keycode = key;
    }
}
