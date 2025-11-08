/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Casilla;
import Cliente.Jugador;
import Hero.Hero;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public class ThreeNumbers extends Ataque {    
    // Atributos
    private Jugador contrincante;  // Contrincante al que se le lanza el ataque
    private ArrayList<Integer> numerosElegidos;  // Arreglo para recorrer cada número elegido
    // No puede usarse un array con tipos primitivos (como lo es el int): solo acepta objetos
    
    // TODO: COMPROBACIÓN AL RECIBIR COMANDO DE CONSEGUIR INTS DE 0 A 9 (PARA DARLE FALLA AL USER)
    
    // Constructor
    public ThreeNumbers(Hero hero, Jugador contrincante, ArrayList<Integer> numerosElegidos) {
        // TODO: CAMBIAR EL HERO DE CONTRINCANTE POR UNA CLASE "JUGADOR", QUE REAGRUPE SU MATRIZ Y SUS 3 HÉROES
        super(hero);
        this.contrincante = contrincante;
        this.numerosElegidos = numerosElegidos;  // Se asignan los números elegidos (podría incluso tenerse una construcción escalable)
    }
    
    /*
    // Métodos
    private ArrayList<Integer> pedirNumerosContrincante(Jugador contrincante) {  // Método ÚNICO para pedirle números a un contrincante
        ArrayList<Integer> numerosContrincante = new ArrayList<Integer>();

        // TODO: CAMBIAR EL HERO DE CONTRINCANTE POR UNA CLASE "JUGADOR", QUE REAGRUPE SU MATRIZ Y SUS 3 HÉROES


        return numerosContrincante;
    }

    
    */
    @Override
    public void ejecutar() { 
        /*
        boolean coincidencia = false;  // Booleano que almacena si hubo una coincidencia
        ArrayList<Integer> numerosContricante = pedirNumerosContrincante(this.contrincante);
        for (Integer num : this.numerosElegidos) {
            if (numerosContrincante.contains(num)) {  // Si el número del atacante también está en la lista del contrincante
                coincidencia = true;  // Hubo una coincidencia
                break;  // Salimos del for: ya tenemos lo que necesitabamos
            }
        }
        if (coincidencia) {  // Si hubo una coincidencia
            int cantidadGolpeada = 1;  // Variable que almacena la cantidad de casillas que serán golpeadas (inicia en 1 porque se necesita para multiplicar)
            for (Integer num : this.numerosElegidos) {
                cantidadGolpeada *= num;  // Se multiplica por el cada número de los elegidos
            }
            for (int i = 0; i < cantidadGolpeada; i++) {  // Para cada golpe que haremos
                // Elección de casilla
                Casilla casilla = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
                casilla.recibirGolpe(casilla.getVida());  // Derrota a la casilla
                casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por un ataque del 'Three Numbers' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
            }
        }
    */
    }
    
    // Getters
    public Jugador getContrincante() {
        return contrincante;
    }

    public ArrayList<Integer> getNumerosElegidos() {
        return numerosElegidos;
    }
    
}
