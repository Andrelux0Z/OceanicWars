package Models;

import java.io.Serializable;
import java.util.Arrays;
import Hero.HeroPackage;

/**
 * DTO serializable para describir un ataque entre clientes/servidor.
 */
public class AttackPayload implements Serializable {
    private String attackerName;
    private String targetName;
    private String heroType;
    private String attackType;
    private String[] extras; // parámetros adicionales (coords, radios, etc.)
    private Hero.HeroPackage heroPackage;

    public AttackPayload(String attackerName, String targetName, String heroType, String attackType, String[] extras) {
        this(attackerName, targetName, heroType, attackType, extras, null);
    }

    public AttackPayload(String attackerName, String targetName, String heroType, String attackType, String[] extras, HeroPackage heroPackage) {
        this.attackerName = attackerName;
        this.targetName = targetName;
        this.heroType = heroType;
        this.attackType = attackType;
        this.extras = extras == null ? new String[0] : extras;
        this.heroPackage = heroPackage;
    }

    public String getAttackerName() {    return attackerName; }
    public String getTargetName() {      return targetName; }
    public String getHeroType() {        return heroType; }
    public String getAttackType() {      return attackType; }
    public String[] getExtras() {        return extras; }
    public HeroPackage getHeroPackage(){ return heroPackage; }

}
