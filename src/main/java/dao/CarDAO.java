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

    public List findAll() {
        Query q = manager.createQuery("SELECT * FROM Car");
        return q.getResultList();
    }

    public void save(Car car) {
        manager.getTransaction().begin();
        manager.persist(car);
        manager.getTransaction().commit();
        manager.close();
        System.out.println("new car is saved");
    }

    public Car findById(Integer id) {
        return manager.find(Car.class, id);
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
        System.out.println("car is updated");
    }

    public void delete(Car car) {
        manager.remove(car);
    }

}
