package oauth.dao;

import oauth.model.Client;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDAO extends JPAUtility {

    public List<Client> findAll() {
        EntityManager manager = getEntityManager();
        TypedQuery<Client> q = manager.createQuery("SELECT c FROM Client c", Client.class);
        List<Client> clientList = q.getResultList();
        manager.close();
        return clientList;
    }

    public Client save(Client client) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(client);
        manager.getTransaction().commit();
        manager.close();
        return client;
    }

    public Client findById(String id) {
        EntityManager manager = getEntityManager();
        TypedQuery<Client> q = manager.createQuery("SELECT c FROM Client c WHERE c.id = ?1", Client.class);
        q.setParameter(1, id);
        List<Client> clientList = q.getResultList();
        manager.close();
        return clientList.get(0);
    }

    public Client delete(String id) {
        return null;
    }

    public Client update() {
        return null;
    }

}
