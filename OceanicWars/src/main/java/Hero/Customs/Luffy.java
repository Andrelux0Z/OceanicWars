package Hero.Customs;

import Hero.WavesControl;
import java.awt.Color;

/**
 * Héroe Luffy - Arquetipo: Waves Control
 * 
 * @author kokoju
 */
public class Luffy extends WavesControl {
    public static final String IMAGE_PATH = "/luffy.png";
    public static final Color COLOR = new Color(255, 215, 0); // Dorado

    public Luffy(Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super("Luffy", IMAGE_PATH, color, ocupacion, sanidad, fuerza, resistencia);
    }
}
