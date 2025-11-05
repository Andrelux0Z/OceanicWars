/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import java.awt.Color;

/**
 *
 * @author kokoju
 */
public class ReleaseTheKraken extends Hero {
        // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia)
    public ReleaseTheKraken(String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Release The Kraken", imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }
    
    @Override
    public void habilidad1() {
        // Ataque habilidad1 = new ThunderRain(this);
        // habilidad1.ejecutar();
    }

    @Override
    public void habilidad2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void habilidad3() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
