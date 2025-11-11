/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Models;

/**
 *
 * @author diego
 */
public enum CommandType {
    ATTACK (4),  //attack Andres 4 5
    APPLYATTACK (4), //
    ATTACK_RESULT (3), // attack result notifications: ATTACK_RESULT <recipient> <message>
    MESSAGE (2), //message hola a todos
    PRIVATE_MESSAGE(3), //private Andres hola andres
    GIVEUP (1), //giveup
    NAME (2),
    BOOST (3); //BOOST <HERO> <HEAL/PROTECT/STRENGHTEN>
    //.. AGREGARÍAN MÁS TIPOS DE COMANDO
    
    
    private int requiredParameters;

    private CommandType(int requiredParameters) {
        this.requiredParameters = requiredParameters;
    }

    public int getRequiredParameters() {
        return requiredParameters;
    }
    
    
    
    
    
}
