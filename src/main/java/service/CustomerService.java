package service;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

    //TODO: Solve logic without instantiation
    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

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
