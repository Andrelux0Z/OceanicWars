/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Casilla;
import Ataques.ElementosAtaques.Direcciones;
import Hero.Hero;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public class ThreeLines extends Ataque {
    // Atributos
    private ArrayList<Point> casillasElegidas;  // Arreglo para recorrer cada casilla elegida
    private int alcanceObtenido;
    
    // Constructor
    public ThreeLines(Hero hero, ArrayList<Point> casillasElegidas) {
        super(hero);
        this.casillasElegidas = casillasElegidas;  // Se asignan las casillas elegidas (podría incluso tenerse una construcción escalable)
        this.alcanceObtenido = (1 + rand.nextInt(4)) * (1 + hero.getFuerzaAtaque());  // Se tiene un alcance de ataque en función de la fuerza del héroe SIN DECIMALES (fuera del for para mantenerse estable)
    }
    
    // Métodos
    @Override
    public void ejecutar() { 
        for (Point punto : casillasElegidas) {
            // Elección de casilla
            try {
                Casilla casilla = matriz.getMatriz()[punto.x][punto.y];  // Toma la casilla elegida por el usuario
                casilla.recibirGolpe(casilla.getVida());  // Golpea la casilla inicial del ataque
                casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada como lugar de apararición del ataque del 'Three Lines' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                Direcciones direccion = Direcciones.values()[rand.nextInt(Direcciones.values().length)];  // Se elige una dirección aleatoria (de los Direcciones.values(), se consigue un índice aleatorio)
                switch(direccion) {
                case ARRIBA:  // Al elegir la dirección ARRIBA
                    for (int i = 0; i < this.alcanceObtenido; i++) {
                        if ((casilla.getY() - i) < 0) {  // Verifica para no salirse de los límites: haciendo un break si llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[casilla.getX()][casilla.getY() - i];  // Se elige la casilla
                        c.recibirGolpe(c.getVida());  // La casilla recibe el mismo daño que su vida: 'instakill'
                        c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por el ataque del 'Three Lines' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                    }
                    break;
                case ABAJO:  // Al elegir la dirección ABAJO
                    for (int i = 0; i < this.alcanceObtenido; i++) {
                        if ((casilla.getY() + i) > this.matriz.getCantidadFilas() - 1) {  // Verifica para no salirse de los límites: haciendo un break si llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[casilla.getX()][casilla.getY() - i];
                        c.recibirGolpe(c.getVida());  // La casilla recibe el mismo daño que su vida: 'instakill'
                        c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por el ataque del 'Three Lines' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                    }
                    break;
                case IZQUIERDA:  // Al elegir la dirección IZQUIERDA
                    for (int i = 0; i < this.alcanceObtenido; i++) {
                        if ((casilla.getX() - i) < 0) {  // Verifica para no salirse de los límites: haciendo un break si llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[casilla.getX() - i][casilla.getY()];
                        c.recibirGolpe(c.getVida());  // La casilla recibe el mismo daño que su vida: 'instakill'
                        c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por el ataque del 'Three Lines' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                    }
                    break;
                case DERECHA:  // Al elegir la dirección DERECHA
                    for (int i = 0; i < this.alcanceObtenido; i++) {
                        if ((casilla.getX() + i) > this.matriz.getCantidadColumnas() - 1) {  // Verifica para no salirse de los límites: haciendo un break si llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[casilla.getX()][casilla.getY() - i];
                        c.recibirGolpe(c.getVida());  // La casilla recibe el mismo daño que su vida: 'instakill'
                        c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por el ataque del 'Three Lines' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                    }
                    break;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
            }
        }
    }
    
    // Getters
    public ArrayList<Point> getCasillasElegidas() {
        return casillasElegidas;
    }

    public int getAlcanceObtenido() {
        return alcanceObtenido;
    }
    
}