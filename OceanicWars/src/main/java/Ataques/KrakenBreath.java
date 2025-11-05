/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Casilla;
import Hero.Hero;
import java.awt.Point;

/**
 *
 * @author kokoju
 */
public class KrakenBreath extends Ataque {
    // Atributos
    Point casillaElegida;
    Direcciones direccion;
    
    // Constructor
    public KrakenBreath(Hero hero, Point casillaElegida, Direcciones direccion) {
        super(hero);
        this.casillaElegida = casillaElegida;  // Se asigna la casilla
        this.direccion = direccion;
    }
    
    // Métodos
    @Override
    public void ejecutar() { 
        int alcanceObtenido = (1 + rand.nextInt(8)) * (1 + hero.getFuerzaAtaque());  // Se tiene un radio de tentáculos en función de la fuerza del héroe (fuera del for para mantenerse estable)
        // Elección de casilla
        try {
            Casilla casilla = matriz.getMatriz()[this.casillaElegida.x][this.casillaElegida.y];  // Toma la casilla elegida por el usuario
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada como lugar de apararición del aliento del 'Kraken Breath' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
            switch(direccion) {
                case ARRIBA:  // Al elegir la dirección ARRIBA
                    for (int i = 0; i < alcanceObtenido; i++) {
                        if ((this.casillaElegida.y - i) < 0) {  // Verifica para no salirse de los límites: haciendo un break si llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[this.casillaElegida.x][this.casillaElegida.y - i];  // Se elige la casilla
                        c.recibirGolpe(c.getVida());  // La casilla recibe el mismo daño que su vida: 'instakill'
                        c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por el aliento del 'Kraken Breath' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                    }
                    break;
                case ABAJO:  // Al elegir la dirección ABAJO
                    for (int i = 0; i < alcanceObtenido; i++) {
                        if ((this.casillaElegida.y + i) > this.matriz.getCantidadFilas() - 1) {  // Verifica para no salirse de los límites: haciendo un break si llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[this.casillaElegida.x][this.casillaElegida.y - i];
                        c.recibirGolpe(c.getVida());  // La casilla recibe el mismo daño que su vida: 'instakill'
                        c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por el aliento del 'Kraken Breath' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                    }
                    break;
                case IZQUIERDA:  // Al elegir la dirección IZQUIERDA
                    for (int i = 0; i < alcanceObtenido; i++) {
                        if ((this.casillaElegida.x - i) < 0) {  // Verifica para no salirse de los límites: haciendo un break si llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[this.casillaElegida.x - i][this.casillaElegida.y];
                        c.recibirGolpe(c.getVida());  // La casilla recibe el mismo daño que su vida: 'instakill'
                        c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por el aliento del 'Kraken Breath' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                    }
                    break;
                case DERECHA:  // Al elegir la dirección DERECHA
                    for (int i = 0; i < alcanceObtenido; i++) {
                        if ((this.casillaElegida.x + i) > this.matriz.getCantidadColumnas() - 1) {  // Verifica para no salirse de los límites: haciendo un break si llega a un borde
                            break;
                        }
                        Casilla c = matriz.getMatriz()[this.casillaElegida.x][this.casillaElegida.y - i];
                        c.recibirGolpe(c.getVida());  // La casilla recibe el mismo daño que su vida: 'instakill'
                        c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por el aliento del 'Kraken Breath' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                    }
                    break;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
        }
    }
}

