/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import java.awt.Color;

/**
 *
 * @author kokoju
 */
public class Hero {
    // Atributos
    String nombre;  // Nombre mostrado
    String imagen;  // Dirección de la imágen
    Color color;  // Color del que pintan las casillas en donde se encuentran
    
    // DATOS EN PORCENTAJES (DE 0 A 1, PASANDO POR DÉCIMALES)
    int ocupacion;  // Porcentaje de las casillas que les pertenece
    int sanidad;  // Curación a las casillas no muertas
    int fuerza;  // Fuerza del siguiente ataque del jugador
    int resistencia;  // Protección brindada a casillas
    boolean siguiente_potenciado;  // Booleano que indica si el usuario pasó turno y su siguiente ataque se potencia

    // Constructor
    public Hero (String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
        this.ocupacion = ocupacion;
        this.sanidad = sanidad;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.siguiente_potenciado = false;
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

    public int getFuerza() {  // Devuelve el stat de fuerza que tiene el héroe, no si ataca con más fuerza o no
        return fuerza;
    }
    
    public int getFuerzaAtaque() {  // Función que SI devuelve la fuerza que se usa en base al valor de 'siguiente_potenciado'
        if (this.siguiente_potenciado) {
            return fuerza;
        }
        else {
            return 0;
        }
    }

    public int getResistencia() {
        return resistencia;
    }
    
    
}
