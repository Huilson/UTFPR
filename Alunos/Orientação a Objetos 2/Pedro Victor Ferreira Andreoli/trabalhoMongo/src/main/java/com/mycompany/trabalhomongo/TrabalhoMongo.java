/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabalhomongo;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import dao.AnimalDAO;
import dao.FuncionarioDAO;
import dao.ServicoDAO;
import dao.TipoFuncionarioDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import model.Animal;
import model.TipoFuncionario;
import org.bson.Document;
import util.Conection;

/**
 *
 * @author Pedro
 */
public class TrabalhoMongo {

    public static void main(String[] args) {
        Conection conection = new Conection();
        String nome,especie,nome_cientifico,familia,comportamento,tipo,funcionario,animal,dia,mes,ano,hora,minuto;
        int res,res1= 0;
        //conectar a coleção
        Scanner sca = new Scanner(System.in);
        
        AnimalDAO animaldao = new AnimalDAO(conection);
        TipoFuncionarioDAO tipoFuncionarioDAO = new TipoFuncionarioDAO(conection);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conection);
        ServicoDAO servicoDAO = new ServicoDAO(conection);
        while(res1!=1)
        {
            System.out.println("1- para cadastrar animal");
            System.out.println("2- para cadastrar funcionario");
            System.out.println("3- para cadastrar servico");
            System.out.println("4- listar todos os servicos");
            System.out.println("5- listar todos os servicos nao feitos");
            System.out.println("6- resgistar servico como feito");
            System.out.println("7- resgistar tipo do funcionario");
            res = sca.nextInt();
            switch (res) {
            case 1:
                System.out.println("Qual o nome do animal?");
                nome = sca.next();
                System.out.println("Qual a especie do animal?");
                especie = sca.next();
                System.out.println("Qual o nome cientifico do animal?");
                nome_cientifico = sca.nextLine();
                System.out.println("Qual o familia do animal?");
                familia = sca.next();
                System.out.println("Qual o comportamento do animal?");
                comportamento = sca.nextLine();
                Document novoAnimal = new Document("nome",nome)
                    .append("especie", especie)
                    .append("nome_cientifico", nome_cientifico)
                    .append("familia", familia)
                    .append("comportamento", comportamento);
                animaldao.salvar(novoAnimal);
                break;
            case 2:
                if(!tipoFuncionarioDAO.ehNulo())
                {
                    System.out.println("Qual o nome do funcionario?");
                    nome = sca.next();
                    System.out.println("Qual o nome do tipo de funcionario?");
                    tipoFuncionarioDAO.listarLista();
                    tipo = sca.next();
                    Document novoFuncionario = new Document("nome", nome)
                    .append("tipoFuncionario",
                        (tipoFuncionarioDAO.buscaObjeto(tipo)));
                    funcionarioDAO.salvar(novoFuncionario);
                }
                else
                     System.out.println("nao ha tipo de funcionario cadastrado");
                break;
            case 3:
                if(!funcionarioDAO.ehNulo() && !animaldao.ehNulo())
                {
                    System.out.println("Qual o nome do Servico");
                    nome = sca.next();
                    System.out.println("Qual o nome do funcionario?");
                    funcionarioDAO.listarLista();
                    funcionario = sca.next();
                    System.out.println("Qual o nome do animal");
                    animaldao.listarLista();
                    animal = sca.next();
                    System.out.println("Qual o dia?");
                    dia = sca.next();
                    System.out.println("Qual o mes?");
                    mes = sca.next();
                    System.out.println("Qual o ano?");
                    ano = sca.next();
                    System.out.println("Qual a hora?");
                    hora = sca.next();
                    System.out.println("Qual o minuto?");
                    minuto = sca.next();
                    Document novoServico = new Document("nome",nome)
                            .append("funcionario", (funcionarioDAO.buscaObjeto(funcionario)))
                            .append("animal", (animaldao.buscaObjeto(animal)))
                            .append("data",dia+"/"+mes+"/"+ano+" "+hora+":"+minuto)
                            .append("status", false);
                    servicoDAO.salvar(novoServico);
                }
                else if(funcionarioDAO.ehNulo())
                  System.out.println("nao ha funcionario cadastrado");
                else
                  System.out.println("nao ha animal cadastrado");
                break;
            case 4:
                servicoDAO.listarLista();
                break;
            case 5:
                servicoDAO.listarnaoFeito();
                break;
            case 6:
                System.out.println("Qual servico deseja fazer?");
                servicoDAO.listarLista();
                nome = sca.next();
                servicoDAO.fazerServico(nome);
                break;
            case 7:
                 System.out.println("Qual o nome do tipo do funcionario?");
                 nome = sca.next();
                 Document novoTipoFuncional = new Document("nome",nome);
                 tipoFuncionarioDAO.salvar(novoTipoFuncional);
                 break;
            default:
                System.out.println("Opcao invalida");
            }        
            System.out.println("para parar digite 1 e qualquer outro numero para continuar");
            res1 = sca.nextInt();
        }
        conection.fecharConection();
        
        
    }
}
