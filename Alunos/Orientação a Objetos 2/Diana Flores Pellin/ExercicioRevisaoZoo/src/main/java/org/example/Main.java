package org.example;

import org.example.service.ZooService;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        ZooService service = new ZooService();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("=============Sistema de Zoo UltraModerno=============");
            System.out.println("Selecione a opção desejada: ");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Cadastrar Animal");
            System.out.println("3 - Cadastrar Serviço");
            System.out.println("4 - Exibir Lista de Serviço Diária");
            System.out.println("0 - Sair");
            System.out.println("");
            opcao = scan.nextInt();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    String nome_funcionario;
                    String cpf;

                    System.out.println("=============Sistema de Zoo UltraModerno : Cadastro de Funcionario=============");
                    System.out.println("Nome do Funcionário: ");
                    nome_funcionario = scan.next();
                    System.out.println("CPF: ");
                    cpf = scan.next();

                    service.createFuncionario(nome_funcionario, cpf);
                    break;

                case 2:
                    String nome_animal;
                    String especie;
                    String descricao;
                    String nomeResponsavel;

                    System.out.println("=============Sistema de Zoo UltraModerno : Cadastro de Animal=============");
                    System.out.println("Nome do Animal: ");
                    nome_animal = scan.next();
                    System.out.println("Espécie do Animal: ");
                    especie = scan.next();
                    System.out.println("Descrição do Animal: ");
                    descricao = scan.next();
                    System.out.println("Defina um responsável: ");
                    nomeResponsavel = scan.next();

                    if(nomeResponsavel == "") {
                        System.out.println("Favor digite um responsável válido: ");
                        nomeResponsavel = scan.next();
                    }

                    service.createAnimal(especie, nome_animal, descricao, nomeResponsavel);
                    break;

                case 3:
                    String descricao_servico;
                    String data;
                    int idAnimal;
                    String nomeResponsavelServico;


                    System.out.println("=============Sistema de Zoo UltraModerno : Cadastro de Serviço=============");
                    System.out.println("Descreva o serviço: ");
                    descricao_servico = scan.next();
                    System.out.println("Defina uma data: ");
                    data = scan.next();
                    System.out.println("Informe o animal: ");
                    idAnimal = scan.nextInt();
                    System.out.println("Defina um responsável: ");
                    nomeResponsavelServico = scan.next();

                    if(nomeResponsavelServico == "") {
                        System.out.println("Favor digite um responsável válido: ");
                        nomeResponsavelServico = scan.next();
                    }
                    service.createServico(descricao_servico, data, idAnimal, nomeResponsavelServico);
                case 4:
                    service.listarServicos();
            }
        }
    }
}