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
    // Color por defecto para este héroe
    public static final Color COLOR_DEFAULT = Color.GREEN;

    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int
    // fuerza, int resistencia)
    public PoseidonTrident(String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Poseidon Trident", imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }

    public PoseidonTrident() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    public boolean buscarAtaque(String[] comando) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void realizarAtaque(Jugador atacado,String[] comando) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
