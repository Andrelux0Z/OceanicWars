package Hero;

import java.awt.Color;

/**
 * Héroe Forky - Arquetipo: Undersea Fire
 * 
 * @author kokoju
 */
public class Forky extends UnderseaFire {
    public static final String IMAGE_PATH = "/forky.png";

    public Forky(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Forky", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
