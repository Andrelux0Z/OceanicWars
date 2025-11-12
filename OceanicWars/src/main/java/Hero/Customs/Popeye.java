package Hero.Customs;

import Hero.WavesControl;
import java.awt.Color;

/**
 * Héroe Popeye - Arquetipo: Waves Control
 * 
 * @author kokoju
 */
public class Popeye extends WavesControl {
    public static final String IMAGE_PATH = "/Popeye_transparent.png";
    public static final Color COLOR = new Color(0, 191, 255); // Azul cielo

    public Popeye(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Popeye", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
