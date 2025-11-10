/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.ElementosAtaques.Direcciones;
import Ataques.KrakenBreath;
import Ataques.ReleaseTheKraken;
import Ataques.Tentaculos;
import Cliente.Jugador;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public abstract class ReleaseTheKrakenHero extends Hero {
    // Color por defecto para este arquetipo
    public static final Color COLOR_DEFAULT = Color.MAGENTA;

    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int
    // fuerza, int resistencia)
    public ReleaseTheKrakenHero(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza,
            int resistencia) {
        super(nombre, imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }

    public ReleaseTheKrakenHero() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void habilidad1(Jugador contrincante, ArrayList<Point> casillasElegidas) {
        Ataque habilidad1 = new Tentaculos(this, contrincante, casillasElegidas);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante, Point casillaElegida, Direcciones direccion) {
        Ataque habilidad2 = new KrakenBreath(this, contrincante, casillaElegida, direccion);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad3 = new ReleaseTheKraken(this, contrincante, casillaElegida);
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
        return "Release The Kraken";
    }
}
