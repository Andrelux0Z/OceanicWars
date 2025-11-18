/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Casilla;
import Cliente.Jugador;
import Cliente.popupThree;
import Hero.Hero;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author kokoju
 */

public class ThreeNumbers extends Ataque {
    // Atributos
    private ArrayList<Integer> numerosElegidos; // Arreglo para recorrer cada número elegido
    // No puede usarse un array con tipos primitivos (como lo es el int): solo
    // acepta objetos

    // TODO: COMPROBACIÓN AL RECIBIR COMANDO DE CONSEGUIR INTS DE 0 A 9 (PARA DARLE
    // FALLA AL USER)

    // Constructor
    public ThreeNumbers(Hero hero, Jugador contrincante, ArrayList<Integer> numerosElegidos) {
        // TODO: CAMBIAR EL HERO DE CONTRINCANTE POR UNA CLASE "JUGADOR", QUE REAGRUPE
        // SU MATRIZ Y SUS 3 HÉROES
        super(hero, contrincante);
        this.numerosElegidos = numerosElegidos; // Se asignan los números elegidos (podría incluso tenerse una
                                                // construcción escalable)
    }

    // Métodos

    @Override
    public void ejecutar() {
        // Validar números del atacante
        if (this.numerosElegidos == null || this.numerosElegidos.size() < 3) {
            System.out.println("ERROR: ThreeNumbers requiere exactamente 3 números");
            return;
        }

        for (Integer n : this.numerosElegidos) {
            if (n == null || n < 0 || n > 9) {
                System.out.println("ERROR: Números del atacante deben estar entre 0 y 9");
                return;
            }
        }

        // Pedir los 3 números al jugador atacado usando el popup en EDT
        final int[] seleccionDefensor = new int[3];
        final boolean[] seleccionValida = new boolean[1];
        seleccionValida[0] = false;

        try {
            SwingUtilities.invokeAndWait(() -> {
                java.awt.Component parent = null;
                try {
                    // Intentar obtener el componente padre del contrincante (FrameClient)
                    parent = contrincante.getMatriz().getRefCliente();
                } catch (Exception ignore) {
                    // Si no se puede obtener el componente padre del contrincante, intentar del
                    // hero
                    try {
                        parent = hero.getParentComponent();
                    } catch (Exception e) {
                    }
                }

                popupThree popup = new popupThree(parent);

                if (popup.isSeleccionValida()) {
                    int[] arr = popup.getNumerosArray();
                    seleccionDefensor[0] = arr[0];
                    seleccionDefensor[1] = arr[1];
                    seleccionDefensor[2] = arr[2];
                    seleccionValida[0] = true;
                }
            });
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo mostrar el popup al defensor - " + e.getMessage());
            seleccionValida[0] = false;
        }

        if (!seleccionValida[0]) {
            System.out.println("El defensor canceló la selección de números");
            return;
        }

        // Mostrar los números elegidos (para debug)
        System.out.println("Números del atacante: " + numerosElegidos.get(0) + ", " + numerosElegidos.get(1) + ", "
                + numerosElegidos.get(2));
        System.out.println("Números del defensor: " + seleccionDefensor[0] + ", " + seleccionDefensor[1] + ", "
                + seleccionDefensor[2]);

        // Comprobar coincidencias: verificar si algún número del atacante coincide con
        // alguno del defensor
        boolean hayCoincidencia = false;
        for (Integer numAtacante : this.numerosElegidos) {
            for (int j = 0; j < 3; j++) {
                if (numAtacante == seleccionDefensor[j]) {
                    hayCoincidencia = true;
                    System.out.println(
                            "¡Coincidencia encontrada! El número " + numAtacante + " está en ambas selecciones");
                    break;
                }
            }
            if (hayCoincidencia)
                break;
        }

        if (!hayCoincidencia) {
            System.out.println("No hay coincidencias. El ataque no tiene efecto.");
            return;
        }

        // Calcular cantidad de casillas a destruir: producto de los 3 números del
        // atacante
        int cantidadADestruir = numerosElegidos.get(0) * numerosElegidos.get(1) * numerosElegidos.get(2);

        System.out.println("Producto de números del atacante: " + numerosElegidos.get(0) + " × "
                + numerosElegidos.get(1) + " × " + numerosElegidos.get(2) + " = " + cantidadADestruir);

        if (cantidadADestruir <= 0) {
            System.out.println("El producto es 0 o negativo, no se destruyen casillas");
            return;
        }

        // Aplicar daños a casillas aleatorias
        int casillasDestruidas = 0;
        for (int i = 0; i < cantidadADestruir; i++) {
            if (matriz.getCasillasActivas() == null || matriz.getCasillasActivas().isEmpty()) {
                System.out.println("No hay más casillas activas para destruir");
                break;
            }

            Casilla c = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));
            int vidaAntes = c.getVida();
            c.recibirGolpe(c.getVida());
            c.getBitacora()
                    .add("La casilla (" + c.getX() + ", " + c.getY()
                            + ") fue destruida por el ataque 'Three Numbers' de " + hero.getNombre() + ", recibiendo "
                            + vidaAntes + " puntos de daño");
            casillasDestruidas++;
        }

        System.out.println("ThreeNumbers ejecutado: " + casillasDestruidas + " casillas destruidas de "
                + cantidadADestruir + " intentadas");
    }

    // Getters
    public Jugador getContrincante() {
        return contrincante;
    }

    public ArrayList<Integer> getNumerosElegidos() {
        return numerosElegidos;
    }

}
