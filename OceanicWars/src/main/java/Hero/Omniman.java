package Hero;

import java.awt.Color;

/**
 * Héroe Omniman - Arquetipo: Poseidon Trident
 * 
 * @author kokoju
 */
public class Omniman extends PoseidonTrident {
    public static final String IMAGE_PATH = "/omniman.png";

    public Omniman(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Omniman", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
