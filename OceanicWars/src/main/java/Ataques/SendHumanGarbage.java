/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Ataques.ElementosAtaques.*;
import Cliente.Casilla;
import Cliente.Jugador;
import Hero.Hero;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public class SendHumanGarbage extends Ataque {
    // Atributos
    private Point casillaElegida;
    private int golpeTonelada;
    
    // Constructor
    public SendHumanGarbage(Hero hero, Jugador contrincante, Point casillaElegida) {
        super(hero, contrincante);
        this.casillaElegida = casillaElegida;  // Se asigna la casilla
        this.golpeTonelada = (25 * (1 + hero.getFuerzaAtaque()));  // La basura pega más si el ataque está potenciado (fuera del cíclo para que se aplique a todos los pedazos)
    }
    
    // TODO: REVISAR EN EL CHAT SI LA CASILLA ELEGIDA TIENE UN REMOLINO
    
    // Métodos
    @Override
    public void ejecutar() {
        // Elección de casilla
        try {

            Casilla casilla = matriz.getMatriz()[this.casillaElegida.x][this.casillaElegida.y];  // Toma la casilla elegida por el usuario
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada como el épicentro del 'Send Human Garbage' de " + hero.getNombre());
            int toneladasObtenidas = ((Remolino) casilla.getObjetoPresente()).getRadio() * 10;  // Se obtiene el radio original del remolino y se multiplica por 10
            for (int i = 0; i < toneladasObtenidas; i++) {  // Se genera la cantidad de basura necesaria
                Casilla c = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
                c.recibirGolpe(this.golpeTonelada);  // Golpea a la casilla
                c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por un pedazo de basura del 'Send Human Garbage' de " + hero.getNombre() + ", recibiendo " + this.golpeTonelada + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
                c.setObjetoPresente(new BasuraRadioactiva(rand.nextBoolean()));  // Se genera una basura radioactiva en la casilla
                c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") ahora está ocupada por basura potencialmente radiactiva debido al 'Send Human Garbage' de " + hero.getNombre());  // Mensaje agregado a la bitácora de la casilla
            }        
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
        }
    }
    
    // Getters
    public Point getCasillaElegida() {
        return casillaElegida;
    }

    public int getGolpeTonelada() {
        return golpeTonelada;
    }
    
}