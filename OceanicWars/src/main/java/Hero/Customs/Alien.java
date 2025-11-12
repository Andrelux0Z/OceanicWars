package Hero.Customs;

import Hero.FishTelepathy;
import java.awt.Color;

/**
 * Héroe Alien - Arquetipo: Fish Telepathy
 * 
 * @author kokoju
 */
public class Alien extends FishTelepathy {
    public static final String IMAGE_PATH = "/alien.png";
    public static final Color COLOR = new Color(0, 255, 0); // Verde

    public Alien(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Alien", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
