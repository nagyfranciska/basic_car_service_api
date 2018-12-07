package dao;

import model.Client;
import service.utility.JPAUtility;

import javax.persistence.EntityManager;

public class ClientDAO extends JPAUtility {

    public Client save(Client client) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(client);
        manager.getTransaction().commit();
        manager.close();
        return client;
    }

    public void delete(String clientId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Client client = manager.find(Client.class, clientId);
            client = manager.merge(client);
            manager.remove(client);
            manager.joinTransaction();
            manager.flush();
            manager.getTransaction().commit();
            manager.close();
    }

    public Client findById(String clientId) {
        EntityManager manager = getEntityManager();
        Client car = manager.find(Client.class, clientId);
        manager.close();
        return car;
    }

}
