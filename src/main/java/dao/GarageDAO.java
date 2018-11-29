package dao;

import model.Garage;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class GarageDAO {

    public GarageDAO() {
    }

    public List findAll() {
        EntityManager manager = JPAUtility.getEntityManager();
        Query q = manager.createQuery("SELECT g FROM Garage g");
        List result = q.getResultList();
        manager.close();
        return result;
    }

    public void save(Garage garage) {
        EntityManager manager = JPAUtility.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(garage);
        manager.getTransaction().commit();
        manager.close();
    }


    public Garage findById(Integer id) {
        EntityManager manager = JPAUtility.getEntityManager();
        Garage result = manager.find(Garage.class, id);
        manager.close();
        return result;
    }


    public void update(Garage garage) {
        EntityManager manager = JPAUtility.getEntityManager();
        Garage garageToUpdate = manager.find(Garage.class, garage.getId());
        manager.getTransaction().begin();
        garageToUpdate.setAddress(garage.getAddress());
        garageToUpdate.setCapacity(garage.getCapacity());
        garageToUpdate.setName(garage.getName());
        garageToUpdate.setServiceList(garage.getServiceList());
        manager.getTransaction().commit();
        manager.close();
    }

    public void delete(Garage garage) {
        EntityManager manager = JPAUtility.getEntityManager();
        manager.remove(garage);
        manager.close();
    }

}
