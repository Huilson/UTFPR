package utfpr.oo24s.menu;

import utfpr.oo24s.dao.AnimalDAO;
import utfpr.oo24s.dao.ProfissionalDAO;
import utfpr.oo24s.dao.ServicosRealizadosDAO;
import utfpr.oo24s.model.Animal;
import utfpr.oo24s.model.Profissional;
import utfpr.oo24s.model.Servico;
import utfpr.oo24s.model.ServicosRealizados;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public abstract class MenuServico {

    public static void switchServicos(EntityManager em){ // Responsável pelo menu dos Serviços
        while1: // Label para identificar o while
        while (true) {
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=- SERVICO =-=-=-=-=-=-=-=-=-=-=-=-\n");
            System.out.println("Escolha uma das opcoes abaixo (Somente numeros): ");
            System.out.println("1 - Consultar lista de servicos;");
            System.out.println("2 - Consultar servicos ainda nao realizados para um animal no dia;");
            System.out.println("3 - Registrar a realizacao de um novo servico;");
            System.out.println("0 - Voltar");

            Scanner scanner = new Scanner(System.in);
            Integer opcao = scanner.nextInt();

            if(opcao >= 0 && opcao < 4) {
                switch1: // Label para identificar o switch
                switch (opcao) {
                    case 1: // Consultar lista completa de servicos
                        consultarListaServicos();
                        break switch1;
                    case 2: // Consultar servicos não realizados em um dia específico para um animal específico
                        consultarServicosNaoRealizadosNoDiaPorAnimal(em);
                        break switch1;
                    case 3: // Registrar a realização de um serviço
                        cadastrarServico(em);
                        break switch1;
                    case 0: // Voltar oa menu inicial
                        break while1;
                }
            }
        }
    }

    public static void consultarListaServicos(){ // Consulta lista padrão de serviços através do Enum
        System.out.println("=-=-=-=-=-=-=-=-=- LISTA DIARIA DE SERVICOS =-=-=-=-=-=-=-=-=-\n");
        Integer i = 0;

        for (Servico servico : Servico.values()){
            System.out.println(i + " - " + servico.getDescricao());
            i++;
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consultarServicosNaoRealizadosNoDiaPorAnimal(EntityManager em){ // Consulta os servicos ainda nao realizados no dia para um animal especifico
        Scanner scanner = new Scanner(System.in);
        Long animalid = 0L;

        while (true){
            System.out.println("Informe o ID do animal: ");
            animalid = scanner.nextLong();

            AnimalDAO animalDAO = new AnimalDAO(em);
            Animal animal = animalDAO.findById(animalid); // Busca o animal infdrmado pelo usuário

            if(animal == null){
                System.out.println("Nenhum animal encontrado com o ID informado, tente novamente.");
                continue;
            } else {
                break;
            }
        }

        System.out.println("Informe o dia");
        Integer dia = scanner.nextInt();

        System.out.println("Informe o mes: ");
        Integer mes = scanner.nextInt() - 1;

        System.out.println("Informe o ano: ");
        Integer ano = scanner.nextInt();

        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes, dia);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatoData.format(calendar.getTime());

        ServicosRealizadosDAO servicosRealizadosDAO = new ServicosRealizadosDAO(em);
        List<Servico> servicosNaoRealizados = servicosRealizadosDAO.buscaServicosNaoRealizadosNoDia(calendar, animalid); // Realiza o select no banco de dados

        System.out.println("\nServicos nao realizados para o animal de ID " + animalid + " no dia " + dataFormatada + "\n");

        for (Servico servico : servicosNaoRealizados){
            System.out.println(servico.getDescricao());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cadastrarServico(EntityManager em){ // Registrar serviços realizados
        Scanner scanner = new Scanner(System.in);

        em.getTransaction().begin(); // Começa uma nova transação.

        while (true) {
            System.out.println("\nInforme o servico que deseja registrar (Somente numeros): ");
            consultarListaServicos(); // Consome o método que imprime a lista padrão de serviços
            Integer opcao = scanner.nextInt();
            Servico servico = null;

            if (!(opcao >= 0 && opcao < Servico.values().length)){ // Verifica se a opção informada está dentro dos valores disponíveis
                System.out.println("Nenhuma altenativa condiz com o valor informado. \nTente novamente.");
                continue;
            } else {
                servico = Servico.values()[opcao];
            }

            System.out.println("\nInforme o ID do animal que deseja registrar o servico '" + Servico.values()[opcao].getDescricao() + "'.");
            Long animalid = scanner.nextLong();

            AnimalDAO animalDAO = new AnimalDAO(em);
            Animal animal = animalDAO.findById(animalid); // Busca o animal informado pelo usuário no banco de dados

            if(animal == null){
                System.out.println("Nenhum animal com o ID '" + animalid + "' foi encontrado. \nTente novamente.");
                continue;
            }

            System.out.println("\nInforme o ID do treinador/profissional que realizou o servico '" + Servico.values()[opcao].getDescricao() + "'.");
            Long profissionalid = scanner.nextLong();

            ProfissionalDAO profissionalDAO = new ProfissionalDAO(em);
            Profissional profissional = profissionalDAO.findById(profissionalid); // Busca o profissional informado pelo usuário no banco de dados

            if(profissional == null){
                System.out.println("Nenhum profissional com o ID '" + animalid + "' foi encontrado. \nTente novamente.");
                continue;
            }

            System.out.println("Informe o dia do mes em que o servico foi realizado");
            Integer dia = scanner.nextInt();

            System.out.println("Informe o mes: ");
            Integer mes = scanner.nextInt() - 1;

            System.out.println("Informe o ano: ");
            Integer ano = scanner.nextInt();

            System.out.println("Informe a hora: ");
            Integer hora = scanner.nextInt();

            System.out.println("Informe o minuto: ");
            Integer minuto = scanner.nextInt();

            Calendar calendar = Calendar.getInstance();
            calendar.set(ano, mes, dia, hora, minuto);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dataFormatada = formato.format(calendar.getTime());

            ServicosRealizados servicoRealizado = new ServicosRealizados(servico, animal, profissional, calendar);
            ServicosRealizadosDAO servicosRealizadosDAO = new ServicosRealizadosDAO(em);

            servicosRealizadosDAO.gravar(servicoRealizado); // Inicia transação de inserção
            em.getTransaction().commit(); // Faz o commit do insert no banco de dados

            System.out.println("\nO servico com o dados abaixo foi gravado com sucesso!");
            System.out.println("Servico: " + servico.getDescricao() + "\n" +
                    "Animal: " + animal.getAnimalid() + " - " + animal.getNome() +
                    "\nProfissional: " + profissional.getProfissionalid() + " - " + profissional.getNome() +
                    "\nData e hora: " +  dataFormatada);
            break;
        }

    }
}
