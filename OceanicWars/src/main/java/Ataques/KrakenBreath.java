/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Ataques.ElementosAtaques.Direcciones;
import Cliente.Casilla;
import Cliente.Jugador;
import Hero.Hero;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public class KrakenBreath extends Ataque {
    // Atributos
    private Point casillaElegida;
    private Direcciones direccion;
    private int alcanceObtenido;

    // Constructor
    public KrakenBreath(Hero hero, Jugador contrincante, Point casillaElegida, Direcciones direccion) {
        super(hero, contrincante);
        this.casillaElegida = casillaElegida; // Se asigna la casilla
        this.direccion = direccion;
        this.alcanceObtenido = (int) ((1 + rand.nextInt(8)) * (1 + hero.getFuerzaAtaque() / 100.0)); // Se tiene un
                                                                                                     // alcance de
                                                                                                     // tentáculos en
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
            casilla.getBitacora()
                    .add("La casilla (" + casilla.getX() + ", " + casilla.getY()
                            + ") fue seleccionada como lugar de apararición del aliento del 'Kraken Breath' de "
                            + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
            casilla.recibirGolpe(casilla.getVida()); // Golpea la casilla inicial del ataque
            switch (direccion) {
                case ARRIBA: // Al elegir la dirección ARRIBA
                    for (int i = 0; i < this.alcanceObtenido; i++) {
                        if ((casilla.getY() - i) < 0) { // Verifica para no salirse de los límites: haciendo un break si
                                                        // llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[casilla.getX()][casilla.getY() - i]; // Se elige la casilla
                        c.getBitacora()
                                .add("La casilla (" + c.getX() + ", " + c.getY()
                                        + ") fue golpeada por el aliento del 'Kraken Breath' de " + hero.getNombre()
                                        + ", recibiendo " + c.getVida() + " puntos de daño");
                        c.recibirGolpe(c.getVida()); // La casilla recibe el mismo daño que su vida: 'instakill'    
                    }
                    break;
                case ABAJO: // Al elegir la dirección ABAJO
                    for (int i = 0; i < this.alcanceObtenido; i++) {
                        if ((casilla.getY() + i) > this.matriz.getCantidadFilas() - 1) { // Verifica para no salirse de
                                                                                         // los límites: haciendo un
                                                                                         // break si llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[casilla.getX()][casilla.getY() - i];
                        c.getBitacora()
                                .add("La casilla (" + c.getX() + ", " + c.getY()
                                        + ") fue golpeada por el aliento del 'Kraken Breath' de " + hero.getNombre()
                                        + ", recibiendo " + c.getVida() + " puntos de daño");
                        c.recibirGolpe(c.getVida()); // La casilla recibe el mismo daño que su vida: 'instakill'
                    }
                    break;
                case IZQUIERDA: // Al elegir la dirección IZQUIERDA
                    for (int i = 0; i < this.alcanceObtenido; i++) {
                        if ((casilla.getX() - i) < 0) { // Verifica para no salirse de los límites: haciendo un break si
                                                        // llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[casilla.getX() - i][casilla.getY()];
                        c.getBitacora()
                                .add("La casilla (" + c.getX() + ", " + c.getY()
                                        + ") fue golpeada por el aliento del 'Kraken Breath' de " + hero.getNombre()
                                        + ", recibiendo " + casilla.getVida() + " puntos de daño");
                        c.recibirGolpe(c.getVida()); // La casilla recibe el mismo daño que su vida: 'instakill'
                    }
                    break;
                case DERECHA: // Al elegir la dirección DERECHA
                    for (int i = 0; i < this.alcanceObtenido; i++) {
                        if ((casilla.getX() + i) > this.matriz.getCantidadColumnas() - 1) { // Verifica para no salirse
                                                                                            // de los límites: haciendo
                                                                                            // un break si llega a un
                                                                                            // borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[casilla.getX()][casilla.getY() - i];
                        c.getBitacora()
                                .add("La casilla (" + c.getX() + ", " + c.getY()
                                        + ") fue golpeada por el aliento del 'Kraken Breath' de " + hero.getNombre()
                                        + ", recibiendo " + c.getVida() + " puntos de daño");
                        c.recibirGolpe(c.getVida()); // La casilla recibe el mismo daño que su vida: 'instakill'
                    }
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
        }
    }

    // Getters
    public Point getCasillaElegida() {
        return casillaElegida;
    }

    public Direcciones getDireccion() {
        return direccion;
    }

    public int getAlcanceObtenido() {
        return alcanceObtenido;
    }

}
