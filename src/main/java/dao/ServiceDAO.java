package dao;

import model.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class ServiceDAO {

    static EntityManager mananger = JPAUtility.getEntityManager();

    public ServiceDAO() {
    }

    public void save(Service service) {
        mananger.getTransaction().begin();
        mananger.persist(service);
        mananger.getTransaction().commit();
        mananger.close();
        JPAUtility.close();
        System.out.println("new service is saved");
    }

    public void update(Service service) {
        Service garageToUpdate = mananger.find(Service.class, service.getId());
        mananger.getTransaction().begin();

        garageToUpdate.setStart(service.getStart());
        garageToUpdate.setEnd(service.getEnd());
        garageToUpdate.setPrice(service.getPrice());
        garageToUpdate.setCar(service.getCar());
        garageToUpdate.setGarage(service.getGarage());
        garageToUpdate.setCustomer(service.getCustomer());

        mananger.getTransaction().commit();
        mananger.close();
        System.out.println("service is updated");
    }

    public void delete(Service service) {
        mananger.remove(service);
    }

    public Service findById(Integer id) {
        return mananger.find(Service.class, id);
    }

    public List findAll() {
        Query q = mananger.createQuery("SELECT * FROM Service");
        return q.getResultList();
    }

}
