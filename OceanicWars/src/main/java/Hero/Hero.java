/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Cliente.Jugador;
import Cliente.Matriz;
import java.awt.Color;

/**
 *
 * @author kokoju
 */

public abstract class Hero {
    // Atributos
    String nombre; // Nombre mostrado
    String imagen; // Dirección de la imágen
    Color color; // Color del que pintan las casillas en donde se encuentran

    // DATOS EN PORCENTAJES (DE 0 A 100)
    int ocupacion; // Porcentaje de las casillas que les pertenece (0 a 100)
    int sanidad; // Curación a las casillas no muertas
    int fuerza; // Fuerza del siguiente ataque del jugador
    int resistencia; // Protección brindada a casillas
    boolean siguientePotenciado; // Booleano que indica si el usuario pasó turno y su siguiente ataque se
                                 // potencia
    Matriz matrizAtaque; // Matriz que va a atacar el héroe en su próximo movimiento

    // Constructor
    public Hero(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
        this.ocupacion = ocupacion;
        this.sanidad = sanidad;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.siguientePotenciado = false;
    }

    // Métodos
    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public Color getColor() {
        return color;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public int getSanidad() {
        return sanidad;
    }

    public int getFuerza() { // Devuelve el stat de fuerza que tiene el héroe, no si ataca con más fuerza o
                             // no
        return fuerza;
    }

    public int getFuerzaAtaque() { // Función que SI devuelve la fuerza que se usa en base al valor de
                                   // 'siguiente_potenciado'
        if (this.siguientePotenciado) {
            return fuerza;
        } else {
            return 0;
        }
    }

    public int getResistencia() {
        return resistencia;
    }

    public Matriz getMatrizAtaque() {
        return matrizAtaque;
    }

    // Setters
    public void setMatrizAAtacar(Matriz matrizAtaque) { // Set para configurar la matriz atacada
        this.matrizAtaque = matrizAtaque;
    }

    // Método abstracto para obtener el arquetipo
    public abstract String getArquetipo();
}
