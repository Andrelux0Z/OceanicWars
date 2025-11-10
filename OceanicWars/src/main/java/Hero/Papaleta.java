package Hero;

import java.awt.Color;

/**
 * Héroe Papaleta - Arquetipo: Release The Kraken
 * 
 * @author kokoju
 */
public class Papaleta extends ReleaseTheKrakenHero {
    public static final String IMAGE_PATH = "/papaleta.png";

    public Papaleta(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Papaleta", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
