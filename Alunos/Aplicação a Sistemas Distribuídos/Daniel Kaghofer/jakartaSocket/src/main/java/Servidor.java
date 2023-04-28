import br.edu.utfpr.model.Pessoa;
import br.edu.utfpr.model.PessoaDao;
import database.ConnectionFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Iniciar servidor
        ServerSocket servidor = new ServerSocket(5555);
        System.out.println("Servidor rodando na porta 5555");

        //Aguardando Solicitacoes de clientes
        Socket socket = servidor.accept();

        System.out.println("Cliente: "+socket.getInetAddress().getHostAddress() +" se conectou");

        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());

        Pessoa pessoa = (Pessoa) entrada.readObject();

        PessoaDao pessoaDao = new PessoaDao(ConnectionFactory.getEntityManager());
        pessoaDao.Salvar(pessoa);

        System.out.println("Pessoa recebida do cliente: " + pessoa.getNome() + ", " + pessoa.getIdade() + " anos");

        Pessoa pessoa1;

        pessoa1 = pessoaDao.Ler(pessoa);

        System.out.println(pessoa1.getNome());

        saida.writeObject(pessoa1);

        entrada.close();
        saida.close();
        servidor.close();
    }
}
