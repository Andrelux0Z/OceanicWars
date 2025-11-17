/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Ataques.ElementosAtaques.Remolino;
import Cliente.Casilla;
import Cliente.Jugador;
import Hero.Hero;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public class SwirlRaising extends Ataque {
    // Atributos
    private Point casillaElegida;
    private int radioObtenido;

    // Constructor
    public SwirlRaising(Hero hero, Jugador contrincante, Point casillaElegida) {
        super(hero, contrincante);
        this.casillaElegida = casillaElegida; // Se asigna la casilla
        this.radioObtenido = (int) ((2 + rand.nextInt(9)) * (1 + hero.getFuerzaAtaque() / 100.0)); // Se tiene un radio
                                                                                                   // del remolino en
                                                                                                   // función de la
                                                                                                   // fuerza del héroe
                                                                                                   // (fuera del for
                                                                                                   // para mantenerse
                                                                                                   // estable)
    }

    // Métodos
    @Override
    public void ejecutar() {
        // Elección de casilla
        try {
            Casilla casilla = matriz.getMatriz()[this.casillaElegida.x][this.casillaElegida.y]; // Toma la casilla
                                                                                                // elegida por el
                                                                                                // usuario
            casilla.recibirGolpe(casilla.getVida()); // La casilla donde sale el remolino es derrotada
            casilla.setObjetoPresente(new Remolino(this.radioObtenido)); // Se crea el objeto del remolino (además de
                                                                         // darle el radio)
            casilla.getBitacora()
                    .add("La casilla (" + casilla.getX() + ", " + casilla.getY()
                            + ") fue seleccionada como lugar de apararición del remolino del 'Swirl Raising' de "
                            + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
            for (Casilla c : matriz.getCasillasActivas()) {
                if (matriz.IsCasillaEnRadio(casilla.getX(), casilla.getY(), c.getX(), c.getY(), this.radioObtenido)) { // Si
                                                                                                                       // la
                                                                                                                       // casilla
                                                                                                                       // está
                                                                                                                       // dentro
                                                                                                                       // de
                                                                                                                       // la
                                                                                                                       // onda
                                                                                                                       // de
                                                                                                                       // expansión
                    c.recibirGolpe(c.getVida());
                    c.getBitacora()
                            .add("La casilla (" + casilla.getX() + ", " + casilla.getY()
                                    + ") fue golpeada por el remolino del 'Swirl Raising' de " + hero.getNombre()
                                    + ", recibiendo " + casilla.getVida() + " puntos de daño");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
        }
    }

    // Getters
    public Point getCasillaElegida() {
        return casillaElegida;
    }

    public int getRadioObtenido() {
        return radioObtenido;
    }

}