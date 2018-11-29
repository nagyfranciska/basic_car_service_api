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

    public void save(Garage garage) {
        manager.getTransaction().begin();
        manager.persist(garage);
        manager.getTransaction().commit();
        manager.close();
    }


    public Garage findById(Integer id) {
        return manager.find(Garage.class, id);
    }


    public void update(Garage garage) {
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
        manager.remove(garage);
    }

}
