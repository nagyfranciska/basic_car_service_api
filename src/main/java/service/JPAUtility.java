package service;

import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtility {
    @Inject
    private  EntityManagerFactory emFactory;

    public EntityManager getEntityManager() {

        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }

    public void initJPA() {
        emFactory = Persistence.createEntityManagerFactory("jpa");
    }

}
