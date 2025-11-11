
package Hero;

import java.io.Serializable;


/**
 *
 * @author sando
 */

public class HeroPackage implements Serializable {
    private String heroType; // simple class name e.g. FishTelepathy
    private String attackerName;
    private int ocupacion;
    private int sanidad;
    private int fuerza;
    private int resistencia;
    private boolean siguientePotenciado;

    public HeroPackage(String heroType, String attackerName, int ocupacion, int sanidad, int fuerza, int resistencia, boolean siguientePotenciado) {
        this.heroType = heroType;
        this.attackerName = attackerName;
        this.ocupacion = ocupacion;
        this.sanidad = sanidad;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.siguientePotenciado = siguientePotenciado;
    }

    public String getHeroType() { return heroType; }
    public String getAttackerName() { return attackerName; }
    public int getOcupacion() { return ocupacion; }
    public int getSanidad() { return sanidad; }
    public int getFuerza() { return fuerza; }
    public int getResistencia() { return resistencia; }
    public boolean isSiguientePotenciado() { return siguientePotenciado; }

    @Override
    public String toString() {
        return "HeroPackage{" + "heroType=" + heroType + ", attacker=" + attackerName + ", fuerza=" + fuerza + '}';
    }
}

