/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.ControlTheKraken;
import Ataques.ThreeLines;
import Ataques.ThreeNumbers;
import Cliente.Jugador;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public class PoseidonTrident extends Hero {
    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia)
    public PoseidonTrident(String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Poseidon Trident", imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }
    
    public void habilidad1(Jugador contrincante, ArrayList<Point> casillasElegidas) {
        Ataque habilidad1 = new ThreeLines(this, contrincante, casillasElegidas);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante, ArrayList<Integer> numerosElegidos) {
        Ataque habilidad2 = new ThreeNumbers(this, contrincante, numerosElegidos);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad3 = new ControlTheKraken(this, contrincante, casillaElegida);
        habilidad3.ejecutar();
    }
}
