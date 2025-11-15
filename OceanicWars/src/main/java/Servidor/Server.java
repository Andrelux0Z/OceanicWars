/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Models.Command;
import Models.CommandFactory;
import Models.CommandType;
import Models.CommandApplyAttack;
import Models.AttackPayload;
import Hero.HeroFactory;
import Hero.Hero;
import static Models.CommandType.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class Server {
    private final int PORT = 35500;
    private final int maxConections = 4;
    private ServerSocket serverSocket;
    private ArrayList<ThreadServidor> connectedClients; // arreglo de hilos por cada cliente conectado
    //referencia a la pantalla
    FrameServer refFrame;
    private ThreadConnections connectionsThread;

    public Server(FrameServer refFrame) {
        connectedClients = new ArrayList<ThreadServidor>();
        this.refFrame = refFrame;
        this.init();
        connectionsThread = new ThreadConnections(this);
        connectionsThread.start();
    }
    
    //método que inicializa el server
    private void init(){
        try {
            serverSocket = new ServerSocket(PORT);
            refFrame.writeMessage("Server running!!!");
        } catch (IOException ex) {
            refFrame.writeMessage("Error: " + ex.getMessage());
        }
    }
    
    void executeCommand(Command comando, ThreadServidor origin) {
        // Si es un ApplyyAttack, validar el payload antes de reenviar
        try {
            if (comando.getType() == APPLYATTACK) {

                if (comando instanceof CommandApplyAttack) {

                    CommandApplyAttack ca = (CommandApplyAttack) comando;
                    AttackPayload payload = ca.getPayload();

                    if (payload != null && payload.getHeroPackage() != null) {
                        Hero prueba = HeroFactory.createFromPackage(payload.getHeroPackage());
                        if (prueba == null) {
                            
                            // Noticar al que envia el ataque que hubo un error utilizando ATTACK_RESULT
                            String msg = "Server: invalid HeroPackage in attack from '" + payload.getAttackerName() + "'";
                            String[] args = new String[]{"ATTACK_RESULT", origin.name, msg};
                            
                            try {
                                origin.objectSender.writeObject(CommandFactory.getCommand(args));
                            } catch (IOException e) {
                                // ignore
                            }
                            return;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            // validation failure; notificar al server
            try {
                String[] args = new String[]{"ATTACK_RESULT", origin.name, "Server: error validating attack payload"};
                origin.objectSender.writeObject(CommandFactory.getCommand(args));
            } catch (IOException e) {
            }
            return;
        }
        

        // Reenviar el comando según su tipo de difusión -> PERDON :(
        //Si es broadcast true
        if (comando.getIsBroadcast())
            this.broadcast(comando);
        
        //Si es broadcast false y se encuentra al jugador receptor
        else if (buscarJugador(comando.getParameters()[1])) {
            if(comando.getType() == PRIVATE_MESSAGE || comando.getType() == APPLYATTACK)
                this.sendPrivate(comando);
            else
                processPrivate(comando,origin);
            
        //Si no se encuentra receptor
        } else {
            String[] args = new String[]{"ATTACK_RESULT", "Server: Jugador objetivo no encontrado"};
            try {
                origin.objectSender.writeObject(CommandFactory.getCommand(args));
            } catch (IOException ex) {
                System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
    }

    
    public void broadcast(Command comando){
        for (ThreadServidor client : connectedClients) {
            try {
                client.objectSender.writeObject(comando);
            } catch (IOException ex) {
                
            }
        }

    }
    
    
    public void processPrivate(Command comando,ThreadServidor own){
        if (comando.getParameters().length <= 1)
            return;
        
                try {
                    own.objectSender.writeObject(comando);
                    
                } catch (IOException ex) {
                
                }
    }
    
    public void sendPrivate(Command comando){
        //asumo que el nombre del cliente viene en la posición 1 .  private_message Andres "Hola"
        if (comando.getParameters().length <= 1)
            return;
        
        String searchName =  comando.getParameters()[1].toUpperCase(); 
        
        for (ThreadServidor client : connectedClients) {
            if (client.name.toUpperCase().equals(searchName)){
                try {
                //simulo enviar solo al primero, pero debe buscarse por nombre
                    client.objectSender.writeObject(comando);
                    break;
                } catch (IOException ex) {
                
                }
            }
        }
    }
    

    public boolean buscarJugador(String searchName) {
        
        for (ThreadServidor client : connectedClients) {
            if (client.name.equalsIgnoreCase(searchName))
                return true;
                    
        }
        return false;
    }
    
    public void showAllNames(){
        this.refFrame.writeMessage("Usuarios conectados");
        for (ThreadServidor client : connectedClients) {
            this.refFrame.writeMessage(client.name);
        }
    }

    public int getMaxConections() {
        return maxConections;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ArrayList<ThreadServidor> getConnectedClients() {
        return connectedClients;
    }

    public FrameServer getRefFrame() {
        return refFrame;
    }

    
    
    
    
    
    

    
    
}
