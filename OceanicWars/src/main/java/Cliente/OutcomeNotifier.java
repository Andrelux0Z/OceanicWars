package Cliente;

import javax.swing.JOptionPane;

/**
 * Muestra popups de resultado (victoria / derrota) basados en mensajes RESULT.
 */
public final class OutcomeNotifier {
    private static boolean mostradoWin = false;
    private static boolean mostradoLose = false;

    private OutcomeNotifier() {
    }

    public static void handleResultMessage(Client client, String msg) {
        if (client == null || msg == null)
            return;
        String m = msg.toUpperCase();
        if (!mostradoWin && m.contains("GANASTE")) {
            mostradoWin = true;
            try {
                JOptionPane.showMessageDialog(client.getRefFrame(), "¡Has ganado!", "Victoria",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ignored) {
            }
        } else if (!mostradoLose && m.contains("PERDISTE")) {
            mostradoLose = true;
            try {
                JOptionPane.showMessageDialog(client.getRefFrame(), "Has perdido", "Derrota",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ignored) {
            }
        }
    }
}
