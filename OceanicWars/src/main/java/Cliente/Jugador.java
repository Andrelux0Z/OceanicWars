/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import Hero.Hero;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public class Jugador {  // Clase del Jugador: guarda su matriz, sus 3 héroes elegidos y una bitácora con información importante
    // Atributos
    private String nombre;  // Nombre del jugador
    private Matriz matriz;  // Matriz que le pertenece al jugador
    private ArrayList<Hero> heroes;  // ArrayList que almacena los héroes del Jugador (podría escalarse si se quisiera)
    private ArrayList<String> bitacora;  // ArrayList que muestra el historial del Jugador (ataques que ha hecho y que ha recibido)
    
    // Constructor

    public Jugador(String nombre, Matriz matriz, ArrayList<Hero> heroes, ArrayList<String> bitacora) {
        this.nombre = nombre;
        this.matriz = matriz;
        this.heroes = heroes;
        this.bitacora = bitacora;
    }
    
    // Métodos
    // Getters
    public String getNombre() {
        return nombre;
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public ArrayList<String> getBitacora() {
        return bitacora;
    }
    
}
