/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.ControlTheKraken;
import Ataques.ThreeLines;
import Ataques.ThreeNumbers;
import Cliente.Casilla;
import Cliente.Jugador;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author kokoju
 */

public class PoseidonTrident extends Hero {
    // Color por defecto para este arquetipo
    public static final Color COLOR_DEFAULT = Color.GREEN;

    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int
    // fuerza, int resistencia)
    public PoseidonTrident(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza,
            int resistencia) {
        super(nombre, imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }



    public void habilidad1(Jugador contrincante, ArrayList<Point> casillasElegidas) {
        Ataque habilidad1 = new ThreeLines(this, contrincante, casillasElegidas);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante, ArrayList<Integer> numerosElegidos) {
        Ataque habilidad2 = new ThreeNumbers(this, contrincante, numerosElegidos);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad3 = new ControlTheKraken(this, contrincante, casillaElegida);
        habilidad3.ejecutar();
    }


    //TODO buscarAtaque y realizarAtaque estan hechos con copilot, es algo provisional
    @Override
    public boolean buscarAtaque(String[] comando) {
        if (comando == null || comando.length < 4) return false;
        String tipo = comando[3].toUpperCase();
        // extras start at index 4
        switch (tipo) {
            case "THREELINES": {
                // Expect pairs of coordinates: at least 3 points => 6 numbers
                int extras = comando.length - 4;
                if (extras < 6 || extras % 2 != 0) return false;
                if (this.getMatrizAtaque() == null) return false;
                for (int i = 4; i + 1 < comando.length; i += 2) {
                    try {
                        int x = Integer.parseInt(comando[i]);
                        int y = Integer.parseInt(comando[i + 1]);
                        if (x < 0 || y < 0 || x >= this.getMatrizAtaque().getCantidadFilas() || y >= this.getMatrizAtaque().getCantidadColumnas()) return false;
                        Casilla c = this.getMatrizAtaque().getMatriz()[x][y];
                        if (c == null) return false;
                        if (c.getObjetoPresente() != null) return false; // no debe haber objeto en la casilla
                    } catch (NumberFormatException ex) { return false; }
                }
                return true;
            }
            case "THREENUMBERS": {
                // Expect 3 integer numbers
                int extras = comando.length - 4;
                if (extras < 3) return false;
                for (int i = 4; i < comando.length && i < 7; i++) {
                    try { Integer.parseInt(comando[i]); } catch (NumberFormatException ex) { return false; }
                }
                return true;
            }
            case "CONTROLTHEKRAKEN": {
                // Expect a single coordinate pair
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
            case "THREELINES": {
                ArrayList<Point> pts = new ArrayList<>();
                for (int i = 4; i + 1 < comando.length; i += 2) {
                    try {
                        int x = Integer.parseInt(comando[i]);
                        int y = Integer.parseInt(comando[i + 1]);
                        pts.add(new Point(x, y));
                    } catch (NumberFormatException ex) { }
                }
                if (!pts.isEmpty()) this.habilidad1(atacado, pts);
                break;
            }
            case "THREENUMBERS": {
                ArrayList<Integer> nums = new ArrayList<>();
                for (int i = 4; i < comando.length && nums.size() < 3; i++) {
                    try { nums.add(Integer.parseInt(comando[i])); } catch (NumberFormatException ex) { }
                }
                if (!nums.isEmpty()) this.habilidad2(atacado, nums);
                break;
            }
            case "CONTROLTHEKRAKEN": {
                try {
                    int x = Integer.parseInt(comando[4]);
                    int y = Integer.parseInt(comando[5]);
                    this.habilidad3(atacado, new Point(x, y));
                } catch (Exception ex) { }
                break;
            }
        }
    }

    public String getArquetipo() { 
        return "Poseidon Trident"; 
    }

}
