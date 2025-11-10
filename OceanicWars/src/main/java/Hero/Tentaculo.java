package Hero;

import java.awt.Color;

/**
 * Héroe Tentaculo - Arquetipo: Release The Kraken
 * 
 * @author kokoju
 */
public class Tentaculo extends ReleaseTheKrakenHero {
    public static final String IMAGE_PATH = "/tentaculo.png";

    public Tentaculo(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Tentaculo", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
