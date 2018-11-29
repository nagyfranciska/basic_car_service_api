package dao;

import model.Service;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServiceDAO {

    private EntityManager manager = JPAUtility.getEntityManager();

    public ServiceDAO() {
    }

    public List findAll() {
        Query q = manager.createQuery("SELECT * FROM Service");
        return q.getResultList();
    }

    public void save(Service service) {
        manager.getTransaction().begin();
        manager.persist(service);
        manager.getTransaction().commit();
        manager.close();
        System.out.println("new service is saved");
    }

    public Service findById(Integer id) {
        return manager.find(Service.class, id);
    }

    public List findByCar(Integer carId) {
        Query q = manager.createQuery("SELECT * FROM Service WHERE CAR_ID = ?1");
        q.setParameter(1, carId);
        return q.getResultList();
    }

    public void update(Service service) {
        Service serviceToUpdate = manager.find(Service.class, service.getId());
        manager.getTransaction().begin();
        serviceToUpdate.setStart(service.getStart());
        serviceToUpdate.setEnd(service.getEnd());
        serviceToUpdate.setPrice(service.getPrice());
        serviceToUpdate.setCar(service.getCar());
        serviceToUpdate.setGarage(service.getGarage());
        serviceToUpdate.setCustomer(service.getCustomer());
        manager.getTransaction().commit();
        manager.close();
        System.out.println("service is updated");
    }

    public void delete(Service service) {
        manager.remove(service);
    }

}
