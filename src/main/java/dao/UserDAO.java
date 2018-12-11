package dao;

import model.User;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO extends JPAUtility {

    public UserDAO() {}

    public List<User> findAll() {
        EntityManager manager = getEntityManager();
        TypedQuery<User> q = manager.createQuery("SELECT u FROM User u", User.class);
        List<User> userList = q.getResultList();
        manager.close();
        return userList;
    }

    public List<User> findAllByCustomer(Integer customerId) {
        EntityManager manager = getEntityManager();
        TypedQuery<User> q = manager.createQuery("SELECT u FROM User u WHERE u.customer.id = ?1", User.class);
        q.setParameter(1, customerId);
        List<User> userList = q.getResultList();
        manager.close();
        return userList;
    }

    public List<User> findAllByGarage(Integer garageId) {
        EntityManager manager = getEntityManager();
        TypedQuery<User> q = manager.createQuery("SELECT u FROM User u WHERE u.garage.id = ?1", User.class);
        q.setParameter(1, garageId);
        List<User> userList = q.getResultList();
        manager.close();
        return userList;
    }

    public User save(User user) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        manager.close();
        return user;
    }

    public User findById(Integer userId) {
        EntityManager manager = getEntityManager();
        User user = manager.find(User.class, userId);
        manager.close();
        return user;
    }

    public User findByName(String username) {
        EntityManager manager = getEntityManager();
        TypedQuery<User> q = manager.createQuery("SELECT u FROM User u WHERE u.name = ?1", User.class);
        q.setParameter(1, username);
        List<User> userList = q.getResultList();
        manager.close();
        return userList.get(0);
    }

    public User update(User user, Integer userId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        User userToUpdate = manager.find(User.class, userId);
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

    public User delete(Integer userId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        User user = manager.find(User.class, userId);
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
