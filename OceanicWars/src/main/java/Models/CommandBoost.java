/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Cliente.Client;
import Cliente.Jugador;
import Hero.Hero;
import Servidor.ThreadServidor;
import java.util.ArrayList;

/**
 *
 * @author sando
 */
public class CommandBoost extends Command {
    
    public CommandBoost(String[] args) {
        super(CommandType.MESSAGE, args);
    }

    @Override
    public void processForServer(ThreadServidor threadServidor) {
        this.setIsBroadcast(true);
        
    }
    
    @Override
    public void processInClient(Client client) { //Cliente propio
        //Forma: BOOST <Heroe> <HEAL/PROTECT/STRENGHTEN>
        
        //Buscar heroe, si no existe, mensaje de error
        String heroeName = this.getParameters()[1].toUpperCase();
        String tipoBoost = this.getParameters()[2].toUpperCase();
        Hero heroe = client.getJugador().buscarHeroe(heroeName);
        boolean flag = false;
                
        if(heroe == null) {
            client.getRefFrame().writeMessage("Heroe seleccionado no existe: " + heroeName);
            flag = true;
        } 
        //Activa el boosteo y regresa el true, si no lo encuentra obviamente no activa nada y regresa false
        else if(!heroe.activarBoost(tipoBoost)) {  
                client.getRefFrame().writeMessage("Mejora seleccionada no existe: " + tipoBoost);
        }  
        
        if(flag)
            client.getRefFrame().writeMessage("El jugador " + client.name + " ha usado " + this.getParameters()[1] + "para ayudar a su civilizacion con " + this.getParameters()[2]);
        }
    }

