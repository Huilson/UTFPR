package utfpr.aulatcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException {
        //Estabele uma conecção com o servidor local ouvindo na porta 8080
        Socket socket = new Socket("127.0.0.1", 8080);
        
        //Definir o stream de saída de dados (Para conversa)
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        
        //O metodo writeUTF envia uma mensagem/String para o servidor
        //Vale lembrar que essa mensagem e serializada automaticamente em byte
        //pela linguagem JAVA
        saida.writeUTF("oi servidor!");
        
        //Definir o stream de entrada de dados (Para ouvir)
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        String novaMensagem = entrada.readUTF();
        System.out.println(novaMensagem);
        
        //Ao encerrar fecha tudo, seguindo a ordem da pilha
        entrada.close();
        saida.close();
        socket.close();
    }
}