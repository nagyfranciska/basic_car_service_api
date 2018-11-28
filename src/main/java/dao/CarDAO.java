package dao;

import model.Car;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
public class CarDAO {

    static EntityManager mananger = JPAUtility.getEntityManager();

    public CarDAO() {
    }

    public void save(Car car) {
        mananger.getTransaction().begin();
        mananger.persist(car);
        mananger.getTransaction().commit();
        mananger.close();
        JPAUtility.close();
        System.out.println("new car is saved");
    }

    public void update(Car car) {
        Car carToUpdate = mananger.find(Car.class, car.getId());
        mananger.getTransaction().begin();
        carToUpdate.setRegistrationDate(car.getRegistrationDate());
        carToUpdate.setColor(car.getColor());
        carToUpdate.setPlate(car.getPlate());
        carToUpdate.setDoorCount(car.getDoorCount());
        carToUpdate.setServiceList(car.getServiceList());
        carToUpdate.setCarType(car.getCarType());
        carToUpdate.setSize(car.getSize());
        mananger.getTransaction().commit();
        mananger.close();
        System.out.println("car is updated");
    }

    public void delete(Car car) {
        mananger.remove(car);
    }

    public Car findById(Integer id) {
        return mananger.find(Car.class, id);
    }

    public List findAll() {
        Query q = mananger.createQuery("SELECT * FROM Car");
        return q.getResultList();
    }
}
