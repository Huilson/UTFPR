/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utfpr.trabalho.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Pedro
 */
public class Factory {
    private static final EntityManagerFactory FACTORY = 
            Persistence.createEntityManagerFactory("trabalho");
    
    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
