/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ataques;

import Cliente.Matriz;
import Cliente.Casilla;
import Hero.Hero;
import java.util.Random;

/**
 *
 * @author kokoju
 */
public class ThunderRain extends Ataque {
    
    public ThunderRain(Hero hero) {
        super(hero);
        this.rand = new Random();
        this.matriz = Cliente.Matriz;
    }
    
    // ATAQUES QUE PUEDE HACER THUNDERS UNDER THE SEA
    @Override
    public void ejecutar() {  // Reciben un héroe para ver sus estadísticas
        for (int i = 0; i < 100 ; i++) {  // For para hacer 100 rayos
            
            // Elección de potencia
            int golpeRayo = (10 + rand.nextInt(11)) * (1 + hero.getFuerzaAtaque());  // Calcula la potencia del golpe de cada rayo (cada uno hace entre 10 y 20 (porque nextInt va hasta n-1) * fuerza del héroe
            
            // Elección de casilla
            Casilla casilla = matriz.getCasillasActivas().get(rand.nextInt(matriz.getCasillasActivas().size()));  // Toma una casilla aleatoria de las que están presentes en el arreglo
            casilla.recibirGolpe(golpeRayo);  // Golpea a la casilla
            casilla.getBitacora().add("La casilla (" + casilla.getX() + ", " + casilla.getY() + ") fue golpeada por un rayo de 'Thunder Rain', recibiendo " + golpeRayo + " puntos de daño");  // Mensaje agregado a la bitácora de la casilla
        }
    }
}
