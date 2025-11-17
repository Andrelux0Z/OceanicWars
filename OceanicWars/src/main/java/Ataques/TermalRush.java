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
public class TermalRush extends Ataque {
    // Atributos
    private Point casillaElegida;
    private int radioObtenido;
    private int tiempoActiva;

    // Constructor
    public TermalRush(Hero hero, Jugador contrincante, Point casillaElegida) {
        super(hero, contrincante);
        this.casillaElegida = casillaElegida; // Se asigna la casilla
        this.radioObtenido = (int) ((5) * (1 + hero.getFuerzaAtaque() / 100.0)); // Se consigue el radio de alcance
                                                                                 // adicional
        this.tiempoActiva = 5 + rand.nextInt(2); // Tiempo que el calentamiento estará activo
    }

    // TODO: REVISAR EN EL CHAT SI LA CASILLA ELEGIDA TIENE UN VOLCAN

    // Métodos
    @Override
    public void ejecutar() {
        // Elección de casilla
        try {
            Casilla casilla = matriz.getMatriz()[this.casillaElegida.x][this.casillaElegida.y]; // Toma la casilla
                                                                                                // elegida por el
                                                                                                // usuario
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY()
                    + ") fue seleccionada como el épicentro del 'Termal Rush' de " + hero.getNombre());
            this.radioObtenido += ((Volcan) casilla.getObjetoPresente()).getRadio(); // Se obtiene el radio del volcán,
                                                                                     // el cual es sumado al radio
                                                                                     // adicional que habiamos sacado en
                                                                                     // un inicio
            int golpeCalentamiento = ((Volcan) casilla.getObjetoPresente()).getRadio(); // El daño progresivo es hecho
                                                                                        // también en función del tamaño
                                                                                        // del volcán

            for (Casilla c : matriz.getCasillasActivas()) {
                if (matriz.IsCasillaEnRadio(casilla.getX(), casilla.getY(), c.getX(), c.getY(), this.radioObtenido)) { // Si
                                                                                                                       // la
                                                                                                                       // casilla
                                                                                                                       // está
                                                                                                                       // dentro
                                                                                                                       // del
                                                                                                                       // rango
                    c.recibirGolpe(golpeCalentamiento * this.tiempoActiva);
                    c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY()
                            + ") fue golpeada por la creación del volcán del 'Volcano Raising' de " + hero.getNombre()
                            + ", recibiendo " + golpeCalentamiento * this.tiempoActiva + " puntos de daño");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
        }
    }
}
