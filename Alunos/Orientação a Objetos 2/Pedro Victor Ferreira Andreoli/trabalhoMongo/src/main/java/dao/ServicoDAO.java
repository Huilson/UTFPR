/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import model.Servico;
import org.bson.Document;
import util.Conection;

/**
 *
 * @author Pedro
 */
public class ServicoDAO extends CRUD<Servico>{
    Conection conection;
    public ServicoDAO(Conection conection){
        super(conection.getDb().getCollection("servico"));
        this.conection = conection;
    }
    
    //lista todos os servicos nao feitos(falso)
    public void listarnaoFeito(){
        MongoCursor<Document> resultados = mongoCollection.find(Filters.eq("status",false)).iterator();
        int i = 1;
        while(resultados.hasNext()){
            System.out.println("Resultado "+i+": "+resultados.next());
            i++;
        }
    }
    //muda servico para true
    public void fazerServico(String nome){
         mongoCollection.updateOne(Filters.eq("nome", nome),
                new Document("$set", new Document ("status", true)));
    }
    
}
