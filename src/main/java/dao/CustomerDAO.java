package dao;

import model.Customer;
import model.Garage;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CustomerDAO {

    static EntityManager mananger = JPAUtility.getEntityManager();

    public CustomerDAO() {
    }

    public void save(Customer customer) {
        mananger.getTransaction().begin();
        mananger.persist(customer);
        mananger.getTransaction().commit();
        mananger.close();
        JPAUtility.close();
        System.out.println("new customer is saved");
    }

    public void update(Customer customer) {
        Customer customerToUpdate = mananger.find(Customer.class, customer.getId());
        mananger.getTransaction().begin();

        customerToUpdate.setName(customer.getName());
        customerToUpdate.setType(customer.getType());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setInvoiceAddress(customer.getInvoiceAddress());
        customerToUpdate.setCarList(customer.getCarList());
        customerToUpdate.setServiceList(customer.getServiceList());

        mananger.getTransaction().commit();
        mananger.close();
        System.out.println("customer is updated");
    }

    public void delete(Customer customer) {
        mananger.remove(customer);
    }

    public Customer findById(Integer id) {
        return mananger.find(Customer.class, id);
    }

    public List findAll() {
        Query q = mananger.createQuery("SELECT * FROM Customer");
        return q.getResultList();
    }


}
