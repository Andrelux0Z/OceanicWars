package Models;

import Cliente.Client;
import Servidor.ThreadServidor;

/**
 * sando
 */
public class CommandResult extends Command {

    public CommandResult(String[] args) {
        super(CommandType.RESULT, args);
        this.consumesTurn = false;
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
        client.getRefFrame().writeMessage(msg);
    }
}


