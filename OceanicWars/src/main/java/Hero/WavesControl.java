/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.RadioactiveRush;
import Ataques.SendHumanGarbage;
import Ataques.ElementosAtaques.Remolino;
import Ataques.SwirlRaising;
import Cliente.Casilla;
import Cliente.Jugador;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public class WavesControl extends Hero {
    // Color por defecto para este arquetipo
    public static final Color COLOR_DEFAULT = Color.ORANGE;

    // Constructor
    // (String nombre, String imagen, Color color, int ocupacion, int sanidad, int
    // fuerza, int resistencia)
    public WavesControl(String nombre, String imagen, Color color, int ocupacion, int sanidad, int fuerza,
            int resistencia) {
        super(nombre, imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }

    public void habilidad1(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad1 = new SwirlRaising(this, contrincante, casillaElegida);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante, Point casillaElegida) {
        Ataque habilidad2 = new SendHumanGarbage(this, contrincante, casillaElegida);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante) {
        Ataque habilidad3 = new RadioactiveRush(this, contrincante);
        habilidad3.ejecutar();
    }

    //TODO validarHeroes y realizarAtaque estan hechos con copilot, es algo provisional
    @Override
    public boolean validarHeroes(String[] comando) {
        if (comando == null || comando.length < 4) return false;
        String tipo = comando[3].toUpperCase();
        switch (tipo) {
            case "SWIRLRAISING": {
                return this.validarParCoords(comando);
            }
            case "SENDHUMANGARBAGE": {
                // SendHumanGarbage requires targeting an existing Remolino
                if (comando.length < 6) return false;
                if (this.getMatrizAtaque() == null) return false;
                try {
                    int x = Integer.parseInt(comando[4]);
                    int y = Integer.parseInt(comando[5]);
                    if (x < 0 || y < 0 || x >= this.getMatrizAtaque().getCantidadFilas() || y >= this.getMatrizAtaque().getCantidadColumnas()) return false;
                    Casilla c = this.getMatrizAtaque().getMatriz()[x][y];
                    if (c == null) return false;
                    Object obj = c.getObjetoPresente();
                    if (!(obj instanceof Remolino)) return false; // object must be a Remolino
                } catch (NumberFormatException ex) { return false; }
                return true;
            }
            case "RADIOACTIVERUSH":
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean buscarHeroes(String attackName) {
        if (attackName == null) return false;
        String tipo = attackName.toUpperCase();
        switch (tipo) {
            case "SWIRLRAISING":
            case "SENDHUMANGARBAGE":
            case "RADIOACTIVERUSH":
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
            case "SWIRLRAISING":
            case "SENDHUMANGARBAGE": {
                try {
                    int x = Integer.parseInt(comando[4]);
                    int y = Integer.parseInt(comando[5]);
                    this.habilidad1(atacado, new Point(x, y)); // for SWIRL use habilidad1, for SEND use habilidad2 below
                    if ("SENDHUMANGARBAGE".equals(tipo)) this.habilidad2(atacado, new Point(x, y));
                } catch (Exception ex) { }
                break;
            }
            case "RADIOACTIVERUSH":
                this.habilidad3(atacado);
                break;
        }
    }

    public String getArquetipo() { 
        return "Waves Control"; 
    }

}