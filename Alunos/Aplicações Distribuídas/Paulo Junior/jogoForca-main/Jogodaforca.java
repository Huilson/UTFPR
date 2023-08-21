/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.jogodaforca;
import java.util.Scanner;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author fack2
 */
public class Jogodaforca {

    public static void main(String[] args) {
        //dicionário de palavras
        String[] facil = {"sagaz", "mexer", "nobre", "bola", "pia"};
        String[] medio = {"inerente", "peculiar", "reiterar", "preceder", "alienado"};
        String[] dificil = {"prerrogativa", "extrovertido", "procrastinar", "racionalidade", "ressarcimento"};
        
        
        Scanner scanner = new Scanner(System.in); //criação do objeto do tipo scanner para fazer a leitura do teclado
        
        //Menu de seleção de dificuldade do jogo
        System.out.println("Jogo da Forca");
        System.out.println("Escolha a dificuldade: ");
        System.out.println("1 - Fácil (palavras de até 5 letras)");
        System.out.println("2 - Médio (palavras de 8 letras)");
        System.out.println("3 - Difícil (palavras de 12 ou mais letras)");
        System.out.println("4 - Sair");
        int menu = scanner.nextInt(); //armazena a opção do jogador
        
        
        Random random = new Random(); //criação do objeto random
        String palavraescolhida; //declaração da variável que ira ser escolhida
        int vidas = 7; //numero de tentativas de erro
        
        //String[] palavras = null; //declaração do array para o nível das palavras selecionadas
        switch(menu){ //tratamento da opção selecionada pelo jogador, o qual o array palavras recebe o dicionário de palavras do nivel escolhido
            case 1:
                //variavel que ira receber uma palavra aleatoria do dicionário escolhida aleatória e converte para minuscula 
                palavraescolhida = facil[random.nextInt(facil.length)].toLowerCase();
                break;
            case 2:
                //variavel que ira receber uma palavra aleatoria do dicionário escolhida aleatória e converte para minuscula 
                palavraescolhida = medio[random.nextInt(medio.length)].toLowerCase();
                break;
            case 3:
                //variavel que ira receber uma palavra aleatoria do dicionário escolhida aleatória e converte para minuscula 
                palavraescolhida = dificil[random.nextInt(dificil.length)].toLowerCase();
                break;
            case 4:
                return;
            default:
                System.out.println("Inválido");
                scanner.close();
                return;
        }
        
        //variavel que ira receber uma palavra aleatoria do dicionário escolhida aleatória e converte para minuscula 
        //String palavraescolhida = palavras[random.nextInt(palavras.length)].toLowerCase(); 
        //int vidas = 7; //numero de tentativas do jogo
        
        Set<Character> letrasdigitadas = new HashSet<>(); //Declaração de estrutura de dados de elemento unico para armazenar as letras digitadas
        
        StringBuilder resposta = new StringBuilder(); //Declaração da variável que ira receber as letras corretas
        
        //Adicionando '_' na variável resposta do mesmo tamanho da palavra escolhida  
        for(int i = 0; i< palavraescolhida.length();i++){
            resposta.append("_");
        }
        
        //looping do jogo enquanto a vida for maior que zero e a resposta não estiver completa
        while(vidas >0 && resposta.toString().contains("_")){
            System.out.println("Palavra: " + resposta); //mostra acertadas
            System.out.println("Vidas restantes: " + vidas); //mostras a quantidade de vidas restantes
            System.out.println("Letras digitadas: " + letrasdigitadas); //mostras todas as letras digitadas
            System.out.println("Digite uma letra: "); 
            char letra = Character.toLowerCase(scanner.next().charAt(0)); //armazena a letra digitada em minuscula
            
            if(letrasdigitadas.contains(letra)){ //verifica se a letra digitada já foi digitada antes
                System.out.println("Letra já digitada");
                continue;
            }
            
            letrasdigitadas.add(letra); //adiciona a letra digitada ao histórico de letras
            
            boolean acertou = false; //declaração variável de verificação se acertou a letra
            
            for(int i = 0; i<palavraescolhida.length();i++){ //percorre a palavra escolhida para verificar se contém a letra
                if(palavraescolhida.charAt(i)==letra){ //caso contenha a letra
                    resposta.setCharAt(i, letra); //adiciona a letra na posição da variável resposta
                    acertou = true; //altera o valor da variável de verificação
                }
            }
            
            if(!acertou){ //se a variável de verificação for false, diminui uma vida
                vidas--;
                System.out.println("Letra incorreta");
            }
        }
        
        scanner.close(); //fecha o objeto scanner
        //verificação final do jogo, se a palavra resposta for igual a palavra escolhida o jogador ganhou, caso contrario perdeu
        if(resposta.toString().equals(palavraescolhida)){
            System.out.println("Fim de jogo! Parabéns! Você ganhou! ");
            System.out.println("A palavra era: " + palavraescolhida);
        }else{
            System.out.println("Fim de jogo! Você perdeu!");
            System.out.println("A palavra era: " + palavraescolhida);
        }
    }
}
