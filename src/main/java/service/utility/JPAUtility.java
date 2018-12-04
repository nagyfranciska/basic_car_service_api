package service.utility;

import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JPAUtility {

    @Inject
    private EntityManagerFactory emFactory;

    protected EntityManager getEntityManager() {

        return emFactory.createEntityManager();
    }

    public void close() {
        emFactory.close();
    }

}
