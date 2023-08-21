package utfpr.jogoforca;

import java.util.HashSet;
import java.util.Set;


public class JogoForca {
  

    public static void main(String[] args) {
        //Instancias
        Palavras palavra = new Palavras();
        Dificuldade dificuldade = new Dificuldade();
        LetrasJogador letraEscolhida = new LetrasJogador();
        Caracteres caracteres = new Caracteres();
        StringBuilder palavraEscolhida = new StringBuilder();
        Set<Character> letrasDigitadas = new HashSet<>();
        VidaJogador vidaJogador = new VidaJogador();
        
        int escolhaJogador;
        int qtdVidas = 6;
        String aux;
        char letra;
        
        //Seleção de dificuldade do jogo
        escolhaJogador = dificuldade.escolhaDificuldade();
        
        System.out.println("---------------------------------------");
        System.out.println("           INICIANDO O JOGO");
        System.out.println("---------------------------------------");
        
        //Sortea a palavra de acordo com a dificuldade escolhida pelo jogado e transfoma em minúscula
        aux = palavra.sorteador(escolhaJogador).toLowerCase();
        
        //System.out.println(aux);

        //prepara a variável de resposta com "_" do tamanho da palavra escolhida
        palavraEscolhida = caracteres.conversorCaracteres(aux);
        
        boolean palavraDescoberta = false;
        
        //Looping principal enquanto o jogo não acabar       
        while (palavraDescoberta == false){
            //recebe a letra digitada pelo jogador e converte para minúscula
            letra = Character.toLowerCase(letraEscolhida.LetraEscolhida());
            //verifica se a letra digitada já foi digitada antes
            if(letrasDigitadas.contains(letra)){ 
                System.out.println("Letra já digitada");
                continue;
            }
            //adiciona a letra digitada ao histórico de letras
            letrasDigitadas.add(letra); 
            //verifica se a palavra contém a letra digitada
            palavraEscolhida = caracteres.acertoDeCaracteres(aux, palavraEscolhida, letra);
            
            qtdVidas = vidaJogador.vidasDoJogador(aux, letra, qtdVidas);
            //Se acabar as vidas, o jogo termina
            if (qtdVidas == 0){
                System.out.println("QUE PENA, VOCÊ PERDEU O JOGO");
                //System.out.println("-------------------------------------------------");
                //System.out.println("GOSTARIA DE JOGAR NOVAMENTE?");
                
                break;
            }
            //Mostra a situação da respota e as letras digitadas
            System.out.println("-------------------------------------------------");
            System.out.println(palavraEscolhida);
            System.out.println("-------------------------------------------------");
            System.out.println("LETRAS DIGITADAS: " + letrasDigitadas);
          
            //Se a variável de resposta for igual a palavra escolhida, o jogo termina
            if (palavraEscolhida.toString().equals(aux)){
                System.out.println("-------------------------------------------------");
                System.out.println("PARABÉNS VOCÊ VENCEU O JOGO!");
                break;
            }
        }
    }
}
