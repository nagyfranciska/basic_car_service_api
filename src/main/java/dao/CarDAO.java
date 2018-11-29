package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Car;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CarDAO {

    public CarDAO() {
    }

    public List findAllByCustomer(Integer customerId) {
        EntityManager manager = JPAUtility.getEntityManager();
        Query q = manager.createQuery("SELECT c FROM Car c WHERE CUST_ID = ?1");
        q.setParameter(1, customerId);
        List result = q.getResultList();
        manager.close();
        return result;
    }

    public List findAll() {
        EntityManager manager = JPAUtility.getEntityManager();
        Query q = manager.createQuery("SELECT c FROM Car c");
        List result = q.getResultList();
        manager.close();
        return result;
    }

    public void save(Car car) {
        EntityManager manager = JPAUtility.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(car);
        manager.getTransaction().commit();
        manager.close();
    }

    public Object findById(Integer customerId, Integer carId) {
        EntityManager manager = JPAUtility.getEntityManager();
        Query q = manager.createQuery("SELECT c FROM Car c WHERE CUST_ID = ?1 AND ID = 2?");
        q.setParameter(1, customerId);
        q.setParameter(2, carId);
        Object result = q.getSingleResult();
        manager.close();
        return result;
    }

    public void update(Car car) {
        EntityManager manager = JPAUtility.getEntityManager();
        Car carToUpdate = manager.find(Car.class, car.getId());
        manager.getTransaction().begin();
        carToUpdate.setRegistrationDate(car.getRegistrationDate());
        carToUpdate.setColor(car.getColor());
        carToUpdate.setPlate(car.getPlate());
        carToUpdate.setDoorCount(car.getDoorCount());
        carToUpdate.setServiceList(car.getServiceList());
        carToUpdate.setCarType(car.getCarType());
        carToUpdate.setSize(car.getSize());
        manager.getTransaction().commit();
        manager.close();
    }

    public void delete(Car car) {
        EntityManager manager = JPAUtility.getEntityManager();
        manager.remove(car);
        manager.close();
    }
}
