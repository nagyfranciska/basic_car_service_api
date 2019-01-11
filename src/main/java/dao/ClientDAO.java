package dao;

import model.Client;
import service.utility.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
        manager.getTransaction().begin();
        TypedQuery<Client> q = manager.createQuery(
                "SELECT c from Client c JOIN FETCH c.redirectUrisList WHERE c.id = ?1", Client.class);
        q.setParameter(1, clientId);
        Client client = q.getSingleResult();
        manager.close();
        return client;
    }

}
