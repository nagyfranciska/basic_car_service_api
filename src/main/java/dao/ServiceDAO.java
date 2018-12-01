package dao;

import model.Service;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ServiceDAO extends JPAUtility{

    public ServiceDAO() {
    }

    public List findAll() {
        EntityManager manager = getEntityManager();
        Query q = manager.createQuery("SELECT s FROM Service s");
        List result = q.getResultList();
        manager.close();
        return result;
    }

    public List findAllByCarIdAndCustomerId(Integer customerId, Integer carId) {
        EntityManager manager = getEntityManager();
        Query q = manager.createQuery("SELECT s FROM Service s WHERE CUST_ID = ?1 AND CAR_ID = ?2");
        q.setParameter(1, customerId);
        q.setParameter(2, carId);
        List result = q.getResultList();
        manager.close();
        return result;
    }

    public List findAllByGarage(Integer garageId) {
        EntityManager manager = getEntityManager();
        Query q = manager.createQuery("SELECT s FROM Service s WHERE GARAGE_ID = ?1");
        q.setParameter(1, garageId);
        List result = q.getResultList();
        manager.close();
        return result;
    }

    public Object findByIdAndGarageId(Integer garageId, Integer serviceId) {
        EntityManager manager = getEntityManager();
        Query q = manager.createQuery("SELECT s FROM Service s WHERE GARAGE_ID = ?1 AND ID = ?2");
        q.setParameter(1, garageId);
        q.setParameter(2, serviceId);
        Object result = q.getSingleResult();
        manager.close();
        return result;
    }

    public void save(Service service) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(service);
        manager.getTransaction().commit();
        manager.close();
    }

    public void update(Service service) {
        EntityManager manager = getEntityManager();
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
    }

    public void delete(Service service) {
        EntityManager manager = getEntityManager();
        manager.remove(service);
        manager.close();
    }

}
