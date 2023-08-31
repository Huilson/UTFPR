package utfpr.aulathread;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExemploThread extends Thread {
    String nome;
    int tempo;

    public ExemploThread(String nome, int tempo) {
        this.nome = nome;
        this.tempo = tempo;
        start();
    }

    @Override
    public void run() {
        try{
            for(int i = 0; i < 10; i++){
                System.out.println("A "+this.nome+" esta sendo executada!");
                Thread.sleep(this.tempo);
            }
        }catch(InterruptedException e){
            Logger.getLogger(ExemploThread.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("A "+this.nome+" termimnou a execução!");
    }
}
