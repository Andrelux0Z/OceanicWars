/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Cliente.Client;
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
        Hero heroe = client.getJugador().buscarHeroe(heroeName);
                
        if(heroe == null) {
            client.getRefFrame().writeMessage("Heroe seleccionado no existe: " + this.getParameters()[1]);
            return;
        }
        
        //Buscar tipo de BOOST y si no mensaje de error
        String tipoBoost = this.getParameters()[2].toUpperCase();
        
        switch (tipoBoost) {
            case "HEAL":
                heroe.Heal();
                break;
            case "PROTECT":
                heroe.Protect();
                break;
            case "STRENGHTEN":
                heroe.Strenghten();
                break;
            default:
                client.getRefFrame().writeMessage("Mejora seleccionada no existe: " + this.getParameters()[2]);
                return;
        }
        client.getRefFrame().writeMessage("El jugador " + client.name + " ha usado " + this.getParameters()[1] + "para ayudar a su civilizacion con " + this.getParameters()[2]);
    }
    
}
