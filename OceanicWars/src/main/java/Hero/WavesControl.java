/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.RadioactiveRush;
import Ataques.SendHumanGarbage;
import Ataques.SwirlRaising;
import Cliente.Jugador;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public abstract class WavesControl extends Hero {
    // Color por defecto para este arquetipo
    public static final Color COLOR_DEFAULT = Color.ORANGE;

    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int
    // fuerza, int resistencia)
    public WavesControl(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza,
            int resistencia) {
        super(nombre, imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }

    public WavesControl() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void habilidad1(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad1 = new SwirlRaising(this, contrincante, casillaElegida);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad2 = new SendHumanGarbage(this, contrincante, casillaElegida);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante) {
        Ataque habilidad3 = new RadioactiveRush(this, contrincante);
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
        return "Waves Control";
    }
}