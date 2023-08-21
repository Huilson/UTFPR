package utfpr.jogoforca;

import java.util.Random;




public class Palavras {
    //Biblioteca de palavras do jogo, separado por dificuldade
    Random gerador = new Random();
    
    public String sorteador(int c){
        if (c == 1){
            //Array de palavras
            String palavrasFaceis[] = {"baixo", "caixa", "disco", "foice", "harpa",
            "manto", "ninho", "papel", "raios", "topos",
            "uvas", "vazio", "xadrez", "zorra", "abrir",
            "balas", "cedro", "dados", "fazer", "gemas"};
            //Seleciona uma palavra aleatória da biblioteca
            String escolha = palavrasFaceis[gerador.nextInt(0, palavrasFaceis.length)]; 
            //retorna a palavra
            return escolha;
        }
        else if (c == 2){
            //Array de palavras
            String palavrasMedias[] = {"abafador", "baioneta", "carretel", "fantasia", "molinete",
            "cachorro", "dinheiro", "elefante", "florista", "girassol",
            "história", "jogador", "lambari", "montanha", "notebook",
            "orangotango", "paralelo", "qualquer", "receita", "sofa"};
            //Seleciona uma palavra aleatória da biblioteca
            String escolha = palavrasMedias[gerador.nextInt(0, palavrasMedias.length)];
            //retorna a palavra
            return escolha;   
        }
        else {
            //Array de palavras
            String palavrasDificil[] = {"acompanhar", "desenvolvimento", "correspondencia", "responsabilidade", "revolucionario",
"satisfatoriamente", "simplicidade", "transformacao", "extraordinariamente", "inconveniente",
"intransigente", "desestabilizacao", "interdependencia", "particularidade", "congratulatorio",
"indisponibilidade", "desenvolvimentista", "procrastinacao", "contraditorio", "inquisitivo"};
            //Seleciona uma palavra aleatória da biblioteca
            String escolha = palavrasDificil[gerador.nextInt(0, palavrasDificil.length)];
            //retorna a palavra
            return escolha;
        }
    }
}
