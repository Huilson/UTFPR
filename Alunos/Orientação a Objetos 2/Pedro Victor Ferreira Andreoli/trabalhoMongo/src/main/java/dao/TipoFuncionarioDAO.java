/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import model.Animal;
import model.TipoFuncionario;
import org.bson.Document;
import util.Conection;

/**
 *
 * @author Pedro
 */
public class TipoFuncionarioDAO  extends CRUD<TipoFuncionario>{
    Conection conection;
    public TipoFuncionarioDAO(Conection conection) {
        super(conection.getDb().getCollection("tipofuncionario"));
        this.conection = conection;
    }
    
}
