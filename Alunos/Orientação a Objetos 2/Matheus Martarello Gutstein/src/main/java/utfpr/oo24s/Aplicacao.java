package utfpr.oo24s;

import javax.persistence.EntityManager;
import utfpr.oo24s.dao.AnimalDAO;
import utfpr.oo24s.dao.ProfissionalDAO;
import utfpr.oo24s.dao.ServicosRealizadosDAO;
import utfpr.oo24s.menu.MenuAnimal;
import utfpr.oo24s.menu.MenuProfissional;
import utfpr.oo24s.menu.MenuServico;
import utfpr.oo24s.model.Animal;
import utfpr.oo24s.model.Profissional;
import utfpr.oo24s.model.Servico;
import utfpr.oo24s.model.ServicosRealizados;
import utfpr.oo24s.util.Factory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {
        EntityManager em = Factory.getEntityManager(); // Instanciando o Entity Manager através do Factory


        while1:
        while(true){ // Inicia o loop que controla o menu das entidades

            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
            System.out.println("Escolha uma das entidades abaixo para movimenta-la (Somente numeros): ");
            System.out.println("1 - Profissional;");
            System.out.println("2 - Animal;");
            System.out.println("3 - Servicos");
            System.out.println("0 - Sair");

            Scanner scanner = new Scanner(System.in);
            Integer opcao = scanner.nextInt();

            if (opcao >= 0 && opcao < 4){ // Valida se a opção informada está dentro das opções disponíveis

                switch1: // Label do switch para identificá-lo
                switch (opcao){
                    case 1: // Abre o menu das ações referentes ao Profissional
                        MenuProfissional.switchProfissional(em);
                        break switch1;
                    case 2: // Abre o menu das ações referentes ao Animal
                        MenuAnimal.switchAnimal(em);
                        break switch1;
                    case 3: // Abre o menu das ações referentes aos Serviços
                        MenuServico.switchServicos(em);
                        break switch1;
                    case 0: // Finaliza o loop responsável pelo menu
                        break while1;
                }
            } else { // Se a opção informada não bater com nenhuma das alternativas, alerta o usuário e repete o loop.
                System.out.println("A opcao '" + opcao + "' nao condiz com nenhuma das alternativas. Tente novamente \n");
                continue;
            }
        }

        if (em.getTransaction().isActive()){
            em.getTransaction().commit(); // Garante que não tenha nenhuma transação pendente antes de fechar a conexão
        }
        em.close(); // Fecha a conexão
    }
}
