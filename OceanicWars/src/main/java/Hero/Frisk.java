package Hero;

import java.awt.Color;

/**
 * Héroe Frisk - Arquetipo: Fish Telepathy
 * 
 * @author kokoju
 */
public class Frisk extends FishTelepathy {
    public static final String IMAGE_PATH = "/frisk.png";
    public static final Color COLOR = new Color(255, 0, 255); // Magenta

    public Frisk(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Frisk", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
