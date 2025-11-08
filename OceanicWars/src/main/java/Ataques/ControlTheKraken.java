/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Ataques.ElementosAtaques.Direcciones;
import Cliente.Casilla;
import Hero.Hero;
import java.awt.Point;

/**
 *
 * @author kokoju
 */
public class ControlTheKraken extends Ataque {
    // Atributos
    private Point casillaElegida;  // Espacio para la casilla en dónde se inicio el ataque
    private int radioObtenido;  // Espacio para recibir el radio que se obtuvo
    
    // TODO: AL RECIBIR ATAQUES, BUSCAR UNA FORMA DE RECUPERAR LOS ATRIBUTOS

    // Constructor
    public ControlTheKraken(Hero hero, Point casillaElegida, int radioObtenido) {
        super(hero);
        this.casillaElegida = casillaElegida;
        this.radioObtenido = radioObtenido;
    }
    
    // Métodos
    @Override
    public void ejecutar() {
        ReleaseTheKraken ataque = new ReleaseTheKraken(hero, casillaElegida, radioObtenido);  // Se crea una copia exacta del ataque
        ataque.ejecutar();  // Se ejecuta
    }
    
    // Getters
    public Point getCasillaElegida() {
        return casillaElegida;
    }

    public int getRadioObtenido() {
        return radioObtenido;
    }
    
}
