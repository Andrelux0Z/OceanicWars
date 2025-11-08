/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques.ElementosAtaques;

/**
 *
 * @author kokoju
 */
public class Volcan extends ObjetoCasilla{  // Viendo su estructura, el volcán es igual a un remolino, pero lo hacemos de esta manera para evitar problemas al hacer cambios entre objetos
    // Atributos
    int radio;
    
    // Constructor
    public Volcan(int radio) {
        this.radio = radio;
    }
    
    // Métodos
    public int getRadio() {
        return radio;
    }
}

