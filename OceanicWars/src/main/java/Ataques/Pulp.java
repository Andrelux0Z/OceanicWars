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

public class Pulp extends Ataque {
    // Atributos
    private int cantidadPulpos;  // Cantidad de pulpos

    // Constructor
    public Pulp(Hero hero, Jugador contrincante) {
        super(hero, contrincante);
        this.cantidadPulpos = (20 + rand.nextInt(31));  // Cantidad de pulpos generada
    }
    
    // Métodos
    @Override
    public void ejecutar() { 
        for (int i = 0; i < this.cantidadPulpos; i++) {
            for (int j = 0; j < 8; j++) {  // Va desde 0 a 7 para representar los 8 tentáculos
                // Elección de casilla
                int golpeTentaculo = 25 * (1 + hero.getFuerzaAtaque());  // Se hace daño basado en la fuerza del heroe
                Casilla casilla = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
                casilla.recibirGolpe(golpeTentaculo);  // Golpea a la casilla
                casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por un tentáculo del 'Pulp' de " + hero.getNombre() + ", recibiendo " + golpeTentaculo + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
            }
        }
    }
    
    // Getters
    public int getCantidadPulpos() {
        return cantidadPulpos;
    }
    
}
