package utfpr.aulatcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws IOException {
        //Definir o serverSocket (porta de conexao)
        ServerSocket serversocket = new ServerSocket(8080);
        System.out.println("O servidor esta ouvindo na porta 8080");

        //Abrir a comunicação (ou seja, ficar ouvindo)
        Socket socket = serversocket.accept();

        //Mostrar o IP do cliente que vai estar conectado
        System.out.println("Um cliente se conectou: " + socket.getInetAddress().getHostAddress());

        //Definir um stream de entrada para o servidor (Para ouvir)
        DataInputStream entrada = new DataInputStream(socket.getInputStream());

        //Quando recebo a mensagem ela está em bytes, para deserializar a mensagem
        //ou uma string usamos o metodo readUTF()
        String mensagem = entrada.readUTF();
        System.out.println(mensagem);

        //Definir um stream de saida para o servidor (Para conversar)
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        String novaMensagem = "Oi cliente, eu sou o servidor!";
        saida.writeUTF(novaMensagem);

        //Ao terminar a conversa fecha tudo, seguindo a ordem da pilha
        saida.close();
        entrada.close();
        socket.close();
        serversocket.close();

    }

}
