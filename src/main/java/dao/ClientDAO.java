package dao;

import model.Client;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDAO extends JPAUtility {

    public ClientDAO() {}

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
        Client client = manager.find(Client.class, id);
        manager.close();
        return client;
    }

    public Client delete(String id) {
        return null;
    }

    public Client update() {
        return null;
    }

}
