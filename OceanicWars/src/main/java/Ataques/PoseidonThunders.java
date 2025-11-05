/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Casilla;
import Cliente.Matriz;
import Hero.Hero;
import java.util.Random;

/**
 *
 * @author kokoju
 */
public class PoseidonThunders {
    // Atributos
    Random rand ;
    Matriz matriz;
    
    public PoseidonThunders(Matriz matriz) {
        this.rand = new Random();
        this.matriz = matriz;
    }
    
        
    public void poseidon_thunders(Hero hero) {
        // Cantidad de rayos generada
        // Al ser un ataque "Instakill", el elemento que aumenta la fuerza será la cantidad de rayos, no su alcance
        int cantidadRayos = (5 + rand.nextInt(6)) * (1 + hero.getFuerzaAtaque()); // Crea de 5 a 10 rayos (porque nextInt va hasta n-1) * fuerza del héroe
        for (int i = 0; i < cantidadRayos; i++) {
            // Elección de radio 
            int radioObtenido = (2 + rand.nextInt(9));  // Radio de 2 hasta 10
            
            // Elección de casilla
            Casilla casilla = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
            casilla.recibirGolpe(casilla.getVida());  // Recibe el mismo daño de su vida ('instakill')
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada como epicentro de una onda de 'Poseidon Thunders', recibiendo " + casilla.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
            
            for (Casilla c : matriz.getCasillasActivas()) {  // Revisamos en las casillas activas
                if (matriz.IsCasillaEnRadio(casilla.getX(), casilla.getY(), c.getX(), c.getY(), radioObtenido)) {
                    c.recibirGolpe(c.getVida());  // Recibe el mismo daño de su vida ('instakill')
                    c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por una onda expansiva de 'Poseidon Thunders', recibiendo " + c.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
                }
            }
        }
    }
}
