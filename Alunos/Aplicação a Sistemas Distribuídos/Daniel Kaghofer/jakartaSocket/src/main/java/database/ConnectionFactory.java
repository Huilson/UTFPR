package database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionFactory {
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("AulaJPA");

    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
