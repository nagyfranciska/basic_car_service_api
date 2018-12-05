package dao;

import model.CDUser;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO extends JPAUtility {

    public UserDAO() {}

    public List<CDUser> findAll() {
        EntityManager manager = getEntityManager();
        TypedQuery<CDUser> q = manager.createQuery("SELECT u FROM CDUser u", CDUser.class);
        List<CDUser> userList = q.getResultList();
        manager.close();
        return userList;
    }

    public List<CDUser> findAllByGarage(Integer garageId) {
        EntityManager manager = getEntityManager();
        TypedQuery<CDUser> q = manager.createQuery("SELECT u FROM CDUser u WHERE GARAGE_ID = ?1", CDUser.class);
        q.setParameter(1, garageId);
        List<CDUser> userList = q.getResultList();
        manager.close();
        return userList;
    }

    public CDUser save(CDUser user) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();
        return user;
    }

    public CDUser findById(Integer userId) {
        EntityManager manager = getEntityManager();
        CDUser user = manager.find(CDUser.class, userId);
        manager.close();
        return user;
    }


    public CDUser delete(Integer serviceId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        CDUser service = manager.find(CDUser.class, serviceId);
        if (service != null) {
            service = manager.merge(service);
            manager.remove(service);
            manager.joinTransaction();
            manager.flush();
            manager.getTransaction().commit();
            manager.close();
            return service;
        } else {
            return null;
        }
    }
}
