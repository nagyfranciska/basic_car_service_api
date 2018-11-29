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

    public List<Customer> findAll() {
        Query q = manager.createQuery("SELECT * FROM Customer");
        return q.getResultList();
    }

    public Customer save(Customer customer) {
        EntityManager manager2 = JPAUtility.getEntityManager();
        manager2.getTransaction().begin();
        manager2.persist(customer);
        manager2.getTransaction().commit();
        manager2.close();
        System.out.println("new customer is saved");
        return customer;
    }

    public Customer findById(Integer id) {
        return manager.find(Customer.class, id);
    }

    public Customer update(Customer customer) {
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
        System.out.println("customer is updated");
        return customer;
    }

    public Customer delete(Customer customer) {
        manager.remove(customer);
        return customer;
    }

}
