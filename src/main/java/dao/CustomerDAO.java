package dao;

import model.Customer;
import service.utility.JPAUtility;

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
