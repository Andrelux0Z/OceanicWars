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
    private ArrayList<Integer> numerosElegidos;  // Arreglo para recorrer cada número elegido
    // No puede usarse un array con tipos primitivos (como lo es el int): solo acepta objetos
    
    // TODO: COMPROBACIÓN AL RECIBIR COMANDO DE CONSEGUIR INTS DE 0 A 9 (PARA DARLE FALLA AL USER)
    
    // Constructor
    public ThreeNumbers(Hero hero, Jugador contrincante, ArrayList<Integer> numerosElegidos) {
        // TODO: CAMBIAR EL HERO DE CONTRINCANTE POR UNA CLASE "JUGADOR", QUE REAGRUPE SU MATRIZ Y SUS 3 HÉROES
        super(hero, contrincante);
        this.numerosElegidos = numerosElegidos;  // Se asignan los números elegidos (podría incluso tenerse una construcción escalable)
    }
    
    
    // Métodos

    @Override
    public void ejecutar() {
        // Validar números del atacante
        if (this.numerosElegidos == null || this.numerosElegidos.size() < 3) return;
        for (Integer n : this.numerosElegidos) {
            if (n == null || n < 0 || n > 9) return; // números invalidos: abortar
        }

        // Pedir los 3 números al jugador atacado usando el popup en EDT
        final int[] seleccion = new int[3];
        final boolean[] ok = new boolean[1];
        ok[0] = false;

        try {
            SwingUtilities.invokeAndWait(() -> {
                java.awt.Component parent = null;
                try { 
                    parent = hero.getParentComponent(); } catch (Exception ignore) {}
                    popupThree popup = new popupThree(parent);
                    
                    if (popup.isSeleccionValida()) {
                        int[] arr = popup.getNumerosArray();
                        seleccion[0] = arr[0];
                        seleccion[1] = arr[1];
                        seleccion[2] = arr[2];
                        ok[0] = true;
                    } else {
                        ok[0] = false;
                    }
            });
        } catch (Exception e) {
            ok[0] = false;
        }

        if (!ok[0]) {
            // No se obtuvo selección válida del defensor
            return;
        }

        // Comprobar coincidencias
        boolean coincidencia = false;
        for (Integer numAtacante : this.numerosElegidos) {
            for (int j = 0; j < 3; j++) {
                if (numAtacante == seleccion[j]) {
                    coincidencia = true;
                    break;
                }
            }
            if (coincidencia) break;
        }

        if (!coincidencia) {
            // No hay coincidencias: nada ocurre
            return;
        }

        // Calcular cantidad a explotar: producto de los números del atacante
        int cantidadGolpeada = 1;
        for (Integer num : this.numerosElegidos) cantidadGolpeada *= num;

        if (cantidadGolpeada <= 0) return; // si producto 0 o negativo, nada que hacer

        // Aplicar daños a casillas aleatorias
        for (int i = 0; i < cantidadGolpeada; i++) {
            if (matriz.getCasillasActivas() == null || matriz.getCasillasActivas().isEmpty()) break;
            Casilla c = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));
            int dano = c.getVida();
            c.recibirGolpe(dano);
            c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por un ataque del 'Three Numbers' de " + hero.getNombre() + ", recibiendo " + dano + " puntos de daño");
        }
    }
    
    // Getters
    public Jugador getContrincante() {
        return contrincante;
    }

    public ArrayList<Integer> getNumerosElegidos() {
        return numerosElegidos;
    }
    
}
