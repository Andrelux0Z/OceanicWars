/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Casilla;
import Cliente.Matriz;
import Hero.Hero;

/**
 *
 * @author kokoju
 */

public class EelAttack extends Ataque {
    // Constructor
    public EelAttack(Hero hero, Matriz matriz) {
        super(hero);
    }
    
    // Métodos
    @Override
    public void ejecutar() {  
        // Cantidad de anguilas generadas
        int cantidadAnguilas = (25 + rand.nextInt(76));  // Se generan entre 25 y 100 anguilas
        for (int i = 0; i < cantidadAnguilas; i++) {
            // Cantidad de descargas de una anguila
            int cantidadDescargas = 1 + rand.nextInt(10);  // Entre 1 y 10 descargas
       
            // Elección de casilla
            Casilla casilla = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada por una anguila del 'Eel Attack' de " + hero.getNombre());
             
            for (int j = 0; j < cantidadDescargas; j++) {  // For para dar las descargas
                int golpeDescarga = 10  * (1 + hero.getFuerzaAtaque());  // Se hace daño basado en la fuerza del heroe
                casilla.recibirGolpe(golpeDescarga);  // Golpea a la casilla
                casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por una descarga de anguila del 'Eel Attack' de " + hero.getNombre() + ", recibiendo " + golpeDescarga + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
            }
        }
    }
}
