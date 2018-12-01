package dao;

import model.Service;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceDAO {

    public ServiceDAO() {
    }

    public List<Service> findAll() {
        EntityManager manager = JPAUtility.getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s", Service.class);
        List<Service> result = q.getResultList();
        manager.close();
        return result;
    }

    public List<Service> findAllByCar(Integer carId) {
        EntityManager manager = JPAUtility.getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s WHERE CAR_ID = ?1", Service.class);
        q.setParameter(1, carId);
        List<Service> result = q.getResultList();
        manager.close();
        return result;
    }

    public List<Service> findAllByGarage(Integer garageId) {
        EntityManager manager = JPAUtility.getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s WHERE GARAGE_ID = ?1", Service.class);
        q.setParameter(1, garageId);
        List<Service> result = q.getResultList();
        manager.close();
        return result;
    }

    public Service findById(Integer serviceId) {
        EntityManager manager = JPAUtility.getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s WHERE ID = ?1", Service.class);
        q.setParameter(1, serviceId);
        Service result = q.getSingleResult();
        manager.close();
        return result;
    }

    public void save(Service service) {
        EntityManager manager = JPAUtility.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(service);
        manager.getTransaction().commit();
        manager.close();
    }

    //TODO: Fix update
    public void update(Service service) {
        EntityManager manager = JPAUtility.getEntityManager();
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

    public Service delete(Integer serviceId) {
        EntityManager manager = JPAUtility.getEntityManager();
        manager.getTransaction().begin();
        Service service = manager.find(Service.class, serviceId);
        service = manager.merge(service);
        manager.remove(service);
        manager.joinTransaction();
        manager.flush();
        manager.getTransaction().commit();
        manager.close();
        return service;
    }

}
