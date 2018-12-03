package dao;

import model.Service;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceDAO extends JPAUtility{

    public ServiceDAO() {
    }

    public List<Service> findAll() {
        EntityManager manager = getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s", Service.class);
        List<Service> result = q.getResultList();
        manager.close();
        return result;
    }

    public List<Service> findAllByCar(Integer carId) {
        EntityManager manager = getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s WHERE CAR_ID = ?1", Service.class);
        q.setParameter(1, carId);
        List<Service> result = q.getResultList();
        manager.close();
        return result;
    }

    public List<Service> findAllByGarage(Integer garageId) {
        EntityManager manager = getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s WHERE GARAGE_ID = ?1", Service.class);
        q.setParameter(1, garageId);
        List<Service> result = q.getResultList();
        manager.close();
        return result;
    }

    public Service findById(Integer serviceId) {
        EntityManager manager = getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s WHERE ID = ?1", Service.class);
        q.setParameter(1, serviceId);
        Service result = q.getSingleResult();
        manager.close();
        return result;
    }

    public Service save(Service service) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(service);
        manager.getTransaction().commit();
        manager.close();
        return service;
    }

    public Service update(Service newService, Integer serviceId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Service service = manager.find(Service.class, serviceId);
        service.setStart(newService.getStart());
        service.setEnd(newService.getEnd());
        service.setPrice(newService.getPrice());
        manager.merge(service);
        manager.getTransaction().commit();
        manager.close();
        return service;
    }

    public Service delete(Integer serviceId) {
        EntityManager manager = getEntityManager();
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
