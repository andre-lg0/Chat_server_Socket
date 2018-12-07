package Servidor;

import java.io.InputStream;
import java.util.Scanner;

public class ClienteListener  implements Runnable{

    private InputStream cliente;
    private Server server;

    public ClienteListener(InputStream cliente , Server server) {
        this.cliente = cliente;
        this.server = server;
    }


    @Override
    public void run() {
        Scanner in  = new Scanner(this.cliente);
        while(in.hasNextLine()){
            server.sendM(in.nextLine());
        }
    }
}
