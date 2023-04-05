/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package utfpr.trabalho;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import utfpr.trabalho.dao.AnimalDAO;
import utfpr.trabalho.dao.FuncionarioDAO;
import utfpr.trabalho.dao.ServicoDAO;
import utfpr.trabalho.dao.TipoFuncionarioDAO;
import utfpr.trabalho.model.Animal;
import utfpr.trabalho.model.Funcionario;
import utfpr.trabalho.model.Servico;
import utfpr.trabalho.model.TipoFuncionario;
import utfpr.trabalho.util.Factory;

/**
 *
 * @author Pedro
 */
public class Trabalho {

    public static void main(String[] args) {
          int res,res1= 0;
          Long idanimal,idfuncionario,idservico,idtipo;
          String nome,especie,dia,mes,ano,hora,minuto ;
          LocalDateTime data = null;
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
          EntityManager em = Factory.getEntityManager();
          Scanner sca = new Scanner(System.in);
          FuncionarioDAO funcionarioDAO = new FuncionarioDAO(em);
          AnimalDAO animalDAO = new AnimalDAO(em);
          ServicoDAO servicoDAO = new ServicoDAO(em);
          TipoFuncionarioDAO tipoFuncionarioDAO = new TipoFuncionarioDAO(em);
          
          
          
          
          while(res1!=1)
          {
            em.getTransaction().begin();
            System.out.println("1- para cadastrar funcionario");
            System.out.println("2- para cadastrar animal");
            System.out.println("3- para cadastrar servico");
            System.out.println("4- listar todos os servicos");
            System.out.println("5- listar todos os servicos nao feitos");
            System.out.println("6- resgistar servico como feito");
            System.out.println("7- resgistar tipo do funcionario");
            System.out.println("8- resgistar servico como feito em massa(comparando com a data de hoje)");
            System.out.println("9- listar animais e seus respectivos funcionarios");
            res = sca.nextInt();
            switch (res) {

              case 1:
                  if(!tipoFuncionarioDAO.buscaTodos().isEmpty())
                  {
                    System.out.println("Qual o nome do funcionario?");
                    nome = sca.next();
                    System.out.println("Qual o tipo do funcionario? Selecione o id");
                    tipoFuncionarioDAO.listarLista();
                    idtipo = sca.nextLong();
                    Funcionario funcionario = new Funcionario(nome,tipoFuncionarioDAO.buscaObjeto(idtipo));
                    funcionarioDAO.salvar(funcionario);
                  }
                  else
                      System.out.println("nao ha tipo de funcionario cadastrado");
                  em.getTransaction().commit();
              break;

              case 2:
                  System.out.println("Qual o nome do animal?");
                  nome = sca.next();
                  System.out.println("Qual a especie do animal?");
                  especie = sca.next();
                  Animal animal = new Animal(nome,especie);
                  animalDAO.salvar(animal);
                  em.getTransaction().commit();
              break;
              case 3:
                  if(!animalDAO.buscaTodos().isEmpty() || !funcionarioDAO.buscaTodos().isEmpty())
                  {
                      System.out.println("Qual a descricao do servico?");
                      nome = sca.next();
                      System.out.println("Qual o dia do servico? dd");
                      dia = sca.next();
                      System.out.println("Qual o mes do servico? MM");
                      mes = sca.next();
                      System.out.println("Qual o ano do servico? YYYY");
                      ano = sca.next();
                      System.out.println("Qual a hora do servico? HH");
                      hora = sca.next();
                      System.out.println("Qual o minuto do servico? mm");
                      minuto = sca.next();
                      System.out.println("Escolha o id do animal");
                      animalDAO.listarLista();
                      idanimal = sca.nextLong();
                      System.out.println("Escolha o id do funcionario");
                      funcionarioDAO.listarLista();
                      idfuncionario = sca.nextLong();
                      Servico servico = new Servico(nome,LocalDateTime.parse(dia+"/"+mes+"/"+ano+" "+hora+":"+minuto, dtf),funcionarioDAO.buscaObjeto(idfuncionario),
                              animalDAO.buscaObjeto(idanimal));
                      servicoDAO.salvar(servico);       
                  }
                  else if (animalDAO.buscaTodos().isEmpty())
                  {
                      System.out.println("nao ha animal cadastrado");
                  }
                  else if(funcionarioDAO.buscaTodos().isEmpty())
                  {
                      System.out.println("nao ha funcionario cadastrado");
                  }
                  em.getTransaction().commit(); 
              break;

              case 4:
                   servicoDAO.buscaTodos().forEach(a ->{System.out.println(a);});  
                   em.getTransaction().commit();
              break;

              case 5:
                   servicoDAO.listarTarefasNaoFeitas();
                   if(servicoDAO.listaNaoFeitas().isEmpty())
                   {
                        System.out.println("Todas as tarefas foram feitas");   
                   }
                   em.getTransaction().commit();
              break;

              case 6:
                  if(!servicoDAO.listaNaoFeitas().isEmpty())
                  {
                   System.out.println("Selecione um id de um servico que nao esteja feito");
                   servicoDAO.listarTarefasNaoFeitas();
                   idservico = sca.nextLong();
                   servicoDAO.buscaObjeto(idservico).setStatus(true);
                   servicoDAO.atualizar(servicoDAO.buscaObjeto(idservico));
                   em.getTransaction().commit();
                  }
                  else
                      System.out.println("Todas as tarefas foram feitas");
                  em.getTransaction().commit();
              break;

              
              case 7:
                  System.out.println("Qual o nome do tipo do funcionario?");
                  nome = sca.next();
                  TipoFuncionario tipoFuncionario = new TipoFuncionario(nome);
                  tipoFuncionarioDAO.salvar(tipoFuncionario);
                  em.getTransaction().commit();
              break;
              
              
              case 8:
                  servicoDAO.registrarServicoMassa();
                  System.out.println("Servicos atualizados com sucesso");
                  em.getTransaction().commit();
              break;
              
              
              case 9:
                  servicoDAO.listarFuncionariosAnimais();
                  em.getTransaction().commit();
              break;
              
              default:
              System.out.println("Opcao invalida");

              }
              System.out.println("para parar digite 1 e qualquer outro numero para continuar");
              res1 = sca.nextInt();
        }
           em.close();
          
    }
}
