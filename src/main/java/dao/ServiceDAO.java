package dao;

import model.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class ServiceDAO {

    private EntityManager manager = JPAUtility.getEntityManager();

    public ServiceDAO() {
    }

    public void save(Service service) {
        manager.getTransaction().begin();
        manager.persist(service);
        manager.getTransaction().commit();
        manager.close();
        JPAUtility.close();
        System.out.println("new service is saved");
    }

    public void update(Service service) {
        Service garageToUpdate = manager.find(Service.class, service.getId());
        manager.getTransaction().begin();

        garageToUpdate.setStart(service.getStart());
        garageToUpdate.setEnd(service.getEnd());
        garageToUpdate.setPrice(service.getPrice());
        garageToUpdate.setCar(service.getCar());
        garageToUpdate.setGarage(service.getGarage());
        garageToUpdate.setCustomer(service.getCustomer());

        manager.getTransaction().commit();
        manager.close();
        System.out.println("service is updated");
    }

    public void delete(Service service) {
        manager.remove(service);
    }

    public Service findById(Integer id) {
        return manager.find(Service.class, id);
    }

    public List findAll() {
        Query q = manager.createQuery("SELECT * FROM Service");
        return q.getResultList();
    }

}
