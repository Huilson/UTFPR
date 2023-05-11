/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.List;
import model.Animal;
import org.bson.Document;
import util.Conection;

/**
 *
 * @author Pedro
 */
public class AnimalDAO extends CRUD<Animal>{

    public AnimalDAO(Conection conection) {
        super(conection.getDb().getCollection("animal"));
    }

    public void atualizar(Animal obj,String nome) {
       mongoCollection.updateOne(Filters.eq("nome", "Aplicação Distribuídas"),
                new Document("$set", new Document ("nome", "Aplicação Distribuídas")
                        .append("professor", "Fábio Favarim")));
    }
    
    
  

 
   

 
   
}
