package dao;

import model.Car;
import service.JPAUtility;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarDAO extends JPAUtility{

    public CarDAO() {
    }

    public List<Car> findAllByCustomer(Integer customerId) {
        EntityManager manager = getEntityManager();
        TypedQuery<Car> q = manager.createQuery("SELECT c FROM Car c WHERE c.customer.id = ?1", Car.class);
        q.setParameter(1, customerId);
        List<Car> carList = q.getResultList();
        manager.close();
        return carList;
    }

    public List<Car> findAll() {
        EntityManager manager = getEntityManager();
        TypedQuery<Car> q = manager.createQuery("SELECT c FROM Car c", Car.class);
        List<Car> carList = q.getResultList();
        manager.close();
        return carList;
    }

    public Car save(Car car) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(car);
        manager.getTransaction().commit();
        manager.close();
        return car;
    }

    public Car findById(Integer carId) {
        EntityManager manager = getEntityManager();
        Car car = manager.find(Car.class, carId);
        manager.close();
        return car;
    }

    public Car update(Car newCar, Integer carId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Car car = manager.find(Car.class, carId);
        if(car != null) {
            car.setCarType(newCar.getCarType());
            car.setPlate(newCar.getPlate());
            car.setRegistrationDate(newCar.getRegistrationDate());
            car.setSize(newCar.getSize());
            car.setDoorCount(newCar.getDoorCount());
            car.setColor(newCar.getColor());
            manager.merge(car);
            manager.getTransaction().commit();
            manager.close();
            return car;
        } else {
            return save(newCar);
        }
    }

    public Car delete(Integer carId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Car car = manager.find(Car.class, carId);
        if (car != null) {
            car = manager.merge(car);
            manager.remove(car);
            manager.joinTransaction();
            manager.flush();
            manager.getTransaction().commit();
            manager.close();
            return car;
        } else {
            return null;
        }
    }

    public boolean plateIsUnique(String plate) {
        EntityManager manager = getEntityManager();
        Query q = manager.createQuery("SELECT 1 from Car c WHERE c.plate = ?1");
        q.setParameter(1, plate);
        return (q.getResultList().isEmpty());
    }
}
