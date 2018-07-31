class Flags { //Settings and tech stuff
    static int serverstage = 0;
    static int clientstage = 0;
    static boolean used = false;
    private static int keycode = -50;

    static synchronized int getKeycode() {
        return keycode;
    }

    static synchronized void setKeycode(int key) {
        keycode = key;
    }
}
