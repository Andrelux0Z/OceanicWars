/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Ataques.ElementosAtaques.Volcan;
import Cliente.Casilla;
import Hero.Hero;
import java.awt.Point;

/**
 *
 * @author kokoju
 */

public class VolcanoRaising extends Ataque {
    // Atributos
    private Point casillaElegida;
    private int radioObtenido;
    
    // Constructor
    public VolcanoRaising(Hero hero, Point casillaElegida) { 
        super(hero);
        this.casillaElegida = casillaElegida;  // Se asigna la casilla
        this.radioObtenido = (1 + rand.nextInt(9)) * (1 + hero.getFuerzaAtaque());  // Se tiene un radio del volcán en función de la fuerza del héroe (fuera del for para mantenerse estable)
    }
    
    // Métodos
    @Override
    public void ejecutar() { 
        // Elección de casilla
        try {
            Casilla casilla = matriz.getMatriz()[this.casillaElegida.x][this.casillaElegida.y];  // Toma la casilla elegida por el usuario
            casilla.recibirGolpe(casilla.getVida());  // La casilla donde sale el volcán es derrotada
            casilla.setObjetoPresente(new Volcan(this.radioObtenido));  // Se crea el objeto del volcán (además de darle el radio)
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue seleccionada como lugar de apararición del volcán del 'Volcano Raising' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
            for (Casilla c : matriz.getCasillasActivas()) {
                if (matriz.IsCasillaEnRadio(casilla.getX(), casilla.getY(), c.getX(), c.getY(), this.radioObtenido)) {  // Si la casilla está dentro de la onda de expansión
                    c.recibirGolpe(c.getVida());
                    c.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por la creación del volcán del 'Volcano Raising' de " + hero.getNombre() + ", recibiendo " + casilla.getVida() + " puntos de daño");
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR: CASILLA FUERA DE LOS LIMITES - Coordenadas inválidas");
        }
    }
}