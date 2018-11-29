package dao;

import model.Car;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
public class CarDAO {


    private EntityManager manager = JPAUtility.getEntityManager();

    public CarDAO() {
    }

    public List findAllByCustomer(Integer customerId) {
        Query q = manager.createQuery("SELECT c FROM Car c WHERE CUST_ID = ?1");
        q.setParameter(1,customerId);
        return q.getResultList();
    }

    public List findAll() {
        Query q = manager.createQuery("SELECT c FROM Car c");
        return q.getResultList();
    }
    public void save(Car car) {
        manager.getTransaction().begin();
        manager.persist(car);
        manager.getTransaction().commit();
        manager.close();
    }

    public Object findById(Integer customerId, Integer carId) {
        Query q = manager.createQuery("SELECT c FROM Car c WHERE CUST_ID = ?1 AND ID = 2?");
        q.setParameter(1, customerId);
        q.setParameter(2, carId);
        return q.getSingleResult();
    }

    public void update(Car car) {
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
        manager.remove(car);
    }
}
