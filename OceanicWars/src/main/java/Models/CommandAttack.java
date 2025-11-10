/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Cliente.Client;
import Cliente.Jugador;
import Hero.Hero;
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
        //Forma: ATTACK <contrincante> <Heroe> <TipoAtaque> <x> <y>
        //Crear una forma para realizar un ataque en la matriz del cliente enemigo, el cliente recibido es el enemigo, por lo que se deberia buscar la forma de implementar los ataque
        //de los heres dependiendo de cual sea en esta funcion, proponer soluciones tanto en esta funcion, como modificaciones en la logica de otras clases (como una clase similar a commandFactory
        //pero para el heroe typeado y el ataque typeado)
        
        
        
        Jugador atacante = cliente.getJugador();
        Hero heroeAtacante = atacante.buscarHeroe(this.getParameters()[2]);       
        if(!heroeAtacante.buscarAtaque(this.getParameters()))
            cliente.getRefFrame().writeMessage("No se ha podido realizar el ataque, comando incorrecto"); //En caso de que algo falle al realizar el ataque
        else {
            Command sendComando = new CommandApplyAttack(this.getParameters());
            try {
                cliente.objectSender.writeObject(sendComando);
            } catch (IOException ex) {
                System.getLogger(CommandAttack.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
    }
   
    
}
