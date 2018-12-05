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

    public String getPasswordByUsername(String name) {
        EntityManager manager = getEntityManager();
        TypedQuery<String> q = manager.createQuery("SELECT u.password FROM CDUSER u WHERE u.username = ?1", String.class);
        q.setParameter(1, name);
        List<String> passwordList = q.getResultList();
        manager.close();
        return ((passwordList.isEmpty() || passwordList.size() > 1) ? null : passwordList.get(0));
    }

    public CDUser update(CDUser user, Integer userId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        CDUser userToUpdate = manager.find(CDUser.class, userId);
        if (userToUpdate != null) {
            userToUpdate.setName(user.getName());
            userToUpdate.setPassword(user.getPassword());
            manager.merge(userToUpdate);
            manager.getTransaction().commit();
            manager.close();
            return userToUpdate;
        } else {
            return null;
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
}
