package dao;

import model.CDUser;
import service.utility.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO extends JPAUtility {

    public UserDAO() {
    }

    public List<CDUser> findAllByCustomer(Integer customerId) {
        EntityManager manager = getEntityManager();
        TypedQuery<CDUser> q = manager.createQuery("SELECT u FROM CDUser u WHERE u.customer.id = ?1", CDUser.class);
        q.setParameter(1, customerId);
        List<CDUser> userList = q.getResultList();
        manager.close();
        return userList;
    }

    public List<CDUser> findAllByGarage(Integer garageId) {
        EntityManager manager = getEntityManager();
        TypedQuery<CDUser> q = manager.createQuery("SELECT u FROM CDUser u WHERE u.garage.id = ?1", CDUser.class);
        q.setParameter(1, garageId);
        List<CDUser> userList = q.getResultList();
        manager.close();
        return userList;
    }

    public List<CDUser> findAll() {
        EntityManager manager = getEntityManager();
        TypedQuery<CDUser> q = manager.createQuery("SELECT u FROM User u", CDUser.class);
        List<CDUser> carList = q.getResultList();
        manager.close();
        return carList;
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
        CDUser car = manager.find(CDUser.class, userId);
        manager.close();
        return car;
    }

    public CDUser update(CDUser newUser, Integer userId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        CDUser user = manager.find(CDUser.class, userId);
        if (user != null) {
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            manager.merge(user);
            manager.getTransaction().commit();
            manager.close();
            return user;
        } else {
            return save(newUser);
        }
    }

    public CDUser delete(Integer userId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        CDUser user = manager.find(CDUser.class, userId);
        if (user != null) {
            user = manager.merge(user);
            manager.remove(user);
            manager.joinTransaction();
            manager.flush();
            manager.getTransaction().commit();
            manager.close();
            return user;
        } else {
            return null;
        }
    }

    public CDUser getByUsername(String username) {
        EntityManager manager = getEntityManager();
        TypedQuery<CDUser> q = manager.createQuery("SELECT u from CDUser u WHERE u.username = ?1", CDUser.class);
        q.setParameter(1, username);
        List<CDUser> userList = q.getResultList();
        manager.close();
        return ((userList.isEmpty() || userList.size() >= 2) ? null : userList.get(0));
    }

}
