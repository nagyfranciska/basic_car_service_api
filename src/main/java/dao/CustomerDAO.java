package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import model.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import service.JPAUtility;

import javax.persistence.EntityManager;
import javax.persistence.Query;import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CustomerDAO extends JPAUtility{



    public CustomerDAO() {
    }

//        @Inject private Provider<EntityManager> emp;
//
//    private Session getSession() {
//        return emp.get().unwrap(Session.class);
//    }
//
//    public List<Customer> findAll2() {
//        CriteriaQuery<Customer> createQuery = emp.get().getCriteriaBuilder().createQuery(Customer.class);
//        return emp.get().createQuery(createQuery).getResultList();
//    }

    public List findAll() {
        EntityManager manager = getEntityManager();
        Query q = manager.createQuery("SELECT c FROM Customer c");
        List result = q.getResultList();
        manager.close();
        return result;
    }

    public void save(Customer customer) {
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(customer);
        manager.getTransaction().commit();
        manager.close();
    }

    public Customer findById(Integer id) {

//        return emp.get().find(Customer.class, id);

        EntityManager manager = getEntityManager();
        Customer result = manager.find(Customer.class, id);
        manager.close();
        return result;
    }

    public void update(Customer customer) {
        EntityManager manager = getEntityManager();
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
        EntityManager manager = getEntityManager();
        manager.remove(customer);
        manager.close();
    }
}
