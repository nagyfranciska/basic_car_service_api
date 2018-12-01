package dao;

import model.Garage;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GarageDAO extends JPAUtility{

    public GarageDAO() {
    }

    public List<Garage> findAll() {
        EntityManager manager =getEntityManager();
        TypedQuery<Garage> q = manager.createQuery("SELECT g FROM Garage g", Garage.class);
        List<Garage> result = q.getResultList();
        manager.close();
        return result;
    }

    public void save(Garage garage) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(garage);
        manager.getTransaction().commit();
        manager.close();
    }


    public Garage findById(Integer id) {
        EntityManager manager = getEntityManager();
        Garage result = manager.find(Garage.class, id);
        manager.close();
        return result;
    }

    public Garage update(Garage newGarage, Integer garageId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Garage garage = manager.find(Garage.class, garageId);
        garage.setAddress(newGarage.getAddress());
        garage.setCapacity(newGarage.getCapacity());
        garage.setName(newGarage.getName());
        manager.merge(garage);
        manager.getTransaction().commit();
        manager.close();
        return garage;
    }

    public Garage delete(Integer garageId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Garage garage = manager.find(Garage.class, garageId);
        manager.merge(garage);
        manager.remove(garage);
        manager.joinTransaction();
        manager.flush();
        manager.getTransaction().commit();
        manager.close();
        return garage;
    }

}
