package service;

import com.google.inject.Inject;
import dao.CustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerService {

    @Inject
    private CustomerDAO customerDAO;

    public CustomerService() {}

    public List<Customer> getCustomers() {
        return customerDAO.findAll();
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

    public Customer deleteCustomer(Integer customerId) {
        return customerDAO.delete(customerId);
    }
}
