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
        Query q = manager.createQuery("SELECT s FROM Service s");
        return q.getResultList();
    }

    public List findAllByCarIdAndCustomerId(Integer customerId, Integer carId) {
        Query q = manager.createQuery("SELECT s FROM Service s WHERE CUST_ID = ?1 AND CAR_ID = ?2");
        q.setParameter(1, customerId);
        q.setParameter(2, carId);
        return q.getResultList();
    }

    public List findAllByGarage(Integer garageId) {
        Query q = manager.createQuery("SELECT s FROM Service s WHERE GARAGE_ID = ?1");
        q.setParameter(1, garageId);
        return q.getResultList();
    }

    public Object findByIdAndGarageId(Integer garageId, Integer serviceId) {
        Query q = manager.createQuery("SELECT s FROM Service s WHERE GARAGE_ID = ?1 AND ID = ?2");
        q.setParameter(1, garageId);
        q.setParameter(2, serviceId);
        return q.getSingleResult();
    }

    public Service save(Service service) {
        manager.getTransaction().begin();
        manager.persist(service);
        manager.getTransaction().commit();
        manager.close();
        System.out.println("new service is saved");
        return service;
    }

    public Service findById(Integer id) {
        return manager.find(Service.class, id);
    }

    public List findByCar(Integer carId) {
        Query q = manager.createQuery("SELECT * FROM Service WHERE CAR_ID = ?1");
        q.setParameter(1, carId);
        return q.getResultList();
    }

    public Service update(Service service) {
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
        return service;
    }

    public Service delete(Service service) {
        manager.remove(service);
        return service;
    }

}
