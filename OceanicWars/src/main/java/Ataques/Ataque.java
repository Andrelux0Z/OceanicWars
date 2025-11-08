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
    public Hero hero;  // El ataque tiene un atributo héroe
    public Random rand ;
    public Matriz matriz;
    
    // Constructor
    public Ataque(Hero hero) {  // Ejecución del ataque
        this.hero = hero;  // Héroe para ver sus características
        this.rand = new Random();  // Variable random (utilidad para los ataques)
        this.matriz = hero.getMatrizAtaque();
    }
    
    // Métodos
    public abstract void ejecutar();
    
    // Getters
    public Hero getHero() {
        return hero;
    }

    public Random getRand() {
        return rand;
    }

    public Matriz getMatriz() {
        return matriz;
    }
    
}
