/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.TermalRush;
import Ataques.VolcanoExplosion;
import Ataques.VolcanoRaising;
import Cliente.Jugador;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public class UnderseaFire extends Hero {
    // Color por defecto para este arquetipo
    public static final Color COLOR_DEFAULT = Color.RED;

    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int
    // fuerza, int resistencia)
    public UnderseaFire(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza,
            int resistencia) {
        super(nombre, imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }


    public void habilidad1(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad1 = new VolcanoRaising(this, contrincante, casillaElegida);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad2 = new VolcanoExplosion(this, contrincante, casillaElegida);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad3 = new TermalRush(this, contrincante, casillaElegida);
        habilidad3.ejecutar();
    }

    //TODO validarHeroes y realizarAtaque estan hechos con copilot, es algo provisional
    @Override
    public boolean validarHeroes(String[] comando) {
        if (comando == null || comando.length < 4) return false;
        String tipo = comando[3].toUpperCase();
        switch (tipo) {
            case "VOLCANORAISING":
            case "VOLCANOEXPLOSION":
            case "TERMALRUSH": {
                return this.validarParCoords(comando);
            }
            default:
                return false;
        }
    }

    @Override
    public boolean buscarHeroes(String attackName) {
        if (attackName == null) return false;
        String tipo = attackName.toUpperCase();
        switch (tipo) {
            case "VOLCANORAISING":
            case "VOLCANOEXPLOSION":
            case "TERMALRUSH":
                return true;
            default:
                return false;
        }
    }

    @Override
    public void realizarAtaque(Jugador atacado, String[] comando) {
        if (comando == null || comando.length < 4) return;
        String tipo = comando[3].toUpperCase();
        switch (tipo) {
            case "VOLCANORAISING":
            case "VOLCANOEXPLOSION":
            case "TERMALRUSH": {
                try {
                    int x = Integer.parseInt(comando[4]);
                    int y = Integer.parseInt(comando[5]);
                    Point p = new Point(x, y);
                    if ("VOLCANORAISING".equals(tipo)) this.habilidad1(atacado, p);
                    else if ("VOLCANOEXPLOSION".equals(tipo)) this.habilidad2(atacado, p);
                    else this.habilidad3(atacado, p);
                } catch (Exception ex) { }
                break;
            }
        }
    }

    public String getArquetipo() { 
        return "Undersea Fire"; 
    }

}
