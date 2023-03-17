package utfpr.aulajpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {
    /*Crio um factory para gerar managers com base na minha unidade de 
    persistencia definida em src/main/resources/META-INF*/
    private static final EntityManagerFactory FACTORY = 
            Persistence.createEntityManagerFactory("aulaJPA");
    
    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
