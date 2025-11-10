package Hero;

import java.awt.Color;

/**
 * Héroe Zanka - Arquetipo: Thunders Under The Sea
 * 
 * @author kokoju
 */
public class Zanka extends ThundersUnderTheSea {
    public static final String IMAGE_PATH = "/zanka.png";

    public Zanka(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Zanka", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
