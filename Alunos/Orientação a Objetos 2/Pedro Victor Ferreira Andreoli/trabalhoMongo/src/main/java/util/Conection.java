/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Pedro
 */
public class Conection {
     private MongoClient client = new MongoClient();     
     private MongoDatabase db = client.getDatabase("zoologico");
     
    public MongoDatabase getDb() {
        return db;
    }
    
    public void fecharConection(){
        client.close();
    }
}

