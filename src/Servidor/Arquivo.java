package Servidor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Arquivo implements Runnable {

    private InputStream arquivo;

    public Arquivo(InputStream arquivo) {
        this.arquivo = arquivo;
    }



    @Override
    public void run() {
        try{
          byte[]buffer = new byte[1024];
          int bytes;
          //criar local para guarda o arquivo
            FileOutputStream FileOut = new FileOutputStream(new File("teste.txt"));
            //System.out.println("Arquivo salvo");
            while((bytes = arquivo.read(buffer))!= -1){
                FileOut.write(buffer,0,bytes);
                FileOut.flush();

            }

        }
        catch (Exception e){
            System.out.println("Erro no arquivo " + e);
        }
    }

}
