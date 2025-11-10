package Hero;

import java.awt.Color;

/**
 * Héroe Frisk - Arquetipo: Fish Telepathy
 * 
 * @author kokoju
 */
public class Frisk extends FishTelepathy {
    public static final String IMAGE_PATH = "/frisk.png";

    public Frisk(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Frisk", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
