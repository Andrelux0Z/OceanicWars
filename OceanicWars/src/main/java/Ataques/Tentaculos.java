/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Casilla;
import Cliente.Matriz;
import Hero.Hero;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public class Tentaculos extends Ataque {
    // Atributos
    ArrayList<Point> casillasElegidas;  // Arreglo para recorrer cada casilla elegida
    
    // Constructor
    public Tentaculos(Hero hero, Matriz matriz, ArrayList<Point> casillasElegidas) {
        super(hero);
        this.casillasElegidas = casillasElegidas;  // Se asignan las casillas elegidas (podría incluso tenerse una construcción escalable
    }
    
    // Métodos
    @Override
    public void ejecutar() { 
        int radioObtenido = 1 + (1 * hero.getFuerzaAtaque());  // Se tiene un radio de tentáculos en función de la fuerza del héroe (fuera del for para mantenerse estable)
        for (Point punto : casillasElegidas) {
            // Elección de casilla
            try {
                Casilla casilla = matriz.getMatriz()[punto.x][punto.y];  // Toma la casilla elegida por el usuario
                for (Casilla c : matriz.getCasillasActivas()) {
                    if (matriz.IsCasillaEnRadio(casilla.getX(), casilla.getY(), c.getX(), c.getY(), radioObtenido)) {
                        c.recibirGolpe(c.getVida());  // Recibe el mismo daño de su vida ('instakill')
                        c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por un tentáculo del 'Tentáculos' de " + hero.getNombre() + ", recibiendo " + c.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla 
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
            }
        }
    }
}
