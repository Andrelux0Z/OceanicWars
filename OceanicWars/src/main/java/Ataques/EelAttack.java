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

public class EelAttack extends Ataque {
    // Atributos
    private int cantidadAnguilas;  // Cantidad de anguilas generadas
    private int cantidadDescargas;  // Cantidad de descargas de una anguila
    private int golpeDescarga;  // Golpe para cada descarga

    // Constructor
    public EelAttack(Hero hero, Jugador contrincante) {
        super(hero, contrincante);
        this.cantidadAnguilas = (25 + rand.nextInt(76));  // Se generan entre 25 y 100 anguilas
        this.cantidadDescargas = 1 + rand.nextInt(10);  // Entre 1 y 10 descargas
        this.golpeDescarga = 10  * (1 + hero.getFuerzaAtaque());  // Se hace daño basado en la fuerza del heroe
    }
    
    // Métodos
    @Override
    public void ejecutar() {  
        for (int i = 0; i < this.cantidadAnguilas; i++) {
            // Cantidad de descargas de una anguila
       
            // Elección de casilla
            Casilla casilla = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada por una anguila del 'Eel Attack' de " + hero.getNombre());
             
            for (int j = 0; j < this.cantidadDescargas; j++) {  // For para dar las descargas
                casilla.recibirGolpe(this.golpeDescarga);  // Golpea a la casilla
                casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por una descarga de anguila del 'Eel Attack' de " + hero.getNombre() + ", recibiendo " + this.golpeDescarga + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
            }
        }
    }
    
    // Getters
    public int getCantidadAnguilas() {
        return cantidadAnguilas;
    }

    public int getCantidadDescargas() {
        return cantidadDescargas;
    }

    public int getGolpeDescarga() {
        return golpeDescarga;
    }
    
}
