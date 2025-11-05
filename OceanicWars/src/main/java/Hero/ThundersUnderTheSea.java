/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;
import Ataques.Ataque;
import Ataques.ThunderRain;

/**
 *
 * @author kokoju
 */
public class ThundersUnderTheSea extends Hero {
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia)
    
    public ThundersUnderTheSea(String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Thunders Under The Sea", imagen, color, ocupacion, sanidad, fuerza resistencia);
    }
    
    @Override
    public void habilidad1() {
        Ataque habilidad1 = new ThunderRain(this);
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
