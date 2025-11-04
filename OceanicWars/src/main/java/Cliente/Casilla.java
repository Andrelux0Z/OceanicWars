/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

// Creación de una casilla: elemento básico del juego en el que ocurren los ataques
public class Casilla {
    // Atributos
    private int x;  // Coord x de la casilla dentro de la matriz
    private int y;  // Coord y de la casilla
    private int vida;  // Vida de la casilla
    private int defensa;  // Cantidad de defensa de la casilla (usualmente en 0, aumenta a 0.5, 0.75 o 1 por una habilidad, y multiplica el ataque recibido antes de aplicarse)
    private String nombreLuchador;  // Nombre del luchador al cual le pertenece la casilla
    private Color color;  // Color del cual se pinta la casilla
    private ArrayList<String> bitacora;  // Bitácora que almacena los movimientos hechos en la casilla (daño, defensas, curas, posesiones)
    private Object elementoPresente; // TEMPORAL: Almacenamiento de objeto creado a la hora de atacar (puede estar null)
    private boolean estado;  // Estado que indica si la casilla está viva o no

    // Constructor
    public Casilla(int x, int y, String nombreLuchador, Color color) {  // Elementos que son pedidos por la casilla, los que no son pedidos son establecidos automáticamente    
        this.x = x;
        this.y = y;
        this.vida = 100;  // Vida es iniciada en 100 por defecto
        this.defensa = 0;  // Al inicio nadie protege las casillas, entonces estas tienen sus defensas en 0s
        this.nombreLuchador = nombreLuchador;
        this.color = color;
        this.bitacora = new ArrayList<String>();  // Se crea un ArrayList para la bitácora
        this.elementoPresente = null;
        this.estado = true;  // Una casilla inicia por defecto viva
    }
    
    // Métodos
    // TODO
    
    
    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVida() {
        return vida;
    }

    public int getDefensa() {
        return defensa;
    }

    public String getNombreLuchador() {
        return nombreLuchador;
    }

    public Color getColor() {
        return color;
    }

    public ArrayList<String> getBitacora() {
        return bitacora;
    }

    public Object getElementoPresente() {
        return elementoPresente;
    }

    public boolean isEstado() {
        return estado;
    }
    
    // Setters
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setElementoPresente(Object elementoPresente) {
        this.elementoPresente = elementoPresente;
    }
}