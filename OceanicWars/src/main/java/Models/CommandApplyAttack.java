/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Cliente.Client;
import Cliente.Jugador;
import Hero.Hero;
import Hero.HeroFactory;
import Servidor.ThreadServidor;

/**
 *
 * @author sando
 */
public class CommandApplyAttack extends Command {

    public CommandApplyAttack(String[] args) {    
        super(CommandType.APPLYATTACK, args);
    }
    
    @Override
    public void processForServer(ThreadServidor threadServidor) {
        this.setIsBroadcast(false);
    }
    
        public void processInClient(Client clienteAtacado) { //Cliente que recibe el ataque
        //Forma: ATTACK <contrincante> <Heroe> <TipoAtaque> <x> <y>
        
        Jugador atacado = clienteAtacado.getJugador();
        
        Hero heroeProvisional = HeroFactory.getHero(this.getParameters()[2]);
        
        heroeProvisional.realizarAtaque(atacado, this.getParameters());
        
        
        
             
        
        
        }
}
