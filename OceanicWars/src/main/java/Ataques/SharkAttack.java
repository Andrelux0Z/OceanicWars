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

public class SharkAttack extends Ataque {
    // Atributos
    private int radioObtenido;  // Radio del ataque de cada tiburón
    
    // Constructor
    public SharkAttack(Hero hero, Jugador contrincante) {
        super(hero, contrincante);
        this.radioObtenido = 1 + rand.nextInt(10);  // Radio entre 1 y 10
    }
    
    // Métodos
    @Override
    public void ejecutar() { 
        // Creación de los puntos para todas las esquinas
        enum Esquina {
            SUP_IZQ,
            SUP_DER,
            INF_IZQ,
            INF_DER;
        }
        
        // No es la mejor ejecución, pero al ser esquinas y no ser escalables, me parece más lógico
        // Para cada esquina, hacemos algo 
        for (Esquina val : Esquina.values()) {
            switch(val) {  // Se hace un switch en función del val revisado
                case SUP_IZQ:
                    for (Casilla c : matriz.getCasillasActivas()) {  // Revisamos en las casillas activas
                        if (matriz.IsCasillaEnRadio(0, 0, c.getX(), c.getY(), this.radioObtenido)) {
                            c.recibirGolpe(c.getVida());  // Recibe el mismo daño de su vida ('instakill')
                            c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por un tiburón del 'Shark Attack' de " + hero.getNombre() + ", recibiendo " + c.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
                        }
                    }
                    break;
                case SUP_DER:
                    for (Casilla c : matriz.getCasillasActivas()) {  // Revisamos en las casillas activas
                        if (matriz.IsCasillaEnRadio(matriz.getCantidadColumnas() - 1, 0, c.getX(), c.getY(), this.radioObtenido)) {
                            c.recibirGolpe(c.getVida());  // Recibe el mismo daño de su vida ('instakill')
                            c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por un tiburón del 'Shark Attack' de " + hero.getNombre() + ", recibiendo " + c.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
                        }
                    }
                    break;
                case INF_IZQ:
                    for (Casilla c : matriz.getCasillasActivas()) {  // Revisamos en las casillas activas
                        if (matriz.IsCasillaEnRadio(0, matriz.getCantidadFilas() - 1, c.getX(), c.getY(), this.radioObtenido)) {
                            c.recibirGolpe(c.getVida());  // Recibe el mismo daño de su vida ('instakill')
                            c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por un tiburón del 'Shark Attack' de " + hero.getNombre() + ", recibiendo " + c.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
                        }
                    }
                    break;
                case INF_DER:
                    for (Casilla c : matriz.getCasillasActivas()) {  // Revisamos en las casillas activas
                        if (matriz.IsCasillaEnRadio(matriz.getCantidadColumnas() - 1, matriz.getCantidadFilas() - 1, c.getX(), c.getY(), this.radioObtenido)) {
                            c.recibirGolpe(c.getVida());  // Recibe el mismo daño de su vida ('instakill')
                            c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por un tiburón del 'Shark Attack' de " + hero.getNombre() + ", recibiendo " + c.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
                        }
                    }
                    break;
            }
        }
    }
    
    // Getters
    public int getRadioObtenido() {
        return radioObtenido;
    }
    
}