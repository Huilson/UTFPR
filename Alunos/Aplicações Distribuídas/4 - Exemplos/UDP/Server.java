package utfpr.aulaupd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    private DatagramSocket socket;
    /*Precisa de um buffer de byte, ou seja, um array de byte*/
    /*Esse buffer ira guardar todas as informações enviadas pela rede*/
    private byte[] buffer = new byte[256];

    public Server(DatagramSocket socket) {
        this.socket = socket;
    }
    
    public void receberEnviar()throws IOException{
        while(true){
            /*Um Pacote de Datramas é usado para enviar a mensagem não orientada a conexão
            Nosso buffer é responsável por carregar a mensagem, então o tamanho da mensagem
            é o tamanho do buffer. Ou seja, nosso datagrama envia pela rede um array de byte*/
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
            
            //Cada pacote enviado ou recebido por um DatagramSocket é endereçado
            //e transportado individualmente
            socket.receive(datagram);
            
            //Todo datragram possui um cabeçalho com IP e PORTA e outra coisitas mais
            InetAddress ip = datagram.getAddress();
            int port = datagram.getPort();
            
            //Para construir a mensagem enviada pelo cliente precisamos DESERIALIZAR 
            //na mão o array de byte. Você pode criar um String apartir de um array de byte, mas
            //precisamos passar por parametro em que posição ela vai começar a deserializar e
            //que posição ela deve parar (ou tamanho do array de char)
            String msg = new String(datagram.getData(),0, datagram.getLength());
            System.out.println("Mensagem recebida: "+msg);
            
            /*Para retorna a mensagem reescrevemos nosso datagrama com as informações
            do cliente, o "antigo" datagrama será coletado pelo Gargabe Collector da JVM
            e a informação possivelmente perdida*/
            datagram = new DatagramPacket(buffer, buffer.length, ip, port);
            
            /*Como não existe conexão entre clientes e servidor fica por conta do
            socket enviar e organizar as mensagens*/
            socket.send(datagram);
        }
    }
    public static void main(String[] args) throws IOException, SocketException{
        DatagramSocket socket = new DatagramSocket(8082);
        Server server = new Server (socket);
        
        System.out.println("Subindo o servidor...");
        server.receberEnviar();
    }
}
