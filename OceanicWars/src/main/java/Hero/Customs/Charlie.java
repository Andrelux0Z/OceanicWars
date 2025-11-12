/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero.Customs;
import Hero.ReleaseTheKrakenHero;
import java.awt.Color;

/**
 * Héroe Charlie - Arquetipo: Release The Kraken
 * 
 * @author kokoju
 */
public class Charlie extends ReleaseTheKrakenHero {
    public static final String IMAGE_PATH = "/charlie.png";
    public static final Color COLOR = new Color(254, 255, 113); // Amarillo

    public Charlie(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Charlie", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
