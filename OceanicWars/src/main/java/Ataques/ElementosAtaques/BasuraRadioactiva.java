/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques.ElementosAtaques;

/**
 *
 * @author kokoju
 */

public class BasuraRadioactiva extends ObjetoCasilla {
    // Atributos
    boolean isToxico;  // Booleano que indica si es tóxico o no
    
    // Constructor
    public BasuraRadioactiva(boolean isToxico) {
        this.isToxico = isToxico;
    }
    
    // Métodos
    public boolean getIsToxico() {
        return this.isToxico;
    }
}
