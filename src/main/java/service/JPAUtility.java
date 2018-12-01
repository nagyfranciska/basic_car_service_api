package service;

import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtility {

    @Inject
    private static EntityManagerFactory emFactory;

    public static EntityManager getEntityManager() {

        return emFactory.createEntityManager();
    }

    public static void close() {
        emFactory.close();
    }

//    public static void initJPA() {
//        emFactory = Persistence.createEntityManagerFactory("jpa");
//    }

}
