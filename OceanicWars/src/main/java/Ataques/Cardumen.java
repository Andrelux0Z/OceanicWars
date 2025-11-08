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

public class Cardumen extends Ataque {
    // Atributos
    private int golpePez;  // Guarda el daño que harán los peces

// Constructor
    public Cardumen(Hero hero, Jugador contrincante) {
        super(hero, contrincante);
        this.golpePez = 30 * (1 + hero.getFuerzaAtaque());  // Se hace daño basado en la fuerza del heroe
    }
    
    // Métodos
    @Override
    public void ejecutar() { 
        int cantidadPeces = (100 + rand.nextInt(201));
        for (int i = 0; i < cantidadPeces; i++) {  // Para cada pez  
            // Elección de casilla
            Casilla casilla = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
            casilla.recibirGolpe(this.golpePez);  // Golpea a la casilla
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por un pez del 'Cardumen' de " + hero.getNombre() + ", recibiendo " + this.golpePez + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
        }
    }
    
    // Getters
    public int getGolpePez() {
        return golpePez;
    }
}