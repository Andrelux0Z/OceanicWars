/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import Ataques.ElementosAtaques.ObjetoCasilla;
import Hero.Hero;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

// Creación de una casilla: elemento básico del juego en el que ocurren los
// ataques
public class Casilla {
    // Atributos
    private int x; // Coord x de la casilla dentro de la matriz
    private int y; // Coord y de la casilla
    private int vida; // Vida de la casilla
    private int defensa; // Cantidad de defensa de la casilla (usualmente en 0, aumenta a 0.5, 0.75 o 1
                         // por una habilidad, y multiplica el ataque recibido antes de aplicarse)
    private Hero hero; // Espacio para guardar el héroe al que le pertenece la casilla
    private Matriz matriz; // Espacio para guardar la matriz a la que pertenece la casilla
    private ArrayList<String> bitacora; // Bitácora que almacena los movimientos hechos en la casilla (daño, defensas,
                                        // curas, posesiones)
    private ObjetoCasilla objetoPresente; // Almacenamiento de objeto creado a la hora de atacar (puede estar null)
    private boolean estado; // Estado que indica si la casilla está viva o no

    // Constructor
    public Casilla(int x, int y, Matriz matriz) { // Elementos que son pedidos por la casilla, los que no son pedidos
                                                  // son establecidos automáticamente
        this.x = x;
        this.y = y;
        this.vida = 100; // Vida es iniciada en 100 por defecto
        this.defensa = 0; // Al inicio nadie protege las casillas, entonces estas tienen sus defensas en
                          // 0s
        this.hero = hero;
        this.matriz = matriz;
        this.bitacora = new ArrayList<String>(); // Se crea un ArrayList para la bitácora
        this.objetoPresente = null;
        this.estado = true; // Una casilla inicia por defecto viva
    }

    // Métodos
    public void recibirGolpe(int golpe) {
        if (this.getEstado()) { // Si la casilla está viva, esta recibe daño
            this.vida -= (golpe) * (100 - this.defensa);
            if (this.vida <= 0) {
                this.vida = 0; // No deja la vida en negativos
                this.estado = false; // Casilla deja de estar activa
                this.bitacora.add("La casilla (" + x + ", " + y + ") fue derrotada"); // Mensaje a la bitácora
                this.matriz.getCasillasActivas().remove(this); // La casilla se elimina del las casillas activas
                this.matriz.getRefCliente().actualizarPanelResumen(); // Actualizar las estadísticas del panel
            }
        }
    }

    public void curar(int cantidad) { // Función para curar la casilla
        this.vida += cantidad;
        if (this.vida < 100) { // if para evitar que las casillas se pasen del 100 de vida
            this.vida = 100;
        }
    }

    public void establecerDefensas(int cantidad) { // Función para establecer defensas
        this.defensa = cantidad; // Establece las defensas en la cantidad pasada (el héroe lo define)
    }

    public void bajarDefensas() { // Función para bajar las defensas de la casilla
        this.defensa = 0; // Vuelve a poner las defensas en 0
    }

    public String obtenerAtributosString() { // Función para obtener los atributos definidos de una casilla
        StringBuilder texto = new StringBuilder(); // Creamos un constructor de Strings
        Field[] campos = this.getClass().getDeclaredFields(); // Se consiguen los campos declarados de la clase que
                                                              // revisamos

        texto.append("      Atributos\n");

        for (Field campo : campos) { // Revisamos cada campo de campos individualmente
            System.out.println(campo);
            try { // Se rodea con un try
                if (!"bitacora".equals(campo.getName()) && !"matriz".equals(campo.getName())) // Se evita mostrar
                                                                                              // elementos no textuales,
                                                                                              // como la bitácora y la
                                                                                              // matriz
                    if (campo.get(this) == null) // Si el campo está vacío
                        texto.append("• ")
                                .append(campo.getName()) // Si lo es, pone 'no tiene' para evitar el null
                                .append(": no  tiene")
                                .append("\n"); // Separador entre datos
                    else if ("hero".equals(campo.getName())) { // Si el campo que vemos es el del héroe
                        Hero heroCasilla = (Hero) campo.get(this); // Se hace un casting al campo del héroe
                        texto.append("• ")
                                .append(campo.getName())
                                .append(": ")
                                .append(heroCasilla.getNombre()) // Se imprime el nombre del héroe (si no se hiciera
                                                                 // esto, se mostraría su dirección de memoria, y no
                                                                 // queremos eso
                                .append("\n"); // Separador entre datos
                    } else
                        texto.append("• ")
                                .append(campo.getName())
                                .append(": ")
                                .append(campo.get(this))
                                .append("\n"); // Separador entre datos
            } catch (IllegalAccessException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        return texto.toString();
    }

    public String obtenerBitacoraString() { // Función para mostrar la información de la bitácora de la casilla
        StringBuilder texto = new StringBuilder(); // Creamos un StringBuilder

        texto.append("      Bitácora\n");

        if (bitacora.isEmpty()) {
            texto.append("(Sin eventos registrados aún)\n");
        } else { // Si hay texto, lo añade al StringBuilder
            for (String evento : bitacora) {
                texto.append("• ")
                        .append(evento).append("\n");
            }
        }

        return texto.toString(); // Devuelve la bitácora hecha string para mostrarla
    }

    public String mostrarInfoCasilla() { // Función que junta los atributos y la bitácora de una casilla
        // Combinar los textos en un solo String
        StringBuilder texto = new StringBuilder();

        texto.append("====================\n")
                .append("INFORMACIÓN DE LA CASILLA (").append(this.getX()).append(", ").append(this.getY())
                .append(")\n") // Ponemos el mensaje sobre quién es la bitácora
                .append("====================\n")
                .append(obtenerAtributosString()) // Primero los datos generales
                .append("\n---------------------------\n") // Separador visual
                .append(obtenerBitacoraString()); // Luego la bitácora

        // Mostrar todo junto en el área de texto

        return texto.toString();
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

    public Hero getHero() {
        return hero;
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