package utfpr.aulaupd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private DatagramSocket socket;
    private InetAddress ip;
    private byte[] buffer;

    public Client(DatagramSocket socket, InetAddress ip) {
        this.socket = socket;
        this.ip = ip;
    }
    
    public void enviarReceber() throws IOException{
        Scanner scan = new Scanner(System.in);
        while(true){
            String msg = scan.nextLine();
            
            //SERIALIZANDO a mensagem
            buffer = msg.getBytes();
            
            /*Lembrando que quando criamos um pacote de datagramas não estamos 
            estabelecendo uma conexão, mas sim, estamos enviando um pacote para alguém
            que possa estar ouvindo (ou não)*/
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, ip, 8082);
            
            //enviamos um pacote com o cabeçalho escrito com as informações do servidor
            socket.send(datagram);
            
            /*fica na espera de uma resposta*/
            socket.receive(datagram);
            
            /*DESERIALIZANDO a mensagem enviada pelo servidor*/
            String msgNova = new String(datagram.getData(),0, datagram.getLength());
            System.out.println("O servidor recebeu essa mensagem: "+msgNova);
        }
    }
    public static void main (String[] agrs) throws IOException, SocketException, UnknownHostException{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        Client client = new Client (socket, ip);
        
        System.out.println("Iniciando conversa com o servidor...");
        client.enviarReceber();
    }
}