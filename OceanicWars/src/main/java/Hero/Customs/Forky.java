package Hero.Customs;

import Hero.UnderseaFire;
import java.awt.Color;

/**
 * Héroe Forky - Arquetipo: Undersea Fire
 * 
 * @author kokoju
 */
public class Forky extends UnderseaFire {
    public static final String IMAGE_PATH = "/forky.png";
    public static final Color COLOR = new Color(255, 69, 0); // Rojo-naranja

    public Forky(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Forky", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
