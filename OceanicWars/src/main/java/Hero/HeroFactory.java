/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

/**
 *
 * @author sando
 */
public class HeroFactory {
    
    public static Hero getHero(String tipoHeroe) {

        switch (tipoHeroe) {
            case "FISHTELEPATHY": // correct spelling
                return new FishTelepathy();
            case "THUNDERSUNDERTHESEA":
                return new ThundersUnderTheSea();
            case "POSEIDONTRIDENT":
                return new PoseidonTrident();
            case "RELEASETHEKRAKEN":
                return new ReleaseTheKrakenHero();
            case "WAVESCONTROL":
                return new WavesControl();
            case "UNDERSEAFIRE":
                return new UnderseaFire();
            default:
                return null;
        }
    }
}
