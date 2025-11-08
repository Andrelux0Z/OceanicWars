/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques.ElementosAtaques;

/**
 *
 * @author kokoju
 */
public class Kraken extends ObjetoCasilla {  // Al igual que el volcán y el remolino, el Kraken se queda en una casilla y tiene radio
    // Atributos
    int radio;
    
    // Constructor
    public Kraken(int radio) {
        this.radio = radio;
    }
    
    // Métodos
    public int getRadio() {
        return radio;
    }
}
