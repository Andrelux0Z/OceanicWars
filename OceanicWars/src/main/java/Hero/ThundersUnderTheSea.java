/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.EelAttack;
import Ataques.PoseidonThunders;
import Ataques.ThunderRain;
import Cliente.Jugador;
import java.awt.Color;

/**
 *
 * @author kokoju
 */
public abstract class ThundersUnderTheSea extends Hero {
    // Color por defecto para este arquetipo
    public static final Color COLOR_DEFAULT = Color.BLUE;

    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int
    // fuerza, int resistencia)
    public ThundersUnderTheSea(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza,
            int resistencia) {
        super(nombre, imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }

    public void habilidad1(Jugador contrincante) {
        Ataque habilidad1 = new ThunderRain(this, contrincante);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante) {
        Ataque habilidad2 = new PoseidonThunders(this, contrincante);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante) {
        Ataque habilidad3 = new EelAttack(this, contrincante);
        habilidad3.ejecutar();
    }

}
