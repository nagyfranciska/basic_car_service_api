package dao;

import model.Garage;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GarageDAO {

    private EntityManager manager = JPAUtility.getEntityManager();

    public GarageDAO() {
    }

    public List findAll() {
        Query q = manager.createQuery("SELECT g FROM Garage g");
        return q.getResultList();
    }

    public Garage save(Garage garage) {
        EntityManager manager2 = JPAUtility.getEntityManager();
        manager2.getTransaction().begin();
        manager2.persist(garage);
        manager2.getTransaction().commit();
        manager2.close();
        System.out.println("new garage is saved");
        return garage;
    }


    public Garage findById(Integer id) {
        return manager.find(Garage.class, id);
    }


    public Garage update(Garage garage) {
        Garage garageToUpdate = manager.find(Garage.class, garage.getId());
        manager.getTransaction().begin();
        garageToUpdate.setAddress(garage.getAddress());
        garageToUpdate.setCapacity(garage.getCapacity());
        garageToUpdate.setName(garage.getName());
        garageToUpdate.setServiceList(garage.getServiceList());
        manager.getTransaction().commit();
        manager.close();
        System.out.println("garage is updated");
        return garageToUpdate;
    }

    public Garage delete(Garage garage) {
        manager.remove(garage);
        return garage;
    }

}
