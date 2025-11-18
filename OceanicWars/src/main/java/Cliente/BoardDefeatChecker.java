package Cliente;

import javax.swing.JOptionPane;

/**
 * Revisa si el jugador local ha perdido (todas sus casillas muertas).
 * Al detectar derrota, muestra popup y evita duplicados.
 */
public final class BoardDefeatChecker {
    private static boolean yaMostrado = false;

    private BoardDefeatChecker() {
    }

    public static void check(Client client) {
        if (client == null || client.getJugador() == null)
            return;
        if (yaMostrado)
            return;

        Matriz m = client.getJugador().getMatriz();
        if (m == null)
            return;

        boolean hayViva = false;
        for (int i = 0; i < m.getCantidadFilas(); i++) {
            for (int j = 0; j < m.getCantidadColumnas(); j++) {
                if (m.getMatriz()[i][j].getEstado()) {
                    hayViva = true;
                    break;
                }
            }
            if (hayViva)
                break;
        }

        if (!hayViva) {
            yaMostrado = true;
            try {
                JOptionPane.showMessageDialog(client.getRefFrame(), "Has perdido: todas tus casillas murieron.");
            } catch (Exception ignored) {
            }

            // Enviar GIVEUP al servidor para marcar como inactivo
            try {
                String[] args = new String[] { "GIVEUP" };
                Models.Command comando = Models.CommandFactory.getCommand(args);
                if (comando != null) {
                    client.objectSender.writeObject(comando);
                }
            } catch (Exception ignored) {
            }
        }
    }
}
