package Servidor;

import sun.applet.Main;
import sun.java2d.loops.GraphicsPrimitive;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    private ServerSocket servidor;
    private ServerSocket arquivo;
    private Scanner read;
    private List<PrintStream> clientes;

    public Server() {
        this.clientes = new ArrayList<PrintStream>();
    }


    private void executa() throws IOException {
        this.servidor = new ServerSocket(5000);
        this.arquivo = new ServerSocket(5001);

        System.out.println("porta esta aberta");
        while (true) {
            Socket cliente = this.servidor.accept();
            Socket Arq = this.arquivo.accept();
            System.out.println("conexão com o cliente " + cliente.getInetAddress().getHostAddress());

            //guarda a saida do cliente na lista;
            PrintStream c = new PrintStream(cliente.getOutputStream());
            this.clientes.add(c);


            // cria uma thread para o cliente e arquivo
            ClienteListener cl = new ClienteListener(cliente.getInputStream(), this);
            Thread t = new Thread(cl);
            t.start();

            Arquivo a = new Arquivo(Arq.getInputStream());
            Thread t2 = new Thread(a);
            t2.start();

        }

        //servidor.close();
       // arquivo.close();
    }
        /// metodo para mandar mensagem para todos os clientes.
    public void sendM(String msg) {
        for (PrintStream cliente : this.clientes) {
            cliente.println(msg);
        }
    }

    public static void main(String[] args) throws IOException{
        Server s =  new Server();
        s.executa();
    }


}







