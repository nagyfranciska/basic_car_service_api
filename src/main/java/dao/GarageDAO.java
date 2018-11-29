package dao;

import model.Garage;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class GarageDAO {

    static EntityManager mananger = JPAUtility.getEntityManager();

    public GarageDAO() {
    }

    public Garage save(Garage garage) {
        mananger.getTransaction().begin();
        mananger.persist(garage);
        mananger.getTransaction().commit();
        mananger.close();
        JPAUtility.close();
        System.out.println("new garage is saved");
        return garage;
    }

    public Garage update(Garage garage) {
        Garage garageToUpdate = mananger.find(Garage.class, garage.getId());
        mananger.getTransaction().begin();

        garageToUpdate.setAddress(garage.getAddress());
        garageToUpdate.setCapacity(garage.getCapacity());
        garageToUpdate.setName(garage.getName());
        garageToUpdate.setServiceList(garage.getServiceList());

        mananger.getTransaction().commit();
        mananger.close();
        System.out.println("garage is updated");
        return garageToUpdate;
    }

    public Garage delete(Garage garage) {
        mananger.remove(garage);
        return garage;
    }

    public Garage findById(Integer id) {
        return mananger.find(Garage.class, id);
    }

    public List findAll() {
        Query q = mananger.createQuery("SELECT * FROM Garage");
        return q.getResultList();
    }

}
