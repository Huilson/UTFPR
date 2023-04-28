import br.edu.utfpr.model.Pessoa;

import java.io.*;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("127.0.0.1", 5555);

        Pessoa pessoa = new Pessoa(23,"Douglas", 80.0,1.73,39);

        //Definir stream saida de dados
        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());

        saida.writeObject(pessoa);

        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

        Pessoa pessoa1 = (Pessoa) entrada.readObject();

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", symbols);

        System.out.println("Pessoa que foi enviada para o servidor, agora com o IMC:");
        System.out.println(pessoa1.getNome()+" - Imc = "+ df.format(pessoa1.getIMC()));

        entrada.close();
        saida.close();
        socket.close();
    }

}
