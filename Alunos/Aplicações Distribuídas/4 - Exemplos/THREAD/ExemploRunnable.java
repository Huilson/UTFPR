package utfpr.aulathread;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExemploRunnable implements Runnable {
    private static CalculadoraThread calc = new CalculadoraThread();
    private String nome;
    private int[] nums;

    public ExemploRunnable(String nome, int[] nums) {
        this.nome = nome;
        this.nums = nums;
        //Posso criar thread dentro do Runnable sem importar nada
        //Pois o Runnable possui herança da classe Thread
        //Quando se passar uma string em um parâmetro de uma Thread
        //será interpretado como se fosse o nome dessa Thread
        new Thread(this, nome).start();
    }

    @Override
    public void run() {
        System.out.println(this.nome + " iniciou!");
        int soma = calc.somaArray(this.nums);
        System.out.println("Resultado da soma para a Thread: "
                +this.nome+", é: "+soma);
        System.out.println("Thread "+this.nome+ " encerrada!");
    }//fim run

}
