/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero.Customs;
import Hero.ThundersUnderTheSea;
import java.awt.Color;

/**
 * Héroe Pim - Arquetipo: Thunders Under The Sea
 * 
 * @author kokoju
 */
public class Pim extends ThundersUnderTheSea {
    public static final String IMAGE_PATH = "/zanka.png";
    public static final Color COLOR = new Color(253, 181, 246); // Rosado

    public Pim(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Pim", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
