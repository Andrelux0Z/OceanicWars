/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import java.util.Locale;

/**
 *
 * @author sando
 */

public class HeroFactory {

    private static final int DEFAULT_OCUPACION = 10;
    private static final int DEFAULT_SANIDAD = 10;
    private static final int DEFAULT_FUERZA = 10;
    private static final int DEFAULT_RESISTENCIA = 10;

    public static Hero createFromPackage(HeroPackage heroPaq) {
        if (heroPaq == null)
            return null;
        String key = heroPaq.getHeroType();
        switch (key) {
            case "FISHTELEPATHY": {
                FishTelepathy heroe = new FishTelepathy(heroPaq.getAttackerName(), "", FishTelepathy.COLOR_DEFAULT,
                        heroPaq.getOcupacion(), heroPaq.getSanidad(), heroPaq.getFuerza(), heroPaq.getResistencia());
                if (heroPaq.isSiguientePotenciado())
                    heroe.Strenghten();
                return heroe;
            }
            case "THUNDERSUNDERTHESEA": {
                ThundersUnderTheSea heroe = new ThundersUnderTheSea(heroPaq.getAttackerName(), "",
                        ThundersUnderTheSea.COLOR_DEFAULT, heroPaq.getOcupacion(), heroPaq.getSanidad(),
                        heroPaq.getFuerza(), heroPaq.getResistencia());
                if (heroPaq.isSiguientePotenciado())
                    heroe.Strenghten();
                return heroe;
            }
            case "POSEIDONTRIDENT": {
                PoseidonTrident heroe = new PoseidonTrident(heroPaq.getAttackerName(), "",
                        PoseidonTrident.COLOR_DEFAULT, heroPaq.getOcupacion(), heroPaq.getSanidad(),
                        heroPaq.getFuerza(), heroPaq.getResistencia());
                if (heroPaq.isSiguientePotenciado())
                    heroe.Strenghten();
                return heroe;
            }
            case "RELEASETHEKRAKEN": {
                ReleaseTheKrakenHero heroe = new ReleaseTheKrakenHero(heroPaq.getAttackerName(), "",
                        ReleaseTheKrakenHero.COLOR_DEFAULT, heroPaq.getOcupacion(), heroPaq.getSanidad(),
                        heroPaq.getFuerza(), heroPaq.getResistencia());
                if (heroPaq.isSiguientePotenciado())
                    heroe.Strenghten();
                return heroe;
            }
            case "WAVESCONTROL": {
                WavesControl heroe = new WavesControl(heroPaq.getAttackerName(), "", WavesControl.COLOR_DEFAULT,
                        heroPaq.getOcupacion(), heroPaq.getSanidad(), heroPaq.getFuerza(), heroPaq.getResistencia());
                if (heroPaq.isSiguientePotenciado())
                    heroe.Strenghten();
                return heroe;
            }
            case "UNDERSEAFIRE": {
                UnderseaFire heroe = new UnderseaFire(heroPaq.getAttackerName(), "", UnderseaFire.COLOR_DEFAULT,
                        heroPaq.getOcupacion(), heroPaq.getSanidad(), heroPaq.getFuerza(), heroPaq.getResistencia());
                if (heroPaq.isSiguientePotenciado())
                    heroe.Strenghten();
                return heroe;
            }
            default:
                return null;
        }
    }
}