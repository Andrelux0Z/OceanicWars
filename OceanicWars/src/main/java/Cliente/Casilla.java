/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;
import Ataques.ElementosAtaques.ObjetoCasilla;
import Hero.Hero;
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
    private Hero hero;  // Espacio para guardar el héroe al que le pertenece la casilla
    private Matriz matriz;  // Espacio para guardar la matriz a la que pertenece la casilla
    private ArrayList<String> bitacora;  // Bitácora que almacena los movimientos hechos en la casilla (daño, defensas, curas, posesiones)
    private ObjetoCasilla objetoPresente; // Almacenamiento de objeto creado a la hora de atacar (puede estar null)
    private boolean estado;  // Estado que indica si la casilla está viva o no

    // Constructor
    public Casilla(int x, int y, Matriz matriz) {  // Elementos que son pedidos por la casilla, los que no son pedidos son establecidos automáticamente    
        // TODO: CAMBIAR PARA QUE LAS CASILLAS RECIBAN UN LUCHADOR
        
        this.x = x;
        this.y = y;
        this.vida = 100;  // Vida es iniciada en 100 por defecto
        this.defensa = 0;  // Al inicio nadie protege las casillas, entonces estas tienen sus defensas en 0s
        this.hero = hero;
        this.matriz = matriz;
        this.bitacora = new ArrayList<String>();  // Se crea un ArrayList para la bitácora
        this.objetoPresente = null;
        this.estado = true;  // Una casilla inicia por defecto viva
    }
    
    // Métodos
    public void recibirGolpe(int golpe) {
    if (this.estado) {  // Si la casilla está viva, esta recibe daño
            this.vida -= (golpe) * (1 - this.defensa);
            if (this.vida <= 0) {
                this.vida = 0;  // No deja la vida en negativos
                this.estado = false;  // Casilla deja de estar activa
                this.bitacora.add("La casilla (" + x + ", " + y + ") fue derrotada");  // Mensaje a la bitácora
                this.matriz.getCasillasActivas().remove(this);  // La casilla se elimina del las casillas activas
            }
        }
    }
    
    
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

    public String getNombreHero() {
        return hero.getNombre();
    }

    public Color getColor() {
        return hero.getColor();
    }

    public ArrayList<String> getBitacora() {
        return bitacora;
    }

    public Object getObjetoPresente() {
        return objetoPresente;
    }

    public boolean getEstado() {
        return estado;
    }
    
    // Setters 
    public void setObjetoPresente(ObjetoCasilla objetoPresente) {
        this.objetoPresente = objetoPresente;
    }
    
    public void setHero(Hero hero) {
        this.hero = hero;
    }
}