package dao;

import model.Service;
import service.utility.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceDAO extends JPAUtility {

    public ServiceDAO() {
    }

    public List<Service> findAll() {
        EntityManager manager = getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s", Service.class);
        List<Service> serviceList = q.getResultList();
        manager.close();
        return serviceList;
    }

    public List<Service> findAllByCar(Integer carId) {
        EntityManager manager = getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s WHERE s.car.id = ?1", Service.class);
        q.setParameter(1, carId);
        List<Service> serviceList = q.getResultList();
        manager.close();
        return serviceList;
    }

    public List<Service> findAllByGarage(Integer garageId) {
        EntityManager manager = getEntityManager();
        TypedQuery<Service> q = manager.createQuery("SELECT s FROM Service s WHERE s.garage.id = ?1", Service.class);
        q.setParameter(1, garageId);
        List<Service> serviceList = q.getResultList();
        manager.close();
        return serviceList;
    }

    public Service save(Service service) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(service);
        manager.getTransaction().commit();
        manager.close();
        return service;
    }

    public Service findById(Integer serviceId) {
        EntityManager manager = getEntityManager();
        Service service = manager.find(Service.class, serviceId);
        manager.close();
        return service;
    }

    public Service update(Service newService, Integer serviceId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Service service = manager.find(Service.class, serviceId);
        if (service != null) {
            service.setStart(newService.getStart());
            service.setEnd(newService.getEnd());
            service.setPrice(newService.getPrice());
            manager.merge(service);
            manager.getTransaction().commit();
            manager.close();
            return service;
        } else {
            return save(newService);
        }
    }

    public Service delete(Integer serviceId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Service service = manager.find(Service.class, serviceId);
        if (service != null) {
            service = manager.merge(service);
            manager.remove(service);
            manager.joinTransaction();
            manager.flush();
            manager.getTransaction().commit();
            manager.close();
            return service;
        } else {
            return null;
        }
    }

}
