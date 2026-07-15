import javax.swing.SwingUtilities;
import ui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // ដំណើរការ GUI នៅលើ Event Dispatch Thread (EDT) ដើម្បីសុវត្ថិភាព Swing
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}