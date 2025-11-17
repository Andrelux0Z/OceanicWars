/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Ataques.ElementosAtaques.Kraken;
import Cliente.Casilla;
import Cliente.Jugador;
import Hero.Hero;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public class ControlTheKraken extends Ataque {
    // Atributos
    private Point casillaElegida;  // Espacio para la casilla en dónde se inicio el ataque

    // Constructor
    public ControlTheKraken(Hero hero, Jugador contrincante, Point casillaElegida) {
        super(hero, contrincante);
        this.casillaElegida = casillaElegida;
    }
    
    // Métodos
    @Override
    public void ejecutar() {
        Casilla casilla = matriz.getMatriz()[casillaElegida.x][casillaElegida.y];
        Kraken kraken = (Kraken) casilla.getObjetoPresente();
        Ataque ataque = new ReleaseTheKraken(hero, contrincante, casillaElegida, kraken.getRadio());  // Se crea una copia exacta del ataque
        ataque.ejecutar();  // Se ejecuta
    }
    
    // Getters
    public Point getCasillaElegida() {
        return casillaElegida;
    }    
}
