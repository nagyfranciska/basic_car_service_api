package dao;

import model.Customer;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerDAO {

    public CustomerDAO() {
    }

    public List<Customer> findAll() {
        EntityManager manager = JPAUtility.getEntityManager();
        TypedQuery<Customer> q = manager.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> result = q.getResultList();
        manager.close();
        return result;
    }

    public void save(Customer customer) {
        EntityManager manager = JPAUtility.getEntityManager();
        manager.getTransaction().begin();
        manager.persist(customer);
        manager.getTransaction().commit();
        manager.close();
    }

    public Customer findById(Integer id) {
        EntityManager manager = JPAUtility.getEntityManager();
        Customer result = manager.find(Customer.class, id);
        manager.close();
        return result;
    }

    //TODO: Fix update
    public void update(Customer customer) {
        EntityManager manager = JPAUtility.getEntityManager();
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

    public Customer delete(Integer customerId) {
        EntityManager manager = JPAUtility.getEntityManager();
        manager.getTransaction().begin();
        Customer customer = manager.find(Customer.class, customerId);
        customer = manager.merge(customer);
        manager.remove(customer);
        manager.joinTransaction();
        manager.flush();
        manager.getTransaction().commit();
        manager.close();
        return customer;
    }
}
