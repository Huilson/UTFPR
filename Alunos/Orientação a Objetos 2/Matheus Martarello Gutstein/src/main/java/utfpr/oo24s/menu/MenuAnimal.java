package utfpr.oo24s.menu;

import utfpr.oo24s.dao.AnimalDAO;
import utfpr.oo24s.dao.ProfissionalDAO;
import utfpr.oo24s.model.Animal;
import utfpr.oo24s.model.Profissional;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public abstract class MenuAnimal {
    public static void switchAnimal (EntityManager em){ // Responsável pelo menu dos Animais
        while1: // Label para identificar o while
        while (true){
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=- ANIMAL =-=-=-=-=-=-=-=-=-=-=-=-\n");
            System.out.println("Escolha uma das opcoes abaixo (Somente numeros): ");
            System.out.println("1 - Cadastrar animal;");
            System.out.println("2 - Buscar todos os animais;");
            System.out.println("3 - Buscar animais por ID do profissional;");
            System.out.println("4 - Buscar animais que ainda nao tiveram nenhum servico realizado no dia;");
            System.out.println("0 - Voltar");

            Scanner scanner = new Scanner(System.in);
            Integer opcao = scanner.nextInt();

            if(opcao >= 0 && opcao < 5){
                switch1: // Label para identificar o switch
                switch (opcao){
                    case 1: // Cadastrar novo animal
                        ProfissionalDAO profissionalDAO = new ProfissionalDAO(em);

                        if(!profissionalDAO.temRegistros()){ // Verifica se existe ao menos um registro de profissional gravado em banco
                            System.out.println("\nNao existe nenhum profissional salvo na base de dados." +
                                    "\n Por favor, cadastre um profissional para que seja possivel cadastrar um novo animal.");
                            break switch1;
                        }
                        cadastrarAnimal(em);
                        break switch1;
                    case 2: // Buscar todos os animais do banco
                        buscarAnimais(em);
                        break switch1;
                    case 3: // Buscar todos os animais relacionados à um profissional específico
                        buscarAnimaisPorProfissional(em);
                        break switch1;
                    case 4: // Busca todos os animais que ainda não tiveram nenhum serviço para o dia
                        buscarAnimaisSemServicoNoDia(em);
                        break switch1;
                    case 0: // Volta para o menu inicial
                        break while1;
                    default:
                        break switch1;
                }
            } else {
                System.out.println("A opcao '" + opcao + "' nao condiz com nenhuma das alternativas. Tente novamente \n");
                continue;
            }
        }
    }

    public static void cadastrarAnimal(EntityManager em) { // Responsável pelo cadastro de novos animais
        Scanner scanner = new Scanner(System.in);
        em.getTransaction().begin(); // Inicia uma nova transação

        while (true) {
            System.out.println("\nInforme o nome do animal: ");
            String nome = scanner.nextLine();
            System.out.println("Informe a raca do animal: ");
            String raca = scanner.nextLine();
            System.out.println("Informe o ID do treinador responsavel pelo animal: ");
            Long treinadorid = scanner.nextLong();

            if ((nome == "" || nome == null) || (raca == "" || raca == null) || (treinadorid == 0 || treinadorid == null)) { // Se o nome, raça ou treinador estiverem vazios, irá solicitar as informações novamente.
                System.out.println("\nUma das opcoes informadas esta vazia. Tente novamente.\n");
                continue;
            }

            ProfissionalDAO profissionalDAO = new ProfissionalDAO(em);
            Profissional treinador = profissionalDAO.findById(treinadorid); // Busca o profissional no banco de dados

            if (treinador == null) {
                System.out.println("\n Nao existe nenhum profissional com o ID informado, tente novamente.");
                continue;
            }

            Animal animal = new Animal(nome, raca, treinador);
            AnimalDAO animalDAO = new AnimalDAO(em);

            animalDAO.gravar(animal); // Inicia a transação para inserir o novo animal
            em.getTransaction().commit(); // Comita a inserção no banco de dados

            System.out.println("Animal de ID " + animal.getAnimalid() + " foi cadastrado com sucesso!");

            break;
        }
    }

    public static void buscarAnimais (EntityManager em){ // Busca todos os profissionais do banco de dados
        AnimalDAO animalDAO = new AnimalDAO(em);
        List<Animal> animais = animalDAO.findAll(); // Executa select sem where no banco de dados. O ideal seria paginar

        System.out.println("\nLista de animais:\n");
        for (Animal animal : animais){
            System.out.println("ID e nome: " + animal.getAnimalid() + " - " + animal.getNome() +
                    "\nRaca: " + animal.getRaca() +
                    "\nTreinador: " + animal.getTreinador().getProfissionalid() + " - " + animal.getTreinador().getNome());
        }
    }

    public static void buscarAnimaisPorProfissional(EntityManager em){ // Busca os animais relacionadas à um profissional específico
        AnimalDAO animalDAO = new AnimalDAO(em);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID do profissional: ");
        Long profissionalid = scanner.nextLong();

        List<Animal> animais = animalDAO.buscarPorProfissional(profissionalid); // Realiza o select no banco de dados

        System.out.println("\nLista de animais:\n");
        for (Animal animal : animais){
            System.out.println("ID e nome: " + animal.getAnimalid() + " - " + animal.getNome() +
                    "\nRaca: " + animal.getRaca() +
                    "\nTreinador: " + animal.getTreinador().getProfissionalid() + " - " + animal.getTreinador().getNome() + "\n");
        }
    }

    public static void buscarAnimaisSemServicoNoDia(EntityManager em){ // Busca os animais que ainda nao tiveram nenhum servico no dia
        AnimalDAO animalDAO = new AnimalDAO(em);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o dia:");
        Integer dia = scanner.nextInt();

        System.out.println("Informe o mes: ");
        Integer mes = scanner.nextInt() - 1;

        System.out.println("Informe o ano: ");
        Integer ano = scanner.nextInt();

        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes, dia);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatoData.format(calendar.getTime());

        List<Animal> animais = animalDAO.buscarAnimaisComServicosNaoRealizados(calendar); // Realiza o select no banco de dados

        if (animais == null || animais.isEmpty()){
            System.out.println("\nTodos os animais ja tem ao menos um servico registrado para o dia.");
        } else {
            System.out.println("\nAnimais que ainda nao receberam nenhum servico no dia " + dataFormatada + ":");

            for (Animal animal : animais){
                System.out.println(animal.getAnimalid() + " - " + animal.getNome());
            }
        }
    }


}
