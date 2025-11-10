package Hero;

import java.awt.Color;

/**
 * Héroe Penitente - Arquetipo: Undersea Fire
 * 
 * @author kokoju
 */
public class Penitente extends UnderseaFire {
    public static final String IMAGE_PATH = "/penitente.png";

    public Penitente(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Penitente", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
