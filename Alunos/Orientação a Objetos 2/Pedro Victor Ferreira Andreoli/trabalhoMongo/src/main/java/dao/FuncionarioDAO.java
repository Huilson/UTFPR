/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoCollection;
import model.Funcionario;
import org.bson.Document;
import util.Conection;

/**
 *
 * @author Pedro
 */
public class FuncionarioDAO extends CRUD<Funcionario> {
    Conection conection;
    public FuncionarioDAO(Conection conection) {
        super(conection.getDb().getCollection("funcionario"));
    }
    
}
