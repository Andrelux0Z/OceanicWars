package Hero.Customs;

import Hero.ThundersUnderTheSea;
import java.awt.Color;

/**
 * Héroe Goku - Arquetipo: Thunders Under The Sea
 * 
 * @author kokoju
 */
public class Goku extends ThundersUnderTheSea {
    public static final String IMAGE_PATH = "/goku.png";
    public static final Color COLOR = new Color(255, 140, 0); // Naranja

    public Goku(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Goku", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
