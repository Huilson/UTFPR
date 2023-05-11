/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Pedro
 */
public abstract class CRUD<T> implements AbstractDAO<T> {
    MongoCollection<Document> mongoCollection;

    public CRUD(MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }
    //RETORNA TODA A LISTA
    @Override
    public void listarLista(){
        MongoCursor<Document> resultados = mongoCollection.find().iterator();
        int i = 1;
        while(resultados.hasNext()){
            System.out.println("Resultado "+i+": "+resultados.next());
            i++;
        }
    };
    
    @Override
    public List<T> buscaTodos(){
        return null;
    };
    
    //busca e retorna documento pelo nome
    @Override
    public Document buscaObjeto(String nome) {
        return  mongoCollection.find(Filters.eq("nome",nome)).first();
    }

    
    
    //exclui pelo nome
    @Override
    public void excluir(String nome){
        mongoCollection.deleteOne(Filters.eq("nome",nome));
    };
    
    //VERIFICA SE HA OBJETO CADASTRADO
    @Override
    public boolean ehNulo() {
        MongoCursor<Document> resultados = mongoCollection.find().iterator();
        if(resultados.hasNext())
            return false;
        else
            return true;
    }

    @Override
    public void salvar(Document documento){
        mongoCollection.insertOne(documento);
    };
    
    
 
    
}
