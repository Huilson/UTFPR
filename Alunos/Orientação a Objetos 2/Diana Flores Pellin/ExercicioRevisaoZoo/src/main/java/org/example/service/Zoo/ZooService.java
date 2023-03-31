package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;

public class ZooService {

    //Dados de conexão
    String driverClassName = "org.postgresql.Driver";
    String url = "jdbc:postgresql://babar.db.elephantsql.com:5432/xhjbnqbb";
    String user = "xhjbnqbb";
    String password = "VgxXRZffm5nXkBfIIngOQHxLNHtcYdCV";

    public void createFuncionario(String nome, String cpf) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();

        String queryFuncionario = "INSERT INTO FUNCIONARIO VALUES(DEFAULT, '" + nome + "', '" + cpf + "')";
        st.execute(queryFuncionario);
        con.close();
    }
    public void createAnimal(String especie, String nomeAnimal, String descricao, String nomeResponsavel) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();

        String queryAnimal = "INSERT INTO ANIMAL VALUES(DEFAULT, '" + especie + "', '" + nomeAnimal + "', '" + descricao+ "', (SELECT ID_FUNCIONARIO FROM FUNCIONARIO WHERE NOME = '" + nomeResponsavel + "'),true)";
        st.execute(queryAnimal);
        con.close();
    }

    public void createServico(String descricao, String data, int idAnimal, String nomeResponsavel) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();

        String queryServico = "INSERT INTO SERVICO VALUES(DEFAULT, '" + descricao + "', '" + data + "', '" + idAnimal + "', (SELECT ID_FUNCIONARIO FROM FUNCIONARIO WHERE NOME = '" + nomeResponsavel + "',  true)";
        st.execute(queryServico);
        con.close();
    }

    public void listarServicos() throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();

        st.execute("SELECT * FROM SERVICOS WHERE CONCLUIDO = FALSE");

        System.out.println("=================================");
        System.out.println("Registro Aqui :)");
        System.out.println("=================================");
    }
}

