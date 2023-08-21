
package utfpr.jogoforca;


public class Caracteres {
    //Recebe a palavra sorteada e retorna  uma string de resposta contendo "_" do tamanho da palavra
    public StringBuilder conversorCaracteres(String palavra){ 
        char[] tracos = palavra.toCharArray(); //Converter para array de char
        StringBuilder palavraEscondida = new StringBuilder();
        System.out.println("---------------------------------------");
        System.out.println("     A PALAVRA CONTÉM " + palavra.length() + " CARACTERES"); //Mostra o tamanho da palavra
        System.out.println("---------------------------------------");
        for (char c : tracos) {
            System.out.print("_ "); //imprimi espaçado os "_"
            palavraEscondida.append("_"); //Adiciona "_" na variável de resposta
        }
        System.out.println("");
        return palavraEscondida; //Retorna a variável de resposta
    }
    
    //Recebe a palavra sorteada, a variável de resposta com "_", a letra digitada
    //Verifica se a palavra sorteada contém a letra digitada e atualiza na variável resposta caso contenha
    public StringBuilder acertoDeCaracteres(String palavra, StringBuilder palavraEscondida, char d){
        char[] espacos = palavra.toCharArray(); //converte para um array de char

        //percorre a palavra para verificar se contém a letra digitada
            for (int c = 0; c < palavra.length(); c++) { 
                    //Caso encontre a letra, atualiza a a variável de resposta com a letra na posição correta
                if (d == espacos[c]) {
                    palavraEscondida.setCharAt(c, espacos [c]);
                }
            }
            return palavraEscondida; //retorna a resposta
        }
}

