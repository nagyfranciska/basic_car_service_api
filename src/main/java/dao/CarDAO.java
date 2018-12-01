package dao;

import model.Car;
import service.JPAUtility;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarDAO extends JPAUtility{

    public CarDAO() {
    }

    public List<Car> findAllByCustomer(Integer customerId) {
        EntityManager manager = getEntityManager();
        TypedQuery<Car> q = manager.createQuery("SELECT c FROM Car c WHERE c.customer.id = ?1", Car.class);
        q.setParameter(1, customerId);
        List<Car> result = q.getResultList();
        manager.close();
        return result;
    }

    public List<Car> findAll() {
        EntityManager manager = getEntityManager();
        TypedQuery<Car> q = manager.createQuery("SELECT c FROM Car c", Car.class);
        List<Car> result = q.getResultList();
        manager.close();
        return result;
    }

    public void save(Car car) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(car);
        manager.getTransaction().commit();
        manager.close();
    }

    public Car findById(Integer carId) {
        EntityManager manager = getEntityManager();
        TypedQuery<Car> q = manager.createQuery("SELECT c FROM Car c WHERE ID = ?1", Car.class);
        q.setParameter(1, carId);
        Car result = q.getSingleResult();
        manager.close();
        return result;
    }

    //TODO: Fix this
    public void update(Car car) {
        EntityManager manager = getEntityManager();
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

    public Car delete(Integer carId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Car car = manager.find(Car.class, carId);
        car = manager.merge(car);
        manager.remove(car);
        manager.joinTransaction();
        manager.flush();
        manager.getTransaction().commit();
        manager.close();
        return car;
    }
}
