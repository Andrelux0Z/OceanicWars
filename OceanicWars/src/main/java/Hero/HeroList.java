package Hero;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

//Hub centralizado con la información de aspecto (color e imagen) de todos los héroes disponibles.
public class HeroList {

    // Clase interna para almacenar la información de aspecto de un héroe
    public static class AparienciaHeroe {
        private final String imagePath;
        private final Color color;

        public AparienciaHeroe(String imagePath, Color color) {
            this.imagePath = imagePath;
            this.color = color;
        }

        public String getImagePath() {
            return imagePath;
        }

        public Color getColor() {
            return color;
        }
    }

    // Mapa que almacena la información de aspecto de cada héroe
    private static final Map<String, AparienciaHeroe> HEROES = new HashMap<>();

    static {
        HEROES.put("Goku", new AparienciaHeroe("/goku.png", new Color(255, 140, 0))); // Naranja
        HEROES.put("Zanka", new AparienciaHeroe("/zanka.png", new Color(0, 0, 255))); // Azul
        HEROES.put("Pim", new AparienciaHeroe("/pim.png", new Color(253, 181, 246))); // Rosado
        HEROES.put("Alien", new AparienciaHeroe("/alien.png", new Color(0, 255, 0))); // Verde
        HEROES.put("Frisk", new AparienciaHeroe("/frisk.png", new Color(255, 0, 255))); // Magenta
        HEROES.put("Omniman", new AparienciaHeroe("/omniman.png", new Color(255, 0, 0))); // Rojo
        HEROES.put("James", new AparienciaHeroe("/james.png", new Color(0, 128, 128))); // Teal
        HEROES.put("Papaleta", new AparienciaHeroe("/papaleta.png", new Color(139, 69, 19))); // Marrón
        HEROES.put("Charlie", new AparienciaHeroe("/charlie.png", new Color(254, 255, 113))); // Amarillo
        HEROES.put("Luffy", new AparienciaHeroe("/luffy.png", new Color(255, 215, 0))); // Dorado
        HEROES.put("Popeye", new AparienciaHeroe("/Popeye_transparent.png", new Color(0, 191, 255))); // Azul cielo
        HEROES.put("Forky", new AparienciaHeroe("/forky.png", new Color(255, 69, 0))); // Rojo-naranja
        HEROES.put("Penitente", new AparienciaHeroe("/penitente.png", new Color(220, 20, 60))); // Carmesí
    }

    // Obtiene la información de aspecto de un héroe por su nombre
    public static AparienciaHeroe getApariencia(String heroName) {
        return HEROES.get(heroName);
    }

    // Obtiene todos los nombres de héroes disponibles
    public static String[] getAllHeroNames() {
        return HEROES.keySet().toArray(new String[0]);
    }

    // Crea un héroe basándose en el arquetipo seleccionado con una apariencia
    // personalizada
    public static Hero createHero(String arquetipo, String nombre, String imagePath, Color color,
            int ocupacion, int sanidad, int fuerza, int resistencia) {
        Hero heroe = null;

        switch (arquetipo) {
            case "Thunders Under The Sea":
                heroe = new ThundersUnderTheSea(nombre, imagePath, color, ocupacion, sanidad, fuerza, resistencia);
                break;
            case "Fish Telepathy":
                heroe = new FishTelepathy(nombre, imagePath, color, ocupacion, sanidad, fuerza, resistencia);
                break;
            case "Release The Kraken":
                heroe = new ReleaseTheKrakenHero(nombre, imagePath, color, ocupacion, sanidad, fuerza, resistencia);
                break;
            case "Waves Control":
                heroe = new WavesControl(nombre, imagePath, color, ocupacion, sanidad, fuerza, resistencia);
                break;
            case "The Trident":
                heroe = new PoseidonTrident(nombre, imagePath, color, ocupacion, sanidad, fuerza, resistencia);
                break;
            case "Undersea Volcanoes":
                heroe = new UnderseaFire(nombre, imagePath, color, ocupacion, sanidad, fuerza, resistencia);
                break;
        }

        return heroe;
    }
}
