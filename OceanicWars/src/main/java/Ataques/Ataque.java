/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;
import Cliente.Matriz;
import Hero.Hero;
import java.util.Random;

/**
 *
 * @author kokoju
 */
public abstract class Ataque {
    // Atributos
    Hero hero;  // El ataque tiene un atributo héroe
    Random rand ;
    Matriz matriz;
    
    // Constructor
    public Ataque(Hero hero, Matriz matriz) {  // Ejecución del ataque
        this.hero = hero;  // Héroe para ver sus características
        this.rand = new Random();  // Variable random (utilidad para los ataques)
        this.matriz = matriz;  // Matriz que se piensa atacar
        this.ejecutar();  // Se ejecuta el ataque: cada ataque hace algo distinto, entonces se deja abstract
    }
    
    // Métodos
    public abstract void ejecutar();
}
