/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.TermalRush;
import Ataques.VolcanoExplosion;
import Ataques.VolcanoRaising;
import Cliente.Jugador;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public abstract class UnderseaFire extends Hero {
    // Color por defecto para este arquetipo
    public static final Color COLOR_DEFAULT = Color.RED;

    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int
    // fuerza, int resistencia)
    public UnderseaFire(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza,
            int resistencia) {
        super(nombre, imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }

    public UnderseaFire() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void habilidad1(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad1 = new VolcanoRaising(this, contrincante, casillaElegida);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad2 = new VolcanoExplosion(this, contrincante, casillaElegida);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad3 = new TermalRush(this, contrincante, casillaElegida);
        habilidad3.ejecutar();
    }

    @Override
    public boolean buscarAtaque(String[] comando) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void realizarAtaque(Jugador atacado,String[] comando) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    public String getArquetipo() {
        return "Undersea Fire";
    }
}
