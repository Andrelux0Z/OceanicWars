/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Cliente.Client;
import Cliente.FrameClient;
import Cliente.Jugador;
import Hero.*;
import Models.AttackPayload;
import Servidor.ThreadServidor;
import javax.swing.SwingUtilities;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author sando
 */
public class CommandApplyAttack extends Command {

    private AttackPayload payload;


    //Contructures
    public CommandApplyAttack(String[] args) {
        super(CommandType.APPLYATTACK, args);
    }

    public CommandApplyAttack(AttackPayload payload) {
        super(CommandType.APPLYATTACK, buildParamsFromPayload(payload));
        this.payload = payload;
    }

    private static String[] buildParamsFromPayload(AttackPayload payload) {
        String[] extras = payload.getExtras();
        String[] params = new String[4 + (extras == null ? 0 : extras.length)];
        params[0] = "APPLYATTACK";
        params[1] = payload.getTargetName();
        params[2] = payload.getHeroType();
        params[3] = payload.getAttackType();

        if (extras != null) 
            for (int i = 0; i < extras.length; i++) 
                params[4 + i] = extras[i];
        return params;
    }



    public AttackPayload getPayload() { 
        return this.payload; 
    }

    @Override
    public void processForServer(ThreadServidor threadServidor) {
        this.setIsBroadcast(false);
    }

    
    @Override
    public void processInClient(Client clienteAtacado) { // Cliente que recibe el ataque
        // Forma esperada de parámetros:
        // [0] = "ATTACK", [1] = contrincante, [2] = Heroe, [3] = TipoAtaque, [4].. = extras

        String[] params = this.getParameters();

        // Si se recibió un payload serializado, reconstruir un arreglo de parámetros a partir de él
        if (this.payload != null) {
            String[] extras = this.payload.getExtras();
            params = new String[4 + (extras == null ? 0 : extras.length)]; //Parametos + la cantidad de extras
            params[0] = "ATTACK";
            params[1] = this.payload.getTargetName();
            params[2] = this.payload.getHeroType();
            params[3] = this.payload.getAttackType();
            if (extras != null) 
                for (int i = 0; i < extras.length; i++) 
                    params[4 + i] = extras[i];
        }

        Jugador atacado = clienteAtacado.getJugador();

        // Reconstruir un Hero atacante a partir del HeroPackage si está presente
        Hero atacanteHero = null;
        if (this.payload != null && this.payload.getHeroPackage() != null) {
            atacanteHero = HeroFactory.createFromPackage(this.payload.getHeroPackage());
        }

        // Verificaciones defensivas: asegurarse de que el jugador atacado y el atacante existan
        if (atacado == null) {
            String msg = "CommandApplyAttack: jugador atacado no inicializado";
            clienteAtacado.getRefFrame().writeMessage(msg);
            // Notificar al atacante vía mensaje privado
            if (this.payload != null && this.payload.getAttackerName() != null) {
                String[] args = new String[]{"ATTACK_RESULT", this.payload.getAttackerName(), msg};
                try {
                    clienteAtacado.objectSender.writeObject(Models.CommandFactory.getCommand(args));
                } catch (Exception e) {
                    // ignore send failures
                }
            }
            return;
        }

        if (atacanteHero == null) {
            String msg = "CommandApplyAttack: no se pudo reconstruir héroe atacante desde el paquete";
            clienteAtacado.getRefFrame().writeMessage(msg);
            if (this.payload != null && this.payload.getAttackerName() != null) {
                String[] args = new String[]{"ATTACK_RESULT", this.payload.getAttackerName(), msg};
                try {
                    clienteAtacado.objectSender.writeObject(CommandFactory.getCommand(args));
                } catch (Exception e) {
                }
            }
            return;
        }

        // Delegar la ejecución del ataque a la lógica del héroe (que crea el Ataque y llama ejecutar())
        try {
            atacanteHero.realizarAtaque(atacado, params);
        } catch (Exception ex) {
            clienteAtacado.getRefFrame().writeMessage("CommandApplyAttack: error ejecutando ataque: " + ex.getMessage());
        }

        // Actualizar UI en EDT
        SwingUtilities.invokeLater(() -> {
            clienteAtacado.getRefFrame().actualizarPnlMatriz();
            });
        String okMsg = "Se aplicó ataque '" + params[3] + "' de '" + params[2] + "'.";
        clienteAtacado.getRefFrame().writeMessage(okMsg);

        // Notificar al atacante que el ataque se aplicó
        if (this.payload != null && this.payload.getAttackerName() != null) {
            String[] args = new String[]{"ATTACK_RESULT", this.payload.getAttackerName(), okMsg};
            try {
                clienteAtacado.objectSender.writeObject(CommandFactory.getCommand(args));
            } catch (Exception e) {
            }
        }
    }

}
