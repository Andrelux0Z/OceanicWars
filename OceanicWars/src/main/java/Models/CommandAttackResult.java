    package Models;

import Cliente.Client;
import Servidor.ThreadServidor;

/**
 * Comando que contiene el resultado (deltas) de un ataque calculado por el servidor.
 */
public class CommandAttackResult extends Command {

    public CommandAttackResult(String[] args) {
        super(CommandType.ATTACK_RESULT, args);
    }

    @Override
    public void processForServer(ThreadServidor threadServidor) {
        this.setIsBroadcast(true);
    }

    @Override
    public void processInClient(Client client) {
        String[] params = this.getParameters();
        String msg = "";

        if (params == null) 
            return;

        if (params.length > 2) {    
            msg = params[2];
            
        } else if (params.length > 1) 
            msg = params[1];
        client.getRefFrame().writeMessage("[AttackResult] " + msg);
    }
}


