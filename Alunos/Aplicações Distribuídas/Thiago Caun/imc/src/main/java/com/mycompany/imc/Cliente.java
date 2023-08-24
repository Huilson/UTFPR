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
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago_k1
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        Socket socket = new Socket("127.0.0.1",8080); 
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(JOptionPane.showInputDialog("Digite seu nome:"));
        pessoa.setAltura(Double.parseDouble(JOptionPane.showInputDialog("Digite sua altura:")));
        pessoa.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Digite sua idade:")));
        pessoa.setPeso(Double.parseDouble(JOptionPane.showInputDialog("Digite seu peso:")));
        
        
        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());

        saida.writeObject(pessoa);
        

      ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
      
       pessoa = (Pessoa)entrada.readObject();
        System.out.println("imc = "+ pessoa.getImc());
        if (pessoa.getImc() < 18.5){
            System.out.println("esta abaixo do pesso");
        }
        else if(pessoa.getImc()>= 18.5 && pessoa.getImc()< 25){
            System.out.println("Peso normal");
        }
        else if(pessoa.getImc()>= 25 && pessoa.getImc()< 30){
            System.out.println("Acima do peso");
        }
        else if(pessoa.getImc()>= 30 && pessoa.getImc()< 35){
            System.out.println("Obesidade classe I");
        }
        else if(pessoa.getImc()>= 35 && pessoa.getImc()< 40){
            System.out.println("Obesidade classe II");
        }
        else {
            System.out.println("Obesidade classe III");
        }
        
        entrada.close();
        saida.close();
        socket.close();
    }
    
}
