
package utfpr.jogoforca;

import java.util.Scanner;


public class LetrasJogador {
    
    Scanner ler = new Scanner(System.in);
    //Obtem a letra digitada pelo jogador    
    public char LetraEscolhida(){
        
        System.out.println("\nINFORME UMA LETRA: ");
        String letraUsuario = ler.next();
        //verifica se o jogador digitou mais de uma letra
        while (letraUsuario.length() > 1){
            System.out.println("VOCÊ DIGITOU MAIS DE UMA LETRA, POR GENTILEZA DIGITE SOMENTE UMA: ");
            letraUsuario = ler.next();
        }
        char primeiraLetra = letraUsuario.charAt(0);
        //retorna a letra digitada pelo jogador
        return primeiraLetra;
    }
    
}
