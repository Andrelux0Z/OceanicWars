/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Cliente.Casilla;
import Cliente.Jugador;
import Cliente.Matriz;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public abstract class Hero {
    // Atributos
    private String nombre; // Nombre mostrado
    private String imagen; // Dirección de la imágen
    private Color color; // Color del que pintan las casillas en donde se encuentran
    private ArrayList<Casilla> casillasEnPosesion;  // ArrayList para el guardado de las casillas que le pertenecen
    private boolean estado;  // Booleano que indica si el héroe está vivo

    // DATOS EN PORCENTAJES (DE 0 A 100)
    private int ocupacion; // Porcentaje de las casillas que les pertenece (0 a 100)
    private int sanidad; // Curación a las casillas no muertas
    private int fuerza; // Fuerza del siguiente ataque del jugador
    private int resistencia; // Protección brindada a casillas
    private boolean siguientePotenciado; // Booleano que indica si el usuario pasó turno y su siguiente ataque se
                                 // potencia
    private Matriz matrizAtaque; // Matriz que va a atacar el héroe en su próximo movimiento

    // Constructor
    public Hero(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
        this.casillasEnPosesion = new ArrayList<Casilla>();
        this.estado = true;
        this.ocupacion = ocupacion;
        this.sanidad = sanidad;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.siguientePotenciado = false;
    }

    // Métodos
    public void verificarMuerte() {  // Función para correr cada que una casilla es golpeada
        boolean algunaViva = false;  // Booleano que lleva la cuenta de si existe alguna casilla viva
        for (Casilla c : casillasEnPosesion) {
            if (c.getEstado() == true) {  // Si alguna casilla está viva
                algunaViva = true;  // Se establece true en alguna viva
                break;  // Se sale del for
            }
        }
        if (!algunaViva) {  // Si todas las casillas están muertas
            this.estado = false;  // El héroe muere
        }
    }
    
    
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
    public void setMatrizAtaque(Matriz matrizAtaque) { // Set para configurar la matriz atacada
        this.matrizAtaque = matrizAtaque;
    }

    // Método abstracto para obtener el arquetipo
    public abstract String getArquetipo();

    public ArrayList<Casilla> getCasillasEnPosesion() {
        return casillasEnPosesion;
    }
}
