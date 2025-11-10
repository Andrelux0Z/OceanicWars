package Hero;

import java.awt.Color;

/**
 * Héroe James - Arquetipo: Poseidon Trident
 * 
 * @author kokoju
 */
public class James extends PoseidonTrident {
    public static final String IMAGE_PATH = "/james.png";

    public James(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("James", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
