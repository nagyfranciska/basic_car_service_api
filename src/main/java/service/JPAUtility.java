package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtility {
    private static EntityManagerFactory emFactory;
    public static EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }
    public static void close(){
        emFactory.close();
    }

    public static void initFactory() {
        emFactory = Persistence.createEntityManagerFactory("jpa");
    }

}
