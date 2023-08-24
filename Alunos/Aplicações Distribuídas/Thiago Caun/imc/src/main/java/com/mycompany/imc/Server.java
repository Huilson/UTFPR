/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.imc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Thiago_k1
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Servidor esta ouvindo na porta 8080");        
    
        Socket socket = serverSocket.accept();
        
        System.out.println("Um cliente se conectou: "+socket.getInetAddress().getHostAddress());
        
        //definir  um stream de input para o servidor
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
        //usa o metodo readUTF para deserializar a mensagem 
        Pessoa pessoa =  (Pessoa)entrada.readObject();
        System.out.println(pessoa.getNome());
        pessoa.setImc(calculoImc(pessoa));
        //definir um stream de saida para o servidor 
        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
        //String novaMensagem = "oi gostosa, eu sou o servido";
        saida.writeObject(pessoa);
        
        //ao terminar a conversa fecha tudo, seguindo a ordem da pilha
        
        //saida.close();
        entrada.close();
        socket.close();
        serverSocket.close();
    }

    private static double calculoImc(Pessoa pessoa) {
        return (pessoa.getPeso()/Math.pow(pessoa.getAltura(), 2));
    }
    
    
}
