package oauth.dao;

import oauth.model.FinalClient;
import service.JPAUtility;

import javax.persistence.EntityManager;

public class FinalClientDAO extends JPAUtility {

    public FinalClient save(FinalClient client) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(client);
        manager.getTransaction().commit();
        manager.close();
        return client;
    }

    public FinalClient findById(String clientId) {
        EntityManager manager = getEntityManager();
        FinalClient client = manager.find(FinalClient.class, clientId);
        manager.close();
        return client;
    }
}
