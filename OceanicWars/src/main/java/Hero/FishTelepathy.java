/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hero;

import Ataques.Ataque;
import Ataques.Cardumen;
import Ataques.Pulp;
import Ataques.SharkAttack;
import Cliente.Jugador;
import java.awt.Color;

/**
 * FishTelepathy hero implementation
 */
public class FishTelepathy extends Hero {
    public static final Color COLOR_DEFAULT = Color.CYAN;

    public FishTelepathy(String nombre,String imagen, Color color, int ocupacion, int sanidad, int fuerza, int resistencia) {
        super(nombre, imagen, color, ocupacion, sanidad, fuerza, resistencia);
    }

    public void habilidad1(Jugador contrincante) {
        Ataque habilidad1 = new Cardumen(this, contrincante);
        habilidad1.ejecutar();
    }

    public void habilidad2(Jugador contrincante) {
        Ataque habilidad2 = new SharkAttack(this, contrincante);
        habilidad2.ejecutar();
    }

    public void habilidad3(Jugador contrincante) {
        Ataque habilidad3 = new Pulp(this, contrincante);
        habilidad3.ejecutar();
    }

    @Override
    public boolean buscarAtaque(String[] comando) {
        if (comando == null || comando.length < 4) return false;
        String tipoAtaque = comando[3].toUpperCase();
        switch (tipoAtaque) {
            case "CARDUMEN":
            case "SHARKATTACK":
            case "PULP":
                // No requieren parámetros extra de posicionamiento
                return true;
            default:
                return false;
        }
    }

    @Override
    public void realizarAtaque(Jugador atacado, String[] comando) {
        if (comando == null || comando.length < 4) return;
        String tipoAtaque = comando[3].toUpperCase();
        switch (tipoAtaque) {
            case "CARDUMEN":
                this.habilidad1(atacado);
                break;
            case "SHARKATTACK":
                this.habilidad2(atacado);
                break;
            case "PULP":
                this.habilidad3(atacado);
                break;
        }
    }

    @Override
    public String getArquetipo() {
        return "Undersea Fire"; 
    }
}
