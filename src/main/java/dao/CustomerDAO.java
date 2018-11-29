package dao;

import model.Customer;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CustomerDAO {

    private EntityManager manager = JPAUtility.getEntityManager();

    public CustomerDAO() {
    }

    public List findAll() {
        Query q = manager.createQuery("SELECT c FROM Customer c");
        return q.getResultList();
    }

    public void save(Customer customer) {
        manager.getTransaction().begin();
        manager.persist(customer);
        manager.getTransaction().commit();
        manager.close();
    }

    public Customer findById(Integer id) {
        return manager.find(Customer.class, id);
    }

    public void update(Customer customer) {
        Customer customerToUpdate = manager.find(Customer.class, customer.getId());
        manager.getTransaction().begin();
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setType(customer.getType());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setInvoiceAddress(customer.getInvoiceAddress());
        customerToUpdate.setCarList(customer.getCarList());
        customerToUpdate.setServiceList(customer.getServiceList());
        manager.getTransaction().commit();
        manager.close();
    }

    public void delete(Customer customer) {
        manager.remove(customer);
    }
}
