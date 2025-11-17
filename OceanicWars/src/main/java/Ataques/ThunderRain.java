/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Casilla;
import Cliente.Jugador;
import Hero.Hero;

/**
 *
 * @author kokoju
 */

public class ThunderRain extends Ataque {
    // Atributos
    private int golpeRayo;

    // Constructor
    public ThunderRain(Hero hero, Jugador contrincante) {
        super(hero, contrincante);
        this.golpeRayo = (int) ((10 + rand.nextInt(11)) * (1 + hero.getFuerzaAtaque() / 100.0)); // Calcula la potencia
                                                                                                 // del golpe de cada
                                                                                                 // rayo (cada uno hace
                                                                                                 // entre 10 y 20
                                                                                                 // (porque nextInt va
                                                                                                 // hasta n-1) * fuerza
                                                                                                 // del héroe
    }

    // Métodos
    @Override
    public void ejecutar() {
        for (int i = 0; i < 100; i++) { // For para hacer 100 rayos
            // Elección de casilla
            Casilla casilla = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size())); // Toma
                                                                                                                 // una
                                                                                                                 // casilla
                                                                                                                 // aleatoria
                                                                                                                 // de
                                                                                                                 // las
                                                                                                                 // que
                                                                                                                 // están
                                                                                                                 // presentes
                                                                                                                 // en
                                                                                                                 // el
                                                                                                                 // arreglo
            casilla.recibirGolpe(golpeRayo); // Golpea a la casilla
            casilla.getBitacora()
                    .add("La casilla (" + casilla.getX() + ", " + casilla.getY()
                            + ") fue golpeada por un rayo del 'Thunder Rain' de " + hero.getNombre() + ", recibiendo "
                            + golpeRayo + " puntos de daño"); // Mensaje agregado a la bitácora de la casilla
            System.out.println(casilla.getVida());
        }
    }

    // Getters
    public int getGolpeRayo() {
        return golpeRayo;
    }
}
