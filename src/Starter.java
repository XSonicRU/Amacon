public class Starter {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Amacon g = new Amacon();
                } catch (Exception ignored) {
                }
            }
        });
    }
}
