/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Cliente.Casilla;
import Cliente.Jugador;
import Cliente.Matriz;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public abstract class Hero implements Serializable {
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
    // TODO: Poner mensaje en pantalla
    public void Heal(){
        for (Casilla c : this.casillasEnPosesion) {  // Se revisan todas las casillas
            if (!c.getEstado())  {  // Si la casilla está viva
                c.curar(sanidad);  // Se cura por la sanidad del héroe
            }
        }
    }
    
    public void Strengthen() {  // Método para potenciar el siguiente ataque del usuario
        this.siguientePotenciado = true;
    }
    
    public void Protect() {
        for (Casilla c : this.casillasEnPosesion) {  // Se revisan todas las casillas
            if (!c.getEstado())  {  // Si la casilla está viva
                c.establecerDefensas(resistencia);  // Se suben las defensas de las casillas
            }
        }
    }
    
    public abstract void realizarAtaque(Jugador atacado,String[] comando);

    // Valida los parámetros del ataque (equivalente al antiguo buscarAtaque)
    public abstract boolean validarHeroes(String[] comando); //Es boolean para indicar si el ataque se realizo con exito

    // Comprueba únicamente si el nombre del ataque existe para este héroe
    public abstract boolean buscarHeroes(String attackName);
    
    public boolean activarBoost(String boostCommand){ //Boolean para validar si es correcto o no el boost enviado
        
        switch(boostCommand) {   
            case "PROTECT":
                this.Protect();
                return true;
            case "STRENGTHEN":
                this.Strengthen();
                return true;
            case "HEAL":
                this.Heal();
                return true;
            default:
                return false;     
        }
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

    public boolean isSiguientePotenciado() {
        return siguientePotenciado;
    }

    // Helper: valida que exista una casilla válida en los parámetros x/y en posiciones 4/5
    protected boolean validarParCoords(String[] comando) {
        if (comando == null || comando.length < 6) return false;
        if (this.getMatrizAtaque() == null) return false;
        try {
            int x = Integer.parseInt(comando[4]);
            int y = Integer.parseInt(comando[5]);
            if (x < 0 || y < 0 || x >= this.getMatrizAtaque().getCantidadFilas() || y >= this.getMatrizAtaque().getCantidadColumnas()) return false;
            Cliente.Casilla c = this.getMatrizAtaque().getMatriz()[x][y];
            if (c == null) return false;
            if (c.getObjetoPresente() != null) return false;
        } catch (NumberFormatException ex) { return false; }
        return true;
    }

    // Helper: valida x,y en posiciones 4/5 y una dirección en posición 6
    protected boolean validarCoordAndDir(String[] comando) {
        if (comando == null || comando.length < 7) return false;
        if (this.getMatrizAtaque() == null) return false;
        try {
            int x = Integer.parseInt(comando[4]);
            int y = Integer.parseInt(comando[5]);
            String dir = comando[6].toUpperCase();
            if (x < 0 || y < 0 || x >= this.getMatrizAtaque().getCantidadFilas() || y >= this.getMatrizAtaque().getCantidadColumnas()) return false;
            Cliente.Casilla c = this.getMatrizAtaque().getMatriz()[x][y];
            if (c == null) return false;
            // Validate direction enum
            Ataques.ElementosAtaques.Direcciones.valueOf(dir);
        } catch (NumberFormatException ex) { return false; }
        catch (IllegalArgumentException ex) { return false; }
        return true;
    }

    // Helper: valida que haya al menos `minPairs` pares de coordenadas (empezando en índice 4)
    protected boolean validarNCoords(String[] comando, int minPairs) {
        if (comando == null) return false;
        int extras = comando.length - 4;
        if (extras < 2 * minPairs) return false;
        if (this.getMatrizAtaque() == null) return false;
        try {
            for (int i = 4; i + 1 < comando.length; i += 2) {
                int x = Integer.parseInt(comando[i]);
                int y = Integer.parseInt(comando[i + 1]);
                if (x < 0 || y < 0 || x >= this.getMatrizAtaque().getCantidadFilas() || y >= this.getMatrizAtaque().getCantidadColumnas()) return false;
                Cliente.Casilla c = this.getMatrizAtaque().getMatriz()[x][y];
                if (c == null) return false;
                if (c.getObjetoPresente() != null) return false;
            }
        } catch (NumberFormatException ex) { return false; }
        return true;
    }

    // Helper: valida que haya al menos `minNums` enteros en los extras (empezando en índice 4)
    protected boolean validarNNumeros(String[] comando, int minNums) {
        if (comando == null) return false;
        int extras = comando.length - 4;
        if (extras < minNums) return false;
        try {
            for (int i = 4; i < 4 + minNums && i < comando.length; i++) {
                Integer.parseInt(comando[i]);
            }
        } catch (NumberFormatException ex) { return false; }
        return true;
    }

    // Consumir el boost: se usa cuando se ejecuta un ataque potenciado
    public void consumirStrengthen() {
        this.siguientePotenciado = false;
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

    public boolean isEstado() {
        return estado;
    }
    
    
}
