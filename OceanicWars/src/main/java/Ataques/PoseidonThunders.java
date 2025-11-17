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

public class PoseidonThunders extends Ataque {
    // Atributos
    private int cantidadRayos; // Cantidad de rayos que se lanzan

    // Constructor
    public PoseidonThunders(Hero hero, Jugador contrincante) {
        super(hero, contrincante);
        this.cantidadRayos = (int) ((5 + rand.nextInt(6)) * (1 + hero.getFuerzaAtaque() / 100.0)); // Crea de 5 a 10
                                                                                                   // rayos (porque
                                                                                                   // nextInt va hasta
                                                                                                   // n-1) * fuerza del
                                                                                                   // héroe
    }

    // Métodos
    @Override
    public void ejecutar() {
        // Cantidad de rayos generada
        // Al ser un ataque "Instakill", el elemento que aumenta la fuerza será la
        // cantidad de rayos, no su alcance
        for (int i = 0; i < this.cantidadRayos; i++) {
            // Elección de radio
            int radioObtenido = (2 + rand.nextInt(9)); // Radio de 2 hasta 10

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
                int danoEpicentro = casilla.getVida(); // Recibe el mismo daño de su vida ('instakill')
                casilla.recibirGolpe(danoEpicentro);
                casilla.getBitacora()
                    .add("La casilla (" + casilla.getX() + ", " + casilla.getY()
                        + ") fue seleccionada como epicentro de una onda del 'Poseidon Thunders' de "
                        + hero.getNombre() + ", recibiendo " + danoEpicentro + " puntos de daño"); // Mensaje
                                                                                                           // agregado a
                                                                                                           // la
                                                                                                           // bitácora
                                                                                                           // de la
                                                                                                           // casilla

            for (Casilla c : matriz.getCasillasActivas()) { // Revisamos en las casillas activas
                if (matriz.IsCasillaEnRadio(casilla.getX(), casilla.getY(), c.getX(), c.getY(), radioObtenido)) {
                        int dano = c.getVida(); // Recibe el mismo daño de su vida ('instakill')
                        c.recibirGolpe(dano);
                        c.getBitacora()
                            .add("La casilla (" + c.getX() + ", " + c.getY()
                                + ") fue golpeada por una onda expansiva del 'Poseidon Thunders' de "
                                + hero.getNombre() + ", recibiendo " + dano + " puntos de daño"); // Mensaje
                                                                                                             // agregado
                                                                                                             // a la
                                                                                                             // bitácora
                                                                                                             // de la
                                                                                                             // casilla
                }
            }
        }
    }

    // Getters
    public int getCantidadRayos() {
        return cantidadRayos;
    }
}
