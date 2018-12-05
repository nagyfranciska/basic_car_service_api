package dao;

import model.Customer;
import org.restlet.resource.ResourceException;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerDAO extends JPAUtility{

    public CustomerDAO() {
    }

    public List<Customer> findAll() {
        EntityManager manager = getEntityManager();
        TypedQuery<Customer> q = manager.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> result = q.getResultList();
        manager.close();
        return result;
    }

    public Customer save(Customer customer) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(customer);
        manager.getTransaction().commit();
        manager.close();
        return customer;
    }

    public Customer findById(Integer customerId) {
        EntityManager manager = getEntityManager();
        Customer result = manager.find(Customer.class, customerId);
        manager.close();
        return result;
    }

    //TODO : postman failed with id 4, worked with id 1 (non-existing based on postmen getCustomers)
    // it creates a new customer with a new id --> check why it worked at the garage and why not here
    public Customer update(Customer newCustomer, Integer customerId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Customer customer = manager.find(Customer.class, customerId);
        if (customer != null) {
            customer.setName(newCustomer.getName());
            customer.setType(newCustomer.getType());
            customer.setAddress(newCustomer.getAddress());
            customer.setInvoiceAddress(newCustomer.getInvoiceAddress());
            manager.merge(customer);
            manager.getTransaction().commit();
            manager.close();
            return customer;
        } else {
            return save(newCustomer);
        }
    }

    //TODO : same problem as at garage delete
    public Customer delete(Integer customerId) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Customer customer = manager.find(Customer.class, customerId);
        if (customer != null) {
            customer = manager.merge(customer);
            manager.remove(customer);
            manager.joinTransaction();
            manager.flush();
            manager.getTransaction().commit();
            manager.close();
            return customer;
        } else {
            return null;
        }


    }



}
