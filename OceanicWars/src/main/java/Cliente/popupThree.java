package Cliente;

import javax.swing.JOptionPane;
import java.util.ArrayList;

//Clase para generar un popup que solicita tres números distintos del 0 al 9
//Para usar: popupThree popup = new popupThree(this);
//Abajo están los getters

public class popupThree {

    private int numero1;
    private int numero2;
    private int numero3;
    private boolean seleccionValida;

    // Constructor que muestra el popup y solicita los tres números
    public popupThree(java.awt.Component parent) {
        this.seleccionValida = false;
        mostrarPopup(parent);
    }

    // Muestra el popup y solicita los tres números distintos
    private void mostrarPopup(java.awt.Component parent) {
        ArrayList<Integer> numerosSeleccionados = new ArrayList<>();

        // Solicitar el primer número
        Integer num1 = solicitarNumero(parent, 1, numerosSeleccionados);
        if (num1 == null) {
            return;
        }
        numerosSeleccionados.add(num1);
        this.numero1 = num1;

        // Solicitar el segundo número
        Integer num2 = solicitarNumero(parent, 2, numerosSeleccionados);
        if (num2 == null) {
            return;
        }
        numerosSeleccionados.add(num2);
        this.numero2 = num2;

        // Solicitar el tercer número
        Integer num3 = solicitarNumero(parent, 3, numerosSeleccionados);
        if (num3 == null) {
            return;
        }
        this.numero3 = num3;

        this.seleccionValida = true;
    }

    // Solicita un número individual con validación
    private Integer solicitarNumero(java.awt.Component parent, int numeroOrden,
            ArrayList<Integer> numerosYaSeleccionados) {
        while (true) {
            String mensaje = "Ingresa el número #" + numeroOrden + " (0-9):";

            if (!numerosYaSeleccionados.isEmpty()) {
                mensaje += "\nNúmeros ya seleccionados: " + numerosYaSeleccionados.toString();
            }

            String input = JOptionPane.showInputDialog(
                    parent,
                    mensaje,
                    "Selección de número " + numeroOrden,
                    JOptionPane.QUESTION_MESSAGE);

            // Si el usuario cancela
            if (input == null) {
                return null;
            }

            try {
                int numero = Integer.parseInt(input.trim());

                // Validar que esté en el rango 0-9
                if (numero < 0 || numero > 9) {
                    JOptionPane.showMessageDialog(
                            parent,
                            "Error: El número debe estar entre 0 y 9.\nPor favor, intenta de nuevo.",
                            "Número Inválido",
                            JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // Validar que no esté repetido
                if (numerosYaSeleccionados.contains(numero)) {
                    JOptionPane.showMessageDialog(
                            parent,
                            "Error: El número " + numero
                                    + " ya fue seleccionado.\nPor favor, elige un número distinto.",
                            "Número Repetido",
                            JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                return numero;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        parent,
                        "Error: Debes ingresar un número entero válido.\nPor favor, intenta de nuevo.",
                        "Entrada Inválida",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Getters

    public int getNumero1() {
        return numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public int getNumero3() {
        return numero3;
    }

    public boolean isSeleccionValida() {
        return seleccionValida;
    }

    public int[] getNumerosArray() {
        return new int[] { numero1, numero2, numero3 };
    }
}
