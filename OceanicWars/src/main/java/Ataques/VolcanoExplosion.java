/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Ataques.ElementosAtaques.Volcan;
import Cliente.Casilla;
import Cliente.Jugador;
import Hero.Hero;
import java.awt.Point;

/**
 *
 * @author kokoju
 */
public class VolcanoExplosion extends Ataque {
    // Atributos
    private Point casillaElegida;
    private int golpePiedra;
    
    // Constructor
    public VolcanoExplosion(Hero hero, Jugador contrincante, Point casillaElegida) {
        super(hero, contrincante);
        this.casillaElegida = casillaElegida;  // Se asigna la casilla
        this.golpePiedra = (20 * (1 + hero.getFuerzaAtaque()));  // Golpe aplicado por cada piedra de la erupción
    }
    
    // TODO: REVISAR EN EL CHAT SI LA CASILLA ELEGIDA TIENE UN VOLCAN
    
    // Métodos
    @Override
    public void ejecutar() {
        // Elección de casilla
        try {

            Casilla casilla = matriz.getMatriz()[this.casillaElegida.x][this.casillaElegida.y];  // Toma la casilla elegida por el usuario
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada como el épicentro del 'Volcano Explosion' de " + hero.getNombre());
            int piedrasObtenidas = ((Volcan) casilla.getObjetoPresente()).getRadio() * 10;  // Se obtiene el radio original del volcán y se multiplica por 10
            for (int i = 0; i < piedrasObtenidas; i++) {
                Casilla c = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
                c.recibirGolpe(this.golpePiedra);  // Golpea a la casilla
                c.getBitacora().add("La casilla (" + c.getX() + ", " + c.getY() + ") fue golpeada por una piedra del 'Volcano Explosion' de " + hero.getNombre() + ", recibiendo " + this.golpePiedra + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
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

    public int getGolpePiedra() {
        return golpePiedra;
    }
}