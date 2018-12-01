package service;

import com.google.inject.Inject;
import dao.CustomerDAO;
import model.Customer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CustomerService {

    @Inject
    CustomerDAO customerDAO;

    public CustomerService() {
    }

//        public List<Customer> getCustomers2() {
//        return customerDAO.findAll2();
//    }

    public List<Customer> getCustomers() {
        List rawList = customerDAO.findAll();
        List<Customer> customerList = new CopyOnWriteArrayList<>();
        try {
            rawList.forEach(c -> customerList.add((Customer)c));
        } catch (TypeNotPresentException e) {
            System.out.println("error in CustomerService with customerList");
        }
        return customerList;
    }

    public Customer saveCustomer(Customer customer) {
        customerDAO.save(customer);
        return getCustomerById(customer.getId());
    }

    public Customer getCustomerById(Integer id) {
        return customerDAO.findById(id);
    }

    public Customer updateCustomer(Customer customer) {
        customerDAO.update(customer);
        return getCustomerById(customer.getId());
    }

    public Customer deleteCustomer(Customer customer) {
        Customer deletedCustomer = getCustomerById(customer.getId());
        customerDAO.delete(customer);
        return deletedCustomer;
    }
}
