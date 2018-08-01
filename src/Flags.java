import java.util.Vector;

class Flags { //Settings and tech stuff
    static int serverstage = 0;
    static int clientstage = 0;
    static boolean used = false;
    private static int keycode = -50;
    private static Vector<Integer> curpress = new Vector<>();

    static synchronized Vector<Integer> getbuttons() {
        return curpress;
    }

    static synchronized void keyaction(boolean isPressed, int keycode) {
        if (isPressed) {
            if (curpress.indexOf(keycode) == -1) {
                curpress.add(keycode);
            }
        } else {
            curpress.remove((Integer) keycode);
        }
    }
}
