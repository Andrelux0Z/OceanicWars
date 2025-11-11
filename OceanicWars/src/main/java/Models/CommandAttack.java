/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Cliente.Client;
import Cliente.Jugador;
import Hero.Hero;
import Hero.HeroPackage;
import Servidor.ThreadServidor;
import java.io.IOException;

/**
 *
 * @author diego
 */
public class CommandAttack extends Command{

    public CommandAttack(String[] args) { //ATTACK Andres 5 7
        super(CommandType.ATTACK, args);
    }
    
    @Override
    public void processForServer(ThreadServidor threadServidor) {
        this.setIsBroadcast(false);
    }
            
    @Override
    public void processInClient(Client cliente) { //Cliente     
        
        Command sendComando;
        boolean flag = false;       //Indica si se detecta un error
        Jugador atacante = cliente.getJugador();

        // Validar que el jugador local esté inicializado
        if (atacante == null) {
            if (cliente.getRefFrame() != null) cliente.getRefFrame().writeMessage("Imposible realizar ataque: jugador local no inicializado");
            return;
        }

        String[] params = this.getParameters();
        if (params == null || params.length < 4) {
            cliente.getRefFrame().writeMessage("Parámetros insuficientes para ATTACK");
            return;
        }

        Hero heroeAtacante = atacante.buscarHeroe(params[2]);
        // Ver si el heroe existe
        if (heroeAtacante == null) {
            cliente.getRefFrame().writeMessage("El heroe escrito no existe");
            flag = true;
            
        // Ver si el ataque y parametros extra son correctos
        } else if (!heroeAtacante.buscarAtaque(params)) {   // Ya valida parametros extra
            cliente.getRefFrame().writeMessage("El ataque escrito no existe");
            flag = true;
        }

        if (flag) return;

        // Construir payload con HeroPackage
        String attackerName = cliente.name;
        String targetName = params[1];
        String heroType = params[2];
        String attackType = params[3];
        String[] extras = new String[params.length - 4];
        for (int i = 4; i < params.length; i++) extras[i - 4] = params[i];

        HeroPackage hp = null;
        if (atacante != null) hp = atacante.buildHeroPackage(params[2]);

        AttackPayload payload = new AttackPayload(attackerName, targetName, heroType, attackType, extras, hp);
        sendComando = new CommandApplyAttack(payload);

        try {
            cliente.objectSender.writeObject(sendComando);
        } catch (IOException ex) {
            System.getLogger(CommandAttack.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }



}
