/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Casilla;
import Cliente.Jugador;
import Hero.Hero;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public class Tentaculos extends Ataque {
    // Atributos
    private ArrayList<Point> casillasElegidas; // Arreglo para recorrer cada casilla elegida
    private int radioObtenido;

    // Constructor
    public Tentaculos(Hero hero, Jugador contrincante, ArrayList<Point> casillasElegidas) {
        super(hero, contrincante);
        this.casillasElegidas = casillasElegidas; // Se asignan las casillas elegidas (podría incluso tenerse una
                                                  // construcción escalable)
        this.radioObtenido = (int) (1 + (4 * hero.getFuerzaAtaque() / 100.0)); // Se tiene un radio de tentáculos en
                                                                               // función de la fuerza del héroe SIN
                                                                               // DECIMALES (fuera del for para
                                                                               // mantenerse estable)
    }

    // Métodos
    @Override
    public void ejecutar() {
        for (Point punto : casillasElegidas) {
            // Elección de casilla
            try {
                Casilla casilla = matriz.getMatriz()[punto.x][punto.y]; // Toma la casilla elegida por el usuario
                for (Casilla c : matriz.getCasillasActivas()) {
                    if (matriz.IsCasillaEnRadio(casilla.getX(), casilla.getY(), c.getX(), c.getY(),
                            this.radioObtenido)) {
                        int dano = c.getVida(); // Recibe el mismo daño de su vida ('instakill')
                        c.recibirGolpe(dano);
                        c.getBitacora()
                            .add("La casilla (" + c.getX() + ", " + c.getY()
                                + ") fue golpeada por un tentáculo del 'Tentáculos' de " + hero.getNombre()
                                + ", recibiendo " + dano + " puntos de daño"); // Mensaje agregado a la
                                                                                              // bitácora de la casilla
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
            }
        }
    }

    // Getters
    public ArrayList<Point> getCasillasElegidas() {
        return casillasElegidas;
    }

    public int getRadioObtenido() {
        return radioObtenido;
    }

}
