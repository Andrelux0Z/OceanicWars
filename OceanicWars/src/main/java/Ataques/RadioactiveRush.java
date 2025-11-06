/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Ataques.ElementosAtaques.BasuraRadioactiva;
import Cliente.Casilla;
import Hero.Hero;

/**
 *
 * @author kokoju
 */

public class RadioactiveRush extends Ataque {    
    // Constructor
    public RadioactiveRush(Hero hero) {
        super(hero);
    }
    
    // Métodos
    // TODO: VER SI LOS TICKS DE LA BASURA PUEDEN HACERSE VISUALMENTE, EN LUGAR DE SOLO APLICARSE AL INSTANTE
    
    @Override
    public void ejecutar() {
        int tiempoActiva = (1 + rand.nextInt(10)) * (1 + hero.getFuerzaAtaque());  // El tiempo que la basura esté activa también depende del poder del usuario (debe estar fuera del for para que la fuerza del héroe se siga aplicando)
        for (Casilla  c : matriz.getCasillasActivas()) {  // Se revisan las casillas, y se ejecuta una acción si tienen basura
            if (c.getObjetoPresente() instanceof BasuraRadioactiva) {
                BasuraRadioactiva b = (BasuraRadioactiva) c.getObjetoPresente();
                if (b.getIsToxico()) {
                    c.recibirGolpe(10 * tiempoActiva);  // La casilla recibe los 10 ticks de daño, multiplicados por el tiempo que la basura está activa
                }
            }
        }               
    }
}