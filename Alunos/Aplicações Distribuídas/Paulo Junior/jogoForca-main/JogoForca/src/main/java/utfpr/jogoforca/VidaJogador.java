package utfpr.jogoforca;


public class VidaJogador {
    
    public int vidasDoJogador(String aux, char letra, int qtdVidas) {
    boolean encontrouLetra = false;

    for (int c = 0; c < aux.length(); c++) {
        if (letra == aux.charAt(c)) {
            encontrouLetra = true;
            break; // Sai do loop assim que encontrar a letra
        }
    }

    if (!encontrouLetra) {
        qtdVidas--;
        System.out.println("-------------------------------------------------");
        System.out.println("A LETRA ESCOLHIDA NÃO EXISTE NA PALAVRA SORTEADA.");
        System.out.println("             VOCÊ PERDEU UMA VIDA.");
        System.out.println("                 VIDA ATUAL: " + qtdVidas);
        System.out.println("-------------------------------------------------");
        if (qtdVidas == 5) {
            System.out.println("  +---+");
            System.out.println("  |   |");
            System.out.println("  O   |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("=========");
        }
        else if (qtdVidas == 4) {
            System.out.println("  +---+");
            System.out.println("  |   |");
            System.out.println("  O   |");
            System.out.println("  |   |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("=========");
        }
        else if (qtdVidas == 3) {
            System.out.println("  +---+");
            System.out.println("  |   |");
            System.out.println("  O   |");
            System.out.println(" /|   |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("=========");
        }
        else if (qtdVidas == 2) {
            System.out.println("  +---+");
            System.out.println("  |   |");
            System.out.println("  O   |");
            System.out.println(" /|\\  |");
            System.out.println("      |");
            System.out.println("      |");
            System.out.println("=========");
        }
        else if (qtdVidas == 1){
            System.out.println("  +---+");
            System.out.println("  |   |");
            System.out.println("  O   |");
            System.out.println(" /|\\  |");
            System.out.println(" /    |");
            System.out.println("      |");
            System.out.println("=========");
        }
        else if (qtdVidas == 0) {
            System.out.println("  +---+");
            System.out.println("  |   |");
            System.out.println("  O   |");
            System.out.println(" /|\\  |");
            System.out.println(" / \\  |");
            System.out.println("      |");
            System.out.println("=========");
        }
        System.out.println("-------------------------------------------------");
    }

    return qtdVidas;
}   
}
