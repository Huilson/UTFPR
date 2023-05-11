package br.edu.utfpr.Main;

import br.edu.utfpr.novoPacote.MetodosAddDados;
import br.edu.utfpr.novoPacote.MetodosAtualizaDados;
import br.edu.utfpr.novoPacote.MetodosConsulta;
import br.edu.utfpr.novoPacote.MetodosDelete;
import java.util.Scanner;

public class Menus {

    Scanner scanner = new Scanner(System.in);

    public Menus() {
        menuEscolha();
    }

    public int menu() {
        System.out.println("\nInforme a opção desejada:\n"
                + "1 - Cadastrar dados;\n"
                + "2 - Deletar dados;\n"
                + "3 - Consultar dados;\n"
                + "4 - Atualizar dados;\n"
                + "5 - Sair;");
        return scanner.nextInt();
    }

    public void menuEscolha() {
        int opcao = menu();
        while (opcao < 6) {
            switch (opcao) {
                case 1:
                    crudMongo();
                    break;
                case 2:
                    crudMongoDelete();
                    break;
                case 3:
                    consulta();
                    break;
                case 4:
                    atualizador();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA.");
                    break;
            }
            opcao = menu();
        }
        System.out.println("===================================");
        menu();
    }

    public int menuAtualizador() {
        System.out.println("\nInforme a opção desejada:\n"
                + "1 - Atualizar um profissional;\n"
                + "2 - Atualizar um animal;\n"
                + "3 - Atualizar um serviço;\n"
                + "4 - Sair;");
        return scanner.nextInt();
    }

    public void atualizador() {
        int opcao = menuAtualizador();
        while (opcao < 4) {
            MetodosAtualizaDados atualizador = new MetodosAtualizaDados();
            switch (opcao) {
                case 1:
                    atualizador.atualizaProfissional();
                    break;
                case 2:
                    atualizador.atualizaAnimal();
                    break;
                case 3:
                    atualizador.atualizaServico();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA.");
                    break;
            }
            opcao = menuAtualizador();
        }
        System.out.println("===================================");
        menuEscolha();
    }

    public int menuCadastro() {
        System.out.println("\nInforme a opção desejada:\n"
                + "1 - Cadastrar um profissional;\n"
                + "2 - Cadastrar um animal;\n"
                + "3 - Cadastrar um serviço;\n"
                + "4 - Sair;");
        return scanner.nextInt();
    }

    public void crudMongo() {
        int opcao = menuCadastro();
        while (opcao < 4) {
            MetodosAddDados addDados = new MetodosAddDados();
            switch (opcao) {
                case 1:
                    addDados.cadastrarProfissional();
                    break;
                case 2:
                    addDados.cadastrarAnimal();
                    break;
                case 3:
                    addDados.cadastrarServico();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA.");
                    break;
            }
            opcao = menuCadastro();
        }
        System.out.println("===================================");
        menuEscolha();
    }

    public int menuConsulta() {
        System.out.println("\nInforme a opção desejada:\n"
                + "1 - Ver serviços não feitos;\n"
                + "2 - Ver serviços já feitos;\n"
                + "3 - Ver lista de animais;\n"
                + "4 - Ver lista de profissionais;\n"
                + "5 - Todos os serviços;\n"
                + "6 - Sair;");
        return scanner.nextInt();
    }

    public void consulta() {
        int opcao = menuConsulta();
        while (opcao < 6) {
            MetodosConsulta consulta = new MetodosConsulta();
            switch (opcao) {
                case 1:
                    consulta.returnServicoNaoFeito();
                    break;
                case 2:
                    consulta.returnServicoFeito();
                    break;
                case 3:
                    consulta.listaAnimais();
                    break;
                case 4:
                    consulta.listaProfissionais();
                    break;
                case 5:
                    consulta.listaServico();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA.");
                    break;
            }
            opcao = menuConsulta();
        }
        System.out.println("===================================");
        menuEscolha();
    }

    public int menuDelete() {
        System.out.println("\nInforme a opção desejada:\n"
                + "1 - Excluir animal\n"
                + "2 - Excluir profissional\n"
                + "3 - Excluir serviço\n"
                + "4 - Sair;");
        return scanner.nextInt();
    }

    public void crudMongoDelete() {
        int opcao = menuDelete();
        while (opcao < 4) {
            MetodosDelete delete = new MetodosDelete();
            switch (opcao) {
                case 1:
                    delete.deletarAnimal();
                    break;
                case 2:
                    delete.deletarProfissional();
                    break;
                case 3:
                    delete.deletarServico();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA.");
                    break;
            }
            opcao = menuDelete();
        }
        System.out.println("===================================");
        menuEscolha();
    }
}
