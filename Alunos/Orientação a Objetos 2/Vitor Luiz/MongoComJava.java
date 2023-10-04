/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mongocomjava;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.Arrays;
import java.util.Date;
import org.bson.Document;

/**
 *
 * @author Vitor
 */
public class MongoComJava {

    public static void main(String[] args) {
        MongoClient conecta = new MongoClient();
        
        MongoDatabase db = conecta.getDatabase("aluno");
        
        MongoCollection<Document> alunos = db.getCollection("alunos");
        
        Document novoAluno = new Document("_id", 1)
                .append("nome", "Vitor")
                .append("data_nascimento", new Date(2001, 03, 16))
                .append("idade", 22)
                .append("disciplina", "Orientacao a Objetos 2")
                .append("Notas", Arrays.asList(7,5,9))
                .append("horas complementares", Arrays.asList(
                        new Document()
                                .append("nome", "Estagio")
                                .append("Carga_horaria", 200)
                                .append("Categoria", "A"),
                        new Document()
                                .append("nome", "Semana academica")
                                .append("Carga_horaria", 40)
                                .append("Categoria", "C")
                ));
        
        
//        alunos.insertOne(novoAluno);
        
        
        //SELECT FIRST RECORD
        Document aluno = alunos.find().first();
        System.out.println("ALUNO: " + aluno);
        
        //SELECT WITH FILTER
        MongoCursor<Document> cursor = alunos.find(Filters.eq("id", 2)).iterator();
        
        while(cursor.hasNext()){
            System.out.println("diukchwaduiwqdiwqj" + cursor.next());
        }
        
        
        //Atualizar documento
        alunos.updateOne(Filters.eq("nome", "Vitor"), new Document("$set", new Document("nome", "Vitor Luiz")));
  
    
//        DELETE
//        alunos.deleteOne(Filters.eq("_id", 1));
    }
}
