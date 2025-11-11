/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.ElementosAtaques.Direcciones;
import Ataques.KrakenBreath;
import Ataques.ReleaseTheKraken;
import Ataques.Tentaculos;
import Cliente.Casilla;
import Cliente.Jugador;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public class ReleaseTheKrakenHero extends Hero {
    // Color por defecto para este arquetipo
    public static final Color COLOR_DEFAULT = Color.MAGENTA;

    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int
    // fuerza, int resistencia)
    public ReleaseTheKrakenHero(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza,
            int resistencia) {
        super(nombre, imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }


    public void habilidad1(Jugador contrincante, ArrayList<Point> casillasElegidas) {
        Ataque habilidad1 = new Tentaculos(this, contrincante, casillasElegidas);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante, Point casillaElegida, Direcciones direccion) {
        Ataque habilidad2 = new KrakenBreath(this, contrincante, casillaElegida, direccion);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad3 = new ReleaseTheKraken(this, contrincante, casillaElegida);
        habilidad3.ejecutar();
    }

    //Todo buscarAtaque y realizarAtaque estan hechos con copilot, es algo provisional
    @Override
    public boolean buscarAtaque(String[] comando) {
        if (comando == null || comando.length < 4) return false;
        String tipo = comando[3].toUpperCase();
        switch (tipo) {
            case "TENTACULOS": {
                // expect pairs of coords (at least 1 pair)
                int extras = comando.length - 4;
                if (extras < 2 || extras % 2 != 0) return false;
                if (this.getMatrizAtaque() == null) return false;
                for (int i = 4; i + 1 < comando.length; i += 2) {
                    try {
                        int x = Integer.parseInt(comando[i]);
                        int y = Integer.parseInt(comando[i + 1]);
                        if (x < 0 || y < 0 || x >= this.getMatrizAtaque().getCantidadFilas() || y >= this.getMatrizAtaque().getCantidadColumnas()) return false;
                        Casilla c = this.getMatrizAtaque().getMatriz()[x][y];
                        if (c == null) return false;
                        if (c.getObjetoPresente() != null) return false;
                    } catch (NumberFormatException ex) { return false; }
                }
                return true;
            }
            case "KRAKENBREATH": {
                // expect x y direction
                if (comando.length < 7) return false;
                if (this.getMatrizAtaque() == null) return false;
                try {
                    int x = Integer.parseInt(comando[4]);
                    int y = Integer.parseInt(comando[5]);
                    String dir = comando[6].toUpperCase();
                    if (x < 0 || y < 0 || x >= this.getMatrizAtaque().getCantidadFilas() || y >= this.getMatrizAtaque().getCantidadColumnas()) return false;
                    Casilla c = this.getMatrizAtaque().getMatriz()[x][y];
                    if (c == null) return false;
                    if (c.getObjetoPresente() != null) return false;
                    Direcciones.valueOf(dir); // validate direction (will throw if invalid)
                } catch (Exception ex) { return false; }
                return true;
            }
            case "RELEASETHEKRAKEN": {
                // expect single coordinate pair
                if (comando.length < 6) return false;
                if (this.getMatrizAtaque() == null) return false;
                try {
                    int x = Integer.parseInt(comando[4]);
                    int y = Integer.parseInt(comando[5]);
                    if (x < 0 || y < 0 || x >= this.getMatrizAtaque().getCantidadFilas() || y >= this.getMatrizAtaque().getCantidadColumnas()) return false;
                    Casilla c = this.getMatrizAtaque().getMatriz()[x][y];
                    if (c == null) return false;
                    if (c.getObjetoPresente() != null) return false;
                } catch (NumberFormatException ex) { return false; }
                return true;
            }
            default:
                return false;
        }
    }

    @Override
    public void realizarAtaque(Jugador atacado, String[] comando) {
        if (comando == null || comando.length < 4) return;
        String tipo = comando[3].toUpperCase();
        switch (tipo) {
            case "TENTACULOS": {
                ArrayList<Point> pts = new ArrayList<>();
                for (int i = 4; i + 1 < comando.length; i += 2) {
                    try { pts.add(new Point(Integer.parseInt(comando[i]), Integer.parseInt(comando[i + 1]))); } catch (Exception e) {}
                }
                if (!pts.isEmpty()) this.habilidad1(atacado, pts);
                break;
            }
            case "KRAKENBREATH": {
                try {
                    int x = Integer.parseInt(comando[4]);
                    int y = Integer.parseInt(comando[5]);
                    Direcciones dir = Direcciones.valueOf(comando[6].toUpperCase());
                    this.habilidad2(atacado, new Point(x, y), dir);
                } catch (Exception e) {}
                break;
            }
            case "RELEASETHEKRAKEN": {
                try {
                    int x = Integer.parseInt(comando[4]);
                    int y = Integer.parseInt(comando[5]);
                    this.habilidad3(atacado, new Point(x, y));
                } catch (Exception e) {}
                break;
            }
        }
    }

    public String getArquetipo() { 
        return "Release The Kraken"; 
    }

}
