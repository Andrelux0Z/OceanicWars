/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Ataques.ElementosAtaques.Volcan;
import Cliente.Casilla;
import Cliente.Jugador;
import Hero.Hero;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public class VolcanoRaising extends Ataque {
    // Atributos
    private Point casillaElegida;
    private int radioObtenido;

    // Constructor
    public VolcanoRaising(Hero hero, Jugador contrincante, Point casillaElegida) {
        super(hero, contrincante);
        this.casillaElegida = casillaElegida; // Se asigna la casilla
        this.radioObtenido = (int) ((1 + rand.nextInt(9)) * (1 + hero.getFuerzaAtaque() / 100.0)); // Se tiene un radio
                                                                                                   // del volcán en
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
            casilla.setObjetoPresente(new Volcan(this.radioObtenido)); // Se crea el objeto del volcán (además de darle
                                                                       // el radio)
            casilla.getBitacora()
                    .add("La casilla (" + casilla.getX() + ", " + casilla.getY()
                            + ") fue seleccionada como lugar de apararición del volcán del 'Volcano Raising' de "
                            + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
            casilla.recibirGolpe(casilla.getVida()); // La casilla donde sale el volcán es derrotada
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
                    c.getBitacora()
                            .add("La casilla (" + casilla.getX() + ", " + casilla.getY()
                                    + ") fue golpeada por la creación del volcán del 'Volcano Raising' de "
                                    + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                    c.recibirGolpe(c.getVida());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
        }
    }
}